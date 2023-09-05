import React from 'react'

const DeleteAccountHolder = () => {
  return (
    <div className='container mt-5 my-5'>
      <div className="row">
        <div className="col-md-12 text-center">
          <h1>Delete Account Holder</h1>
        </div>
      </div>
      <div className="row">
        <div className="col-md-7 mx-auto">

          <form>
            <div className="mb-3">
              <label htmlFor="userId" className="form-label">Account Holder ID</label>
              <input type="number" className="form-control" id="userId" />
            </div>
            <button type="submit" className="btn btn-danger">Delete</button>
          </form>

        </div>
      </div>
    </div>
  )
}

export default DeleteAccountHolder
