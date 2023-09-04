import React, { useState } from 'react';
import axios from 'axios';

const DeleteAccountHolder = () => {
  const [username, setUsername] = useState('');

  const handleChange = (e) => {
    setUsername(e.target.value);
  };

  const handleDelete = async (e) => {
    e.preventDefault();
    try {
      // Send a DELETE request to delete the account holder by username
      const response = await axios.delete(`http://localhost:9080/api/v1/accounts/delete/${username}`, {
        withCredentials: true,
        headers: {
          'Authorization': 'Basic ' + btoa('admin:admin'),
        },
      });

      if (response.status === 200) {
        // Handle successful deletion, e.g., show a success message or redirect
        console.log('Account holder deleted successfully');
        // Optionally, you can reset the form or clear the account holder data here
      } else {
        // Handle deletion failure
        console.error('Deletion failed');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div className='container mt-5 my-5'>
      <div className="row">
        <div className="col-md-12 text-center">
          <h1>Delete Account Holder</h1>
        </div>
      </div>
      <div className="row">
        <div className="col-md-7 mx-auto">

          <form onSubmit={handleDelete}>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">Account Holder Username</label>
              <input type="text" className="form-control" id="username" value={username}
                onChange={handleChange} />
            </div>
            <button type="submit" className="btn btn-danger">Delete</button>
          </form>

        </div>
      </div>
    </div>
  )
}

export default DeleteAccountHolder
