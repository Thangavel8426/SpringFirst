import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate, Outlet } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-toastify/dist/ReactToastify.css';
import './styles/App.css'; // Import global styles
import { ToastContainer } from 'react-toastify';

import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import ExperimentForm from './pages/ExperimentForm';
import ExperimentDetail from './pages/ExperimentDetail';
import Navbar from './components/Navbar';
import { AuthProvider, useAuth } from './context/AuthContext';
import Register from './pages/Register';
import Profile from './pages/Profile';
import EditProfile from './pages/EditProfile';
import { SearchProvider, ExperimentProvider } from './context/SearchContext';

function PrivateRoute({ children }) {
  const { isAuthenticated } = useAuth();
  return isAuthenticated ? children : <Navigate to="/login" />;
}

// This layout will be used for all pages that require authentication
// and the standard navbar-container structure.
const MainLayout = () => (
  <>
    <Navbar />
    <Container className="mt-4">
      <Outlet />
    </Container>
  </>
);

function App() {
  return (
    <ExperimentProvider>
      <AuthProvider>
        <SearchProvider>
          <Router future={{ v7_startTransition: true, v7_relativeSplatPath: true }}>
            <div className="App">
              <ToastContainer position="top-right" />
              <Routes>
                {/* Public routes - no authentication required */}
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />

                {/* Application routes use the MainLayout, wrapped in PrivateRoute */}
                <Route element={<PrivateRoute><MainLayout /></PrivateRoute>}>
                  <Route path="/" element={<Dashboard />} />
                  <Route path="/dashboard" element={<Dashboard />} />
                  <Route path="/profile" element={<Profile />} />
                  <Route path="/edit-profile" element={<EditProfile />} />
                  <Route path="/experiment/new" element={<ExperimentForm />} />
                  <Route path="/experiment/:id" element={<ExperimentDetail />} />
                  <Route path="/experiment/:id/edit" element={<ExperimentForm />} />
                </Route>
              </Routes>
            </div>
          </Router>
        </SearchProvider>
      </AuthProvider>
    </ExperimentProvider>
  );
}

export default App; 