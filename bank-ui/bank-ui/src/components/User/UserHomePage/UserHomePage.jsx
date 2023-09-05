import React from 'react';

const UserHomePage = () => {
  return (
    <div className="container mt-5">
      <div className="row">
        <div className="col-md-6 offset-md-3 text-center">
          <h1>Welcome, {'{Username here}'}!</h1>
          <p>This is the account holder dashboard of the Bank Application.</p>
          <p>Here, you can manage your account, see balance, transactions history, and more.</p>
          <button className="btn btn-primary">Welcome!</button>
        </div>
      </div>
    </div>
  );
};

export default UserHomePage;
