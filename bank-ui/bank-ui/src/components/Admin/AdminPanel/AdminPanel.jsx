import React from 'react'
import { Routes, Route } from 'react-router-dom'
import AddAccountHolder from '../AddAccountHolder/AddAccountHolder';
import DeleteAccountHolder from '../DeleteAccountHolder/DeleteAccountHolder';
import UpdateAccountHolder from '../UpdateAccountHolder/UpdateAccountHolder';
import SearchAccountHolder from '../SearchAccountHolder/SearchAccountHolder';
import AdminHomePage from '../AdminHomePage/AdminHomePage';

const AdminPanel = () => {
    return (
        <Routes>
            <Route path="/" element={<AdminHomePage />} />
            <Route path="/addAccHolder" element={<AddAccountHolder />} />
            <Route path="/delAccHolder" element={<DeleteAccountHolder />} />
            <Route path="/updAccHolder" element={<UpdateAccountHolder />} />
            <Route path="/searchAccHolder" element={<SearchAccountHolder />} />
        </Routes>
    )
}

export default AdminPanel
