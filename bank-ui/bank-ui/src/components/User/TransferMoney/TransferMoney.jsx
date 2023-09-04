import React, { useState } from 'react';

const TransferMoney = () => {
  const [recipient, setRecipient] = useState('');
  const [amount, setAmount] = useState('');

  const handleSend = () => {
    // Handle the send transaction logic here
    // You can send the transaction details to the server or update the balance locally
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h2 className="card-title">Send Money</h2>
              <form>
                <div className="mb-3">
                  <label htmlFor="recipient" className="form-label">
                    Recipient Account
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="recipient"
                    placeholder="Enter recipient's account"
                    value={recipient}
                    onChange={(e) => setRecipient(e.target.value)}
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="amount" className="form-label">
                    Amount to Send
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    id="amount"
                    placeholder="Enter amount"
                    value={amount}
                    onChange={(e) => setAmount(e.target.value)}
                  />
                </div>
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={handleSend}
                >
                  Send
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default TransferMoney;
