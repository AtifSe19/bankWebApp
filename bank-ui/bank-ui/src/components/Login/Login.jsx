import React, { useState } from 'react';
import axios from 'axios';

function Login() {
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async(e) => {
        e.preventDefault();
        // Send the login request to your Spring Security backend using Axios
        const { username, password } = formData;
        const credentials = { username, password };
        console.log(credentials.username, credentials.password);

        // Replace 'YOUR_API_ENDPOINT' with the actual endpoint for authentication
        await axios.post('http://localhost:9080/login', credentials)
            .then(response => { 
                if (response.status === 200) {
                    // Redirect to the authenticated area or perform any other actions
                    // history.push('/adminHomePage');
                } else {
                    // Handle authentication failure, display an error message, etc.
                }
            })
            .catch(error => {
                // Handle API request errors (e.g., network issues, server errors)
                console.error('API request error:', error);
            });
    };

    return (
        <div className="login-container">
            <h1>Login</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="username">Username</label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        value={formData.username}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </div>
                <button type="submit">Login</button>
            </form>
        </div>
    );
}

export default Login;
