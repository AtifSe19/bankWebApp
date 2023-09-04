import React, { useState } from 'react';
import axios from 'axios';

const AddAccountHolder = () => {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    roles: 'USER', // Default role is 'user'
    address: '',
  });

  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      const dataToSend = {
        ...formData,
        roles: formData.roles.toUpperCase(),
      };
  
      const response = await axios.post('http://localhost:9080/api/v1/accounts', dataToSend, {
        withCredentials: true,
        headers: {
          'Authorization': 'Basic ' + btoa('admin:admin'),
        },
      });
  
      if (response.status === 200) {
        // Handle successful submission, e.g., show a success message or redirect
        console.log('Account holder added successfully');
        setMessage('Account holder added successfully');
      } else {
        // Handle submission failure
        console.error('Submission failed');
        setMessage("Account holder can't be added");
      }
    } catch (error) {
      setMessage(`Error: ${error.message}`);
    }
  };
  

  return (
    <div className='container mt-5 my-5'>
      <div className="row">
        <div className="col-md-12 text-center">
          <h1>Add Account Holder</h1>
        </div>
      </div>
      <div className="row">
        <div className="col-md-7 mx-auto">
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">Username</label>
              <input
                type="text"
                className="form-control"
                id="username"
                name="username"
                value={formData.username}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">Email address</label>
              <input
                type="email"
                className="form-control"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">Password</label>
              <input
                type="password"
                className="form-control"
                id="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
              />
            </div>
            <div className="form-check">
              <input
                className="form-check-input"
                type="radio"
                name="roles"
                id="admin"
                value="admin"
                checked={formData.roles === 'admin'}
                onChange={handleChange}
              />
              <label className="form-check-label" htmlFor="admin">
                Admin
              </label>
            </div>
            <div className="form-check">
              <input
                className="form-check-input"
                type="radio"
                name="roles"
                id="user"
                value="user"
                checked={formData.roles === 'user'}
                onChange={handleChange}
              />
              <label className="form-check-label" htmlFor="user">
                User
              </label>
            </div>
            <div className="mb-3">
              <label htmlFor="address" className="form-label">Address</label>
              <textarea
                className="form-control"
                id="address"
                name="address"
                rows="3"
                value={formData.address}
                onChange={handleChange}
              ></textarea>
            </div>
            <button type="submit" className="btn btn-primary">Add</button>
          </form>
          {message && <p className="mt-3">{message}</p>}
        </div>
      </div>
    </div>
  );
};

export default AddAccountHolder;
