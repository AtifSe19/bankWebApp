// import React, { useState } from 'react';
// import axios from 'axios';
// import './Login.css';

// function Login() {
//   const [username, setUsername] = useState('');
//   const [password, setPassword] = useState('');

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     // Send a POST request to the Spring Boot backend with username and password
//     const response = axios.post('http://localhost:9080/login', {
//       method: 'POST',
//       headers: {
//         'Content-Type': 'application/json',
//       },
//       body: JSON.stringify({ username, password }),
//     });

//     if (response.ok) {
//       console.log("Successful login")
//     } else {
//       // Handle login failure
//     }
//   };

//   return (
//     <div>
//       <h2>Login</h2>
//       <form onSubmit={handleSubmit}>
//         <input
//           type="text"
//           placeholder="Username"
//           value={username}
//           onChange={(e) => setUsername(e.target.value)}
//         />
//         <input
//           type="password"
//           placeholder="Password"
//           value={password}
//           onChange={(e) => setPassword(e.target.value)}
//         />
//         <button type="submit">Login</button>
//       </form>
//     </div>

//     // <div class="container-fluid">
//     //   <div class="row main-content bg-success text-center">
//     //     <div class="col-md-4 text-center company__info">
//     //       <span class="company__logo"><h2><span class="fa fa-bank"></span></h2></span>
//     //       <h4 class="company_title">Secure Bank</h4>
//     //     </div>
//     //     <div class="col-md-8 col-xs-12 col-sm-12 login_form ">
//     //       <div class="container-fluid">
//     //         <div class="row">
//     //           <h2>Log In</h2>
//     //         </div>
//     //         <div class="row">
//     //           <form control="" class="form-group">
//     //             <div class="row">
//     //               <input type="text" name="username" id="username" class="form__input" placeholder="Username" />
//     //             </div>
//     //             <div class="row">
//     //                {/* <span class="fa fa-lock"></span> */}
//     //               <input type="password" name="password" id="password" class="form__input" placeholder="Password" />
//     //             </div>
//     //             <div class="row">
//     //               <input type="checkbox" name="remember_me" id="remember_me" class="" />
//     //               <label for="remember_me">Remember Me!</label>
//     //             </div>
//     //             <div class="row">
//     //               <input type="submit" value="Submit" class="buttn btn" />
//     //             </div>
//     //           </form>
//     //         </div>
//     //         <div class="row">
//     //           <p>Don't have an account? <a href="#">Register Here</a></p>
//     //         </div>
//     //       </div>
//     //     </div>
//     //   </div>
//     // </div>
//   );
// }

// export default Login;
