import React from 'react'
import { Routes, Route } from 'react-router-dom'
import TransferMoney from '../TransferMoney/TransferMoney';
import BalanceHistory from '../BalanceHistory/BalanceHistory';
import TransactionHistory from '../TransactionHistory/TransactionHistory';
import UserHomePage from '../UserHomePage/UserHomePage';
import Transaction from '../Transaction/Transaction';
import Login from '../../Login/Login';

const UserPanel = () => {
    return (
        <Routes>
            <Route path="/" element={<UserHomePage />} />
            <Route path="/transMoney" element={<TransferMoney />} />
            <Route path="/balHistory" element={<BalanceHistory />} />
            <Route path="/transHistory" element={<TransactionHistory />} />
            <Route path = "/transaction" element = {<Transaction />} />
            <Route path = "/login" element = {<Login />} />
        </Routes>
    )
}

export default UserPanel
