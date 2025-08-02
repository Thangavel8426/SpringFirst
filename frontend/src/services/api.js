import axios from 'axios';
import { jwtDecode } from 'jwt-decode';

const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add token to requests if available
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    // Validate token before sending request
    try {
      const decoded = jwtDecode(token);
      const currentTime = Date.now() / 1000;
      
      if (decoded.exp && decoded.exp < currentTime) {
        console.log('Token expired, removing from localStorage');
        localStorage.removeItem('token');
        window.location.href = '/login';
        return Promise.reject('Token expired');
      }
      
      config.headers.Authorization = `Bearer ${token}`;
    } catch (error) {
      console.error('Token validation error:', error);
      localStorage.removeItem('token');
      window.location.href = '/login';
      return Promise.reject('Invalid token');
    }
  }
  return config;
}, (error) => {
  return Promise.reject(error);
});

// Handle response errors
api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response) {
      // Handle 401 Unauthorized errors
      if (error.response.status === 401) {
        console.log('Unauthorized access, logging out');
        localStorage.removeItem('token');
        window.location.href = '/login';
      }
      
      // Handle 403 Forbidden errors
      if (error.response.status === 403) {
        console.log('Access forbidden');
      }
    }
    return Promise.reject(error);
  }
);

export const authAPI = {
  login: (credentials) => api.post('/auth/login', credentials),
  register: (userData) => api.post('/auth/register', userData),
};

export const experimentAPI = {
  getAll: () => api.get('/experiments'),
  getById: (id) => api.get(`/experiments/${id}`),
  create: (experimentData) => api.post('/experiments', experimentData),
  update: (id, experimentData) => api.put(`/experiments/${id}`, experimentData),
  delete: (id) => api.delete(`/experiments/${id}`),
  updateStatus: (id, status) => api.put(`/experiments/${id}/status`, null, { params: { status } }),
  search: (keyword, page = 0, size = 10) => api.get('/experiments/search', { params: { keyword, page, size } }),
  getByUser: (userId) => api.get(`/experiments/user/${userId}`),
  getByStatus: (status) => api.get(`/experiments/status/${status}`),
};

export const userAPI = {
  getAll: () => api.get('/users'),
  getById: (id) => api.get(`/users/${id}`),
  create: (userData) => api.post('/users', userData),
  update: (id, userData) => api.put(`/users/${id}`, userData),
  delete: (id) => api.delete(`/users/${id}`),
  checkUsername: (username) => api.get(`/users/check-username/${username}`),
  checkEmail: (email) => api.get(`/users/check-email/${email}`),
  uploadProfilePicture: (username, fileData) => api.post(`/profile-pictures/${username}`, fileData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
  }),
};

export default api; 