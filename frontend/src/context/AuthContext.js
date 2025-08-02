import React, { createContext, useContext, useState, useEffect, useCallback } from 'react';
import { authAPI } from '../services/api';
import { jwtDecode } from "jwt-decode";

const AuthContext = createContext();

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(localStorage.getItem('token'));
  const [loading, setLoading] = useState(true);

  const validateToken = useCallback((token) => {
    if (!token) return false;
    
    try {
      const decoded = jwtDecode(token);
      const currentTime = Date.now() / 1000;
      
      // Check if token is expired (with 5 minute buffer)
      if (decoded.exp && decoded.exp < currentTime + 300) {
        console.log('Token is expired or will expire soon');
        return false;
      }
      
      return true;
    } catch (error) {
      console.error('Token validation error:', error);
      return false;
    }
  }, []);

  const refreshUser = useCallback(() => {
    const storedToken = localStorage.getItem('token');
    console.log('Checking stored token:', storedToken ? 'exists' : 'not found');
    
    if (storedToken && validateToken(storedToken)) {
      try {
        const decodedUser = jwtDecode(storedToken);
        console.log('Token is valid, user:', decodedUser.sub);
        setUser(decodedUser);
        setToken(storedToken);
        setIsAuthenticated(true);
      } catch (error) {
        console.error('Token decoding error:', error);
        logout();
      }
    } else {
      console.log('No valid token found, user not authenticated');
      setIsAuthenticated(false);
      setUser(null);
      setToken(null);
    }
    setLoading(false);
  }, [validateToken]);

  useEffect(() => {
    refreshUser();
  }, [refreshUser]);

  // Set up periodic token validation
  useEffect(() => {
    if (isAuthenticated && token) {
      const interval = setInterval(() => {
        if (!validateToken(token)) {
          console.log('Token expired during session, logging out');
          logout();
        }
      }, 60000); // Check every minute

      return () => clearInterval(interval);
    }
  }, [isAuthenticated, token, validateToken]);

  const login = async (username, password) => {
    try {
      const response = await authAPI.login({ username, password });
      const { token: authToken } = response.data;
      
      if (!authToken) {
        throw new Error('No token received from server');
      }
      
      const decodedUser = jwtDecode(authToken);
      console.log('Login successful, user:', decodedUser.sub);
      
      setUser(decodedUser);
      setToken(authToken);
      setIsAuthenticated(true);
      localStorage.setItem('token', authToken);
    } catch (error) {
      console.error("Login failed:", error);
      throw error;
    }
  };

  const logout = () => {
    console.log('Logging out user');
    setUser(null);
    setToken(null);
    setIsAuthenticated(false);
    localStorage.removeItem('token');
  };

  const value = {
    isAuthenticated,
    user,
    token,
    login,
    logout,
    loading,
    refreshUser,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}; 