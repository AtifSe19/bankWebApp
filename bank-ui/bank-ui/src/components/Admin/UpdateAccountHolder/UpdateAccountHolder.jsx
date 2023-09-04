import React, { useState } from 'react';
import axios from 'axios';

const UpdateAccountHolder = () => {
  const [username, setUsername] = useState('');
  const [accountHolder, setAccountHolder] = useState({
    id: '',
    username: '',
    password: '',
    email: '',
    roles: '',
    address: '',
  });

  const handleChange = (e) => {
    setUsername(e.target.value);
  };

  const handleSearch = async () => {
    try {
      // Send a request to search for the account holder by username
      const response = await axios.get(`http://localhost:9080/api/v1/accounts/getSpecAccHolder/${username}`, {
        withCredentials: true,
        headers: {
          'Authorization': 'Basic ' + btoa('admin:admin'),
        },
      });

      if (response.status === 200) {
        // Set the found account holder data
        setAccountHolder(response.data);
      } else {
        // Handle account holder not found
        console.error('Account holder not found');
        setAccountHolder({
          id: '',
          username: '',
          password: '',
          email: '',
          roles: '',
          address: '',
        });
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Exclude 'id', 'username', and 'password' from the data to be sent
      const { id, username, password, roles, ...dataToSend } = accountHolder;

      const response = await axios.put(`http://localhost:9080/api/v1/accounts/update/${username}`, dataToSend, {
        withCredentials: true,
        headers: {
          'Authorization': 'Basic ' + btoa('admin:admin'),
          'Content-Type': 'application/json',
        },
      });

      if (response.status === 200) {
        // Handle successful update, e.g., show a success message or redirect
        console.log('Account holder updated successfully');
      } else {
        // Handle update failure
        console.error('Update failed');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div className='container mt-5 my-5'>
      <div className="row">
        <div className="col-md-12 text-center">
          <h1>Update Account Holder</h1>
        </div>
      </div>
      <div className="row">
        <div className="col-md-7 mx-auto">
          <div className="mb-3">
            <label htmlFor="searchUsername" className="form-label">Search by Username</label>
            <input
              type="text"
              className="form-control"
              id="searchUsername"
              name="searchUsername"
              value={username}
              onChange={handleChange}
            />
            <button className="btn btn-primary mt-2" onClick={handleSearch}>Search</button>
          </div>
          <form onSubmit={handleSubmit}>
            {/* Render form fields here based on the found account holder data */}
            {/* Include 'id', 'username', and 'roles' as disabled fields */}
            <div className="mb-3">
              <label htmlFor="id" className="form-label">ID</label>
              <input
                type="text"
                className="form-control"
                id="id"
                name="id"
                value={accountHolder.id || ''} // Initialize with an empty string if accountHolder.id is null or undefined
                disabled
              />
            </div>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">Username</label>
              <input
                type="text"
                className="form-control"
                id="username"
                name="username"
                value={accountHolder.username}
                disabled // This disables the input field
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">Email address</label>
              <input
                type="email"
                className="form-control"
                id="email"
                name="email"
                value={accountHolder.email}
                onChange={(e) => setAccountHolder({ ...accountHolder, email: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="roles" className="form-label">Roles</label>
              <input
                type="text"
                className="form-control"
                id="roles"
                name="roles"
                value={accountHolder.roles}
                disabled // This disables the input field
              />
            </div>
            <div className="mb-3">
              <label htmlFor="address" className="form-label">Address</label>
              <textarea
                className="form-control"
                id="address"
                name="address"
                rows="3"
                value={accountHolder.address}
                onChange={(e) => setAccountHolder({ ...accountHolder, address: e.target.value })}
              ></textarea>
            </div>
            <button type="submit" className="btn btn-primary">Update</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default UpdateAccountHolder;
