import React, { useState } from 'react';
import axios from 'axios';

import {toast} from 'react-toastify';

const TransactionForm = () => {
  const [transactionType, setTransactionType] = useState('deposit');
  const [amount, setAmount] = useState('');


  const handleSubmit = async (e) => {
    e.preventDefault();

    // Define the API endpoint based on the selected transaction type
    const apiUrl =
      transactionType === 'deposit'
        ? '/api/v1/transactions/deposit'
        : '/api/v1/transactions/withdraw';

    try {
      const response = await axios.post(
        apiUrl,
        { amount },
      );

      if (response.status === 200) {
        toast.success(`Successfully ${transactionType}ed $${amount}`);
      } else {
        toast.error(`Failed to ${transactionType} the transaction`);
      }
    } catch (error) {
      toast.error(`Failed to process this transaction`)
    }
  };

  return (
    <div className="container mt-5 my-5">
      <div className="row">
        <div className="col-md-6 offset-md-3">
          <h2>Transaction</h2>
          <form onSubmit={handleSubmit}>
            <div className="form-group my-2">
              <label htmlFor="transactionType">Select Transaction Type:</label>
              <select
                id="transactionType"
                className="form-control"
                value={transactionType}
                onChange={(e) => setTransactionType(e.target.value)}
              >
                <option value="deposit">Deposit</option>
                <option value="withdraw">Withdraw</option>
              </select>
            </div>
            <div className="form-group">
              <label htmlFor="amount">Amount:</label>
              <input
                type="number"
                className="form-control"
                id="amount"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
                required
              />
            </div>
            <button type="submit" className="btn btn-primary my-2">
              Submit
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default TransactionForm;
