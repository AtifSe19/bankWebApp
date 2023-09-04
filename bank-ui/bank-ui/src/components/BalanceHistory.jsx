import React from 'react'

const BalanceHistory = () => {
  return (
    <div className='container my-3'>
    <h1> Your Balance History till {"{Date here}"}</h1>

    <table class="table">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Amount</th>
                <th scope="col">DB/CR</th>
                <th scope="col">Date</th>
            </tr>
        </thead>
        <tbody class="table-group-divider shadow">
            <tr>
                <th scope="row">1</th>
                <td>45000</td>
                <td>DB</td>
                <td>2023-12-12</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>15000</td>
                <td>CR</td>
                <td>2022-09-08</td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td>6000</td>
                <td>DB</td>
                <td>2023-11-12</td>
            </tr>
        </tbody>
    </table>
</div>
  )
}

export default BalanceHistory
