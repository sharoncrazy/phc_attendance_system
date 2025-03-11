import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios'
export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    // Dummy API Call (Replace it with Axios or Fetch)

    try {
        // ✅ API Call to Spring Boot Backend
        const response = await axios.post('http://localhost:8080/login', {
          email: email,
          password: password
        });

    // Store the Token and Role in LocalStorage
    localStorage.setItem('token', response.data.token);
    localStorage.setItem('role', response.data.role);
    console.log(response);
    // Redirect Based on Role
    if (response.role === 'ADMIN') {
      navigate('/admin');
    } else if (response.role === 'DOCTOR') {
      navigate('/doctor');
    } else {
      navigate('/');
    }
    } catch (error) {
      // ✅ Handle Login Failure
      if (error.response && error.response.status === 401) {
        alert('Invalid Credentials');
      } else {
        alert('Something went wrong. Please try again.' + error);
      }
    }
  };

  return (
    <div>
      <h1>Login</h1>
      <form onSubmit={handleLogin}>
        <div>
          <label>Email:</label>
          <input 
            type="email" 
            value={email} 
            onChange={(e) => setEmail(e.target.value)} 
            required 
          />
        </div>
        <div>
          <label>Password:</label>
          <input 
            type="password" 
            value={password} 
            onChange={(e) => setPassword(e.target.value)} 
            required 
          />
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
}
