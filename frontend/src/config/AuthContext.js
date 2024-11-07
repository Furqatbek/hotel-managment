import React, { createContext, useState, useEffect } from 'react';

// Create context
export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    // Check if the user is logged in when the app starts (check credentials in localStorage)
    const authData = localStorage.getItem('authData');
    if (authData) {
      setIsAuthenticated(true);
    }
  }, []);

  const login = (username, password) => {
    const credentials = btoa(`${username}:${password}`); // Base64 encode the username and password
    localStorage.setItem('authData', credentials); // Save credentials to localStorage
    setIsAuthenticated(true);
  };

  const logout = () => {
    localStorage.removeItem('authData'); // Remove credentials on logout
    setIsAuthenticated(false);
  };

  const getAuthHeader = () => {
    const authData = localStorage.getItem('authData');
    if (authData) {
      return {
        Authorization: `Basic ${authData}`, // Add the Authorization header
      };
    }
    return {};
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, login, logout, getAuthHeader }}>
      {children}
    </AuthContext.Provider>
  );
};
