import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UserHomePage = () => {
  const [balance, setBalance] = useState(null);
  const [username, setUsername] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Replace 'userId' with the actual identifier for the logged-in user
        const [balanceResponse, usernameResponse] = await Promise.all([
          axios.get(`http://localhost:9080/api/v1/balance`, {
            withCredentials: true,
            headers: {
              'Authorization': 'Basic ' + btoa('admin:admin'),
            },
          }),
          axios.get(`http://localhost:9080/api/v1/balance/getUsername`, {
            withCredentials: true,
            headers: {
              'Authorization': 'Basic ' + btoa('admin:admin'),
            },
          }),
        ]);

        if (balanceResponse.status === 200) {
          const { amount } = balanceResponse.data;
          setBalance(amount);
        } else {
          console.error('Failed to fetch user balance');
        }

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
          <p>This is the account holder dashboard of the Bank Application.</p>
          {balance !== null ? (
            <p>Your balance: ${balance.toFixed(2)}</p>
          ) : (
            <p>Loading balance...</p>
          )}
          <p>Here, you can manage your account, see balance, transactions history, and more.</p>
          <button className="btn btn-primary">Welcome!</button>
        </div>
      </div>
    </div>
  );
};

export default UserHomePage;
