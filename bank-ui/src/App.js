import React, { useState, useEffect } from 'react';
import './lib/css/bootstrap.min.css';
// import './lib/js/bootstrap.min.js';
import AdminPanel from './pages/Admin/AdminPanel/AdminPanel';
import UserPanel from './pages/User/UserPanel/UserPanel';
import Navbar from './components/Navbar/Navbar';
import { BrowserRouter as Router } from 'react-router-dom';
import axios from 'axios';
import './App.css';

function App() {
  const [user, setUser] = useState(null);

  useEffect(() => {

    const fetchUserRoles = async () => {
      try {
        const response = await axios.get('/api/v1/accounts/getRoles');

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
      {/* <Routes>
        <Route path="/login" element={<Login />} />
      </Routes> */}
      <Navbar user={user} />
      {user && user.role === 'admin' && (
        <AdminPanel />
      )}
      {user && user.role === 'user' && (
        <UserPanel />
      )}
    </Router>
  );
}

export default App;

