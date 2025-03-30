import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axiosInstance from "../services/axiosInstance"; // Axios instance with JWT token

const FacilityList = () => {
  const { blockName } = useParams(); // ✅ Get block name from URL
  const [facilities, setFacilities] = useState([]);
  const [loading, setLoading] = useState(true); // ✅ Loading state
  const [error, setError] = useState(null); // ✅ Error state

  useEffect(() => {
    const fetchFacilities = async () => {
      try {
        const response = await axiosInstance.get(`/api/facilities/${blockName}/facilities`);
        setFacilities(response.data);
      } catch (err) {
        console.error("Error fetching facilities:", err);
        setError("Failed to fetch facilities");
      } finally {
        setLoading(false);
      }
    };

    fetchFacilities();
  }, [blockName]);

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-2">Facilities in {blockName}</h2>

      {loading ? (
        <p className="text-gray-500">Loading facilities...</p>
      ) : error ? (
        <p className="text-red-500">{error}</p>
      ) : facilities.length > 0 ? (
        <ul className="bg-gray-100 p-4 rounded-lg shadow-md">
          {facilities.map((facility, index) => (
            <li key={index} className="py-2 border-b last:border-none">
              {facility.name}
            </li>
          ))}
        </ul>
      ) : (
        <p className="text-gray-500">No facilities found</p>
      )}
    </div>
  );
};

export default FacilityList;
