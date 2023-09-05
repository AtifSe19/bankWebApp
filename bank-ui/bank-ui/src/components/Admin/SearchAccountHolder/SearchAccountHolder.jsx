import React, { useState } from 'react';

const AdminPanel = () => {
  const [searchInput, setSearchInput] = useState('');
  const [accounts, setAccounts] = useState([
    { id: 1, name: 'atif', email: 'atif@example.com', balance: '$1000' },
    { id: 2, name: 'taimoor', email: 'taimoor@example.com', balance: '$1500' },
    // Add more accounts as needed
  ]);

  const handleSearchInputChange = (event) => {
    setSearchInput(event.target.value);
  }

  const handleSearch = () => {
    // Filter accounts based on the search input
    const filteredAccounts = accounts.filter(account => account.id.toString().includes(searchInput));
    setAccounts(filteredAccounts);
  }

  return (
    <div className="container mt-5 my-5">
      <div className="row">
        <div className="col-md-12 text-center">
          <h1>Search Account Holder</h1>
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search by Account Holder ID"
              value={searchInput}
              onChange={handleSearchInputChange}
            />
            <div className="input-group-append">
              <button className="btn btn-primary" onClick={handleSearch}>Search</button>
            </div>
          </div>
        </div>
      </div>
      <div className="row">
        <div className="col-md-12">
          <table className="table shadow">
            <thead>
              <tr>
                <th>#</th>
                <th>Username</th>
                <th>Email</th>
                <th>Balance</th>
              </tr>
            </thead>
            <tbody className='table-group-divider'>
              {accounts.map(account => (
                <tr key={account.id}>
                  <td>{account.id}</td>
                  <td>{account.name}</td>
                  <td>{account.email}</td>
                  <td>{account.balance}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default AdminPanel;
