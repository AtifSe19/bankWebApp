import React, { useState, useEffect } from 'react';
import './lib/bootstrap/dist/css/bootstrap.min.css';
import '../node_modules/bootstrap/dist/js/bootstrap.min.js';
import AdminPanel from './components/Admin/AdminPanel/AdminPanel';
import UserPanel from './components/User/UserPanel/UserPanel';
import Navbar from './components/Navbar/Navbar';
import { BrowserRouter as Router } from 'react-router-dom';
import axios from 'axios';
import './App.css';

function App() {
  const [user, setUser] = useState(null);

  useEffect(() => {
    // Fetch user roles from the backend when the component mounts
    const fetchUserRoles = async () => {
      try {
        const response = await axios.get('http://localhost:9080/api/v1/accounts/getRoles', {
          withCredentials: true,
          headers: {
            'Authorization': 'Basic ' + btoa('admin:admin'),
            'Content-Type': 'application/json',
          },
        });

        if (response.status === 200) {
          const roles = response.data;
          // Determine the user's role based on the fetched roles
          setUser({ role: roles.includes('ADMIN') ? 'admin' : 'user' });
        } else {
          console.error('Failed to fetch user roles');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchUserRoles();
  }, []);

  return (
    <Router>
      <Navbar user={user} />
      {user && user.role === 'admin' ? (
        <AdminPanel />
      ) : (
        <UserPanel />
      )}
    </Router>
  );
}

export default App;

