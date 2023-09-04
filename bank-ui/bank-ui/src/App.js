import React from 'react';
import Login from './components/Login';
import CreateUser from './components/CreateUser';
import DeleteUser from './components/DeleteUser';
import UserHomePage from './components/UserHomePage';
import ShowTransactionHistory from './components/ShowTransactionHistory';
import BalanceHistory from './components/BalanceHistory';
import Navbar from './components/Navbar';
import UserNavBar from './components/UserNavBar';
import Footer from './components/Footer';
import { Link } from 'react-router-dom'
import Deposit from './components/Deposit';
import Withdraw from './components/Withdraw';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <Router>
        <div className="App">
        <UserNavBar />
        <UserHomePage />
        <Routes>
          <Route path="/" component={Login} />
          <Route path="/createUser" component={CreateUser} />
          <Route path="/deleteUser" component={DeleteUser} />
          <Route path="/userHomePage" component={UserHomePage} />
          <Route path="/showTransactionHistory" component={ShowTransactionHistory} />
          <Route path="/balanceHistory" element={BalanceHistory} />
          <Route path="/deposit" component={Deposit} />
          <Route path="/withdraw" component={Withdraw} />
        </Routes>
        <Footer />
    </div>
      </Router>
  );
}

export default App;
