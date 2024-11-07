import React, { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { AuthContext } from '../config/AuthContext';

const ProtectedRoute = ({ children }) => {
  const { isAuthenticated } = useContext(AuthContext);

  if (!isAuthenticated) {
    return <Navigate to="/" replace />; // Redirect to login page if not authenticated
  }

  return children; // Render the protected route if authenticated
};

export default ProtectedRoute;
