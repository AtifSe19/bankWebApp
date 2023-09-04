import React from 'react'

const DeleteUser = () => {
  return (
    <div className="container">
    <form>
        <h1>Delete Account Holder</h1>
        <div class="mb-3">
            <label for="id" class="form-label">Enter Account Holder Id </label>
            <input type="number" class="form-control" id="id" />
        </div>
        <button type="submit" class="btn btn-danger">Delete</button>
    </form>
</div>
  )
}

export default DeleteUser
