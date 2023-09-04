import React from 'react'

const UserHomePage = () => {
  return (
    <div className='container my-3'>
        <h1>Welcome {"{Username here}"}</h1>


        <label>Your current balance: </label>

        <input class="form-control" type="text" value="100000" readonly></input>
    </div>
  )
}

export default UserHomePage
