import React from 'react'

const AddAccountHolder = () => {
  return (
    <div className='container mt-5 my-5'>
      <div className="row">
        <div className="col-md-12 text-center">
          <h1>Add Account Holder</h1>
        </div>
      </div>
      <div className="row">
        <div className="col-md-7 mx-auto">
          <form>
            <div className="mb-3">
              <label htmlFor="username" className="form-label">Username</label>
              <input type="text" className="form-control" id="username" />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">Email address</label>
              <input type="email" className="form-control" id="email" />
            </div>
            <div className="mb-3">
              <label htmlFor="password" className="form-label">Password</label>
              <input type="password" className="form-control" id="password" />
            </div>
            <div className="form-check">
              <input className="form-check-input" type="radio" name="role" id="admin" />
              <label className="form-check-label" htmlFor="admin">
                Admin
              </label>
            </div>
            <div className="form-check">
              <input className="form-check-input" type="radio" name="role" id="user" checked />
              <label className="form-check-label" htmlFor="user">
                User
              </label>
            </div>
            <div className="mb-3">
              <label htmlFor="address" className="form-label">Address</label>
              <textarea className="form-control" id="address" rows="3"></textarea>
            </div>
            <button type="submit" className="btn btn-primary">Add</button>
          </form>
        </div>
      </div>
    </div>
  )
}

export default AddAccountHolder
