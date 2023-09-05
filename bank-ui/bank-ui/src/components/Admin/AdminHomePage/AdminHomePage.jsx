import React from 'react';

const AdminHomePage = () => {
  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-md-6 offset-md-3 text-center">
          <h1>Welcome, Admin!</h1>
          <p>This is the admin dashboard of the Bank Application.</p>
          <p>Here, you can manage user accounts, transactions, and more.</p>
          <button className="btn btn-primary">Welcome!</button>
        </div>
      </div>
    </div>
  );
};

export default AdminHomePage;
