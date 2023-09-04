import React, { useEffect, useState } from 'react';
import axios from 'axios';

const BalanceHistory = ({ username = "atif" }) => {
  const [balanceHistory, setBalanceHistory] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:9080/api/v1/balance/history/${username}`);

        if (response.status === 200) {
          // Process and format the date
          const formattedData = response.data.map((entry) => ({
            ...entry,
            formattedDate: formatDate(entry.date), // Format the date here
          }));
          setBalanceHistory(formattedData);
        } else {
          console.error('Failed to fetch balance history');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchData();
  }, [username]);

  // Function to format the timestamp
  const formatDate = (dateArray) => {
    if (!Array.isArray(dateArray) || dateArray.length !== 7) {
      return 'Invalid Date';
    }

    // Extract date components from the array
    const [year, month, day, hour, minute, second] = dateArray;

    // Create a Date object with the extracted components
    const date = new Date(year, month - 1, day, hour, minute, second);

    // Format the date as desired (e.g., YYYY-MM-DD HH:mm:ss)
    const formattedDate = date.toISOString().slice(0, 19).replace('T', ' ');

    return formattedDate;
  };

  return (
    <div className="container mt-5 my-3 text-center">
      <div className="row">
        <div className="col-md-12">
          <h1>Balance History</h1>
        </div>
      </div>
      <table className="table shadow">
        <thead>
          <tr>
            <th>#</th>
            <th>Amount</th>
            <th>DB/CR</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody className='table-group-divider'>
          {balanceHistory.map((entry, index) => (
            <tr key={index}>
              <td>{index + 1}</td>
              <td>{entry.amount}</td>
              <td>{entry.db_cr_indicator}</td>
              <td>{entry.formattedDate}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>

  );
};

export default BalanceHistory;