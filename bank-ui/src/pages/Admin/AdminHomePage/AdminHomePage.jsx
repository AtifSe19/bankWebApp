
import React, { useEffect, useState } from 'react';
import axios from 'axios';

import { toast } from 'react-toastify';

const AdminHomePage = () => {
  const [username, setUsername] = useState(null);

  const handleWelcome = () => {
    toast.success(`Welcome, @_${username}! to the Admin dashboard`, {
      position: toast.POSITION.TOP_CENTER,
      });
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [usernameResponse] = await Promise.all([
          axios.get(`/api/v1/accounts/getUsername`, {
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
          <p>Here, you can manage manage user accounts and more.</p>
          <button className="btn btn-primary" onClick={handleWelcome} style={{border: "1px solid #008080"}}>Welcome!</button>
        </div>
      </div>
    </div>
  );
};

export default AdminHomePage;
