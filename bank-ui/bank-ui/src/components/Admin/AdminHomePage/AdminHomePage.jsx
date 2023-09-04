
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AdminHomePage = () => {
  const [username, setUsername] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [usernameResponse] = await Promise.all([
          axios.get(`http://localhost:9080/api/v1/balance/getUsername`, {
            withCredentials: true,
            headers: {
              'Authorization': 'Basic ' + btoa('admin:admin'),
            },
          }),
        ]);

        if (usernameResponse.status === 200) {
          const username = usernameResponse.data; // Set username as-is
          setUsername(username);
        } else {
          console.error('Failed to fetch username');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };

    // Call the fetchData function when the component mounts
    fetchData();
  }, []);
  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-md-6 offset-md-3 text-center">
          <h1>Welcome, @_{username}!</h1>
          <p>This is the Admin dashboard of the Bank Application.</p>
          <p>Here, you can manage user accounts, transactions, and more.</p>
          <button className="btn btn-primary">Welcome!</button>
        </div>
      </div>
    </div>
  );
};

export default AdminHomePage;
