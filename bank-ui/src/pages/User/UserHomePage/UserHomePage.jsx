import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';

const UserHomePage = () => {
  const [balance, setBalance] = useState(null);
  const [username, setUsername] = useState(null);

  const handleWelcome = () => {
    toast.success(`Welcome, @_${username}! to the User dashboard`, {
      position: toast.POSITION.TOP_CENTER,
      });
    }

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [balanceResponse, usernameResponse] = await Promise.all([
          axios.get(`/api/v1/balances`, {
            withCredentials: true,
            headers: {
              'Authorization': 'Basic ' + btoa('admin:admin'),
            },
          }),
          axios.get(`/api/v1/accounts/getUsername`, {
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
          <p>This is the Account Holder dashboard of the Bank Application.</p>
          {balance !== null ? (
            <p>Your balance: ${balance.toFixed(2)}</p>
          ) : (
            <p>Loading balance...</p>
          )}
          <p>Here, you can manage your account, see balance, transactions history, and more.</p>
          <button className="btn btn-primary" onClick={handleWelcome}>Welcome!</button>
        </div>
      </div>
    </div>
  );
};

export default UserHomePage;
