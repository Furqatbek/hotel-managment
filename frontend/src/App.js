import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Sidebar from './components/Sidebar';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import Clients from './pages/Clients';
import BookingPage from './pages/BookingPage';
import { AuthProvider } from "./config/AuthContext";
import ProtectedRoute from "./components/ProtectedRoute";

const AppLayout = ({ children }) => {
  return (
    <div style={{ display: 'flex' }}>
      <Sidebar />
      <main style={{ flex: 1, padding: '20px' }}>
        {children}
      </main>
    </div>
  );
};

const App = () => (
  <AuthProvider>
    <Router>
      <Routes> {/* Wrap Routes inside the Routes component */}
        <Route path="/" element={<LoginPage />} />
        {/* Wrap protected routes with ProtectedRoute */}
        <Route path="/home" element={<ProtectedRoute><AppLayout><HomePage /></AppLayout></ProtectedRoute>} />
        <Route path="/clients" element={<ProtectedRoute><AppLayout><Clients /></AppLayout></ProtectedRoute>} />
        <Route path="/booking" element={<ProtectedRoute><AppLayout><BookingPage /></AppLayout></ProtectedRoute>} />
      </Routes>
    </Router>
  </AuthProvider>
);

export default App;
