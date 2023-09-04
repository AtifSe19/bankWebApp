import React from 'react';
import './lib/bootstrap/dist/css/bootstrap.min.css';
import AdminPanel from './components/Admin/AdminPanel/AdminPanel';
import UserPanel from './components/User/UserPanel/UserPanel';
import Navbar from './components/Navbar/Navbar';
import { useState, useEffect } from 'react';
import { BrowserRouter as Router } from 'react-router-dom';

function App() {
  const [user, setUser] = useState(null);
  // setUser({ role: 'admin' });

  useEffect(() => {
    setUser({ role: 'user' }); // Example for a non-admin user
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
