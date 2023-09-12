import React, { useState } from 'react';
import axios from 'axios';
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';

const TransferMoney = () => {
  const [receiver, setReceiver] = useState('');
  const [amount, setAmount] = useState('');


  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        `/api/v1/transactions/transfer?receiver=${receiver}`,
        { amount },
        {
          withCredentials: true,
          headers: {
            'Authorization': 'Basic ' + btoa('admin:admin'),
            'Content-Type': 'application/json',
          },
        }
      );

      if (response.status === 200) {
        toast.success(`Successfully transferred $${amount} to @_${receiver}`);
      } else{
        toast.error('You cannot transfer money to yourself');
      }
    } catch (error) {
      toast.error(`Something went wrong!`);
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card">
            <div className="card-body">
              <h2 className="card-title">Send Money</h2>
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="recipient" className="form-label">
                    Recipient Account
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="receiver"
                    placeholder="Enter recipient's username"
                    value={receiver}
                    onChange={(e) => setReceiver(e.target.value)}
                    required
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
                    required
                  />
                </div>
                <button
                  type="submit"
                  className="btn btn-primary"
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
