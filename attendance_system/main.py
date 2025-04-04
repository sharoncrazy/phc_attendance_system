import mysql.connector
import numpy as np
import hashlib
from datetime import datetime
import requests

class Fingerprint:
    def __init__(self, minutiae_points):
        self.minutiae = minutiae_points  

    def to_feature_vector(self):
        # Convert minutiae points to a string for hashing
        return ''.join([f"{x}{y}{t}" for x, y, t in self.minutiae])

    def encrypt(self):
        # Simulate encryption with SHA-256 hash
        feature_vector = self.to_feature_vector()
        return hashlib.sha256(feature_vector.encode()).hexdigest()


#Creating configuration
db_config = {
        'database':'attendance_db',
        'host' : 'localhost',
        'password' : 'root',
        'user' : 'root'
    }


def setup_database():
        conn = mysql.connector.connect(**db_config)
        c = conn.cursor()
        c.execute('''CREATE TABLE IF NOT EXISTS fingerprints (
                    employee_id VARCHAR(10) PRIMARY KEY,
                    template VARCHAR(64),
                    name VARCHAR(100))''')
        c.execute('''CREATE TABLE IF NOT EXISTS attendance (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    employee_id VARCHAR(10),
                    timestamp DATETIME,
                    status VARCHAR(20),
                    FOREIGN KEY (employee_id) REFERENCES fingerprints(employee_id))''')
        conn.commit()
        conn.close()
        print("Database tables created or verified.")

def generate_fingerprint(num_points=10):
    # Generate random minutiae points
    minutiae = np.random.randint(0, 100, size=(num_points, 2))  
    types = np.random.randint(0, 2, size=(num_points, 1))        
    minutiae = np.hstack((minutiae, types))                     
    return Fingerprint(minutiae)

# Register a doctor
def register_doctor(employee_id, name, minutiae_points):
    fingerprint = Fingerprint(minutiae_points)
    template = fingerprint.encrypt()

    conn = mysql.connector.connect(**db_config)
    c = conn.cursor()
    c.execute("INSERT INTO fingerprints (employee_id, template, name) VALUES (%s, %s, %s) "
              "ON DUPLICATE KEY UPDATE template=%s, name=%s",
              (employee_id, template, name, template, name))
    conn.commit()
    conn.close()
    print(f"Registered {name} with Employee ID {employee_id}")


def send_attendance_post_request(employee_id, name, status, timestamp):
    
    url = "test"
    
    
    payload = {
        "employee_id": employee_id,
    }
    
    try:
        # POST request
        response = requests.post(url, json=payload, headers={"Content-Type": "application/json"})
        response.raise_for_status()  
        print(f"POST request sent successfully: {response.status_code}")
    except requests.exceptions.RequestException as e:
        print(f"Failed to send POST request: {e}")

# Authenticate fingerprint
def authenticate(employee_id, captured_fingerprint, threshold=0.8):
    conn = mysql.connector.connect(**db_config)
    c = conn.cursor()
    c.execute("SELECT template, name FROM fingerprints WHERE employee_id = %s", (employee_id,))
    result = c.fetchone()
    conn.close()

    if not result:
        return False, "Employee ID not found"

    stored_template, name = result
    captured_template = captured_fingerprint.encrypt()

    # Simplified matching (hash equality); real systems use minutiae alignment
    score = 1.0 if stored_template == captured_template else 0.0

    # Log attendance
    status = "Present" if score >= threshold else "Absent"
    log_attendance(employee_id, status)

    return score >= threshold, f"Authentication for {name}: {status} (Score: {score})"

# Log attendance
def log_attendance(employee_id, status):
    conn = mysql.connector.connect(**db_config)
    c = conn.cursor()
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    c.execute("INSERT INTO attendance (employee_id, timestamp, status) VALUES (%s, %s, %s)",
              (employee_id, timestamp, status))
    conn.commit()
    conn.close()

# testing
if __name__ == "__main__":
    
    setup_database()

    # Register a doctor with simulated fingerprint
    doctor_fp = generate_fingerprint(num_points=10)
    register_doctor("DOC001", "Dr. Jayanthi", doctor_fp.minutiae)

    # Simulate authentication with a random fingerprint 
    captured_fp_random = generate_fingerprint(num_points=10)
    success, message = authenticate("DOC001", captured_fp_random)
    print(message)

    # Simulate authentication with the same fingerprint 
    captured_fp_match = Fingerprint(doctor_fp.minutiae)
    success, message = authenticate("DOC001", captured_fp_match)
    print(message)

    # View attendance 
    conn = mysql.connector.connect(**db_config)
    c = conn.cursor()
    c.execute("SELECT employee_id, timestamp, status FROM attendance")
    print("Attendance Log:", c.fetchall())
    conn.close()