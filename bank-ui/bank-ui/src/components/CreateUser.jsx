import React from 'react'

const CreateUser = () => {
    return (
        <div className="container">
            <form>
                <h1>Add Account Holder</h1>
                <div class="mb-3">
                    <label for="username" class="form-label">Username </label>
                    <input type="text" class="form-control" id="username" />
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email </label>
                    <input type="email" class="form-control" id="email" />
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" />
                </div>

                {/* create a radio button for the role field. */}
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="role" id="admin" />
                    <label class="form-check-label" for="admin">
                        Admin
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="role" id="user" checked />
                    <label class="form-check-label" for="user">
                        User
                    </label>
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <textarea class="form-control" id="address" rows="3"></textarea>
                </div>
                <div className="my-3">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>
    )
}

export default CreateUser
