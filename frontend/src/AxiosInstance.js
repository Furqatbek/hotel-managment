// src/axiosInstance.js

import axios from 'axios';

// Create an axios instance
const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080', // Change to your backend URL
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor to log request details
axiosInstance.interceptors.request.use(
  (request) => {
    console.log('Request:', request);
    return request;
  },
  (error) => {
    console.error('Request Error:', error);
    return Promise.reject(error);
  }
);

// Response interceptor to log response details
axiosInstance.interceptors.response.use(
  (response) => {
    console.log('Response:', response);
    return response;
  },
  (error) => {
    console.error('Response Error:', error);
    return Promise.reject(error);
  }
);

export default axiosInstance;
