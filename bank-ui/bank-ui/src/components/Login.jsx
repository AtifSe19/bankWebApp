import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
const Login = () => {
    return (
        <div className="container">
        <form>
            <h1>Login</h1>
            <div class="mb-3">
                <label for="username" class="form-label">Username </label>
                <input type="text" class="form-control" id="username" />
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" />
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
    )
}

export default Login
