import React, { useState } from 'react';
import { Card, Form, Button, Alert } from 'react-bootstrap';
import { useNavigate, Link } from 'react-router-dom';
import { authAPI } from '../services/api';
import '../styles/AnimatedLogin.css'; // Import the new CSS

const Register = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    role: 'STUDENT'
  });
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    setLoading(true);
    
    console.log('Attempting registration with data:', { ...formData, password: '[HIDDEN]' });
    
    try {
      const response = await authAPI.register(formData);
      console.log('Registration successful:', response.data);
      setSuccess('Registration successful! Redirecting to login...');
      setTimeout(() => navigate('/login'), 2000);
    } catch (err) {
      console.error('Registration error:', err);
      console.error('Error response:', err.response);
      
      let errorMessage = 'Registration failed. Please check your details and try again.';
      
      if (err.response) {
        // Server responded with error
        if (err.response.status === 400) {
          errorMessage = err.response.data || 'Invalid registration data.';
        } else if (err.response.status === 409) {
          errorMessage = 'Username or email already exists.';
        } else if (err.response.status === 500) {
          errorMessage = 'Server error. Please try again later.';
        } else if (err.response.data) {
          errorMessage = err.response.data || 'Registration failed.';
        }
      } else if (err.request) {
        // Network error
        errorMessage = 'Network error. Please check your connection.';
      } else {
        // Other error
        errorMessage = err.message || 'Registration failed.';
      }
      
      setError(errorMessage);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="animated-container">
      <div className="top"></div>
      <div className="bottom"></div>
      <div className="center">
        <Card style={{ width: '100%', border: 'none', background: 'transparent' }}>
          <Card.Header as="h2" className="text-center" style={{ border: 'none', background: 'transparent' }}>
            Create Account
          </Card.Header>
          <Card.Body>
            {success && <Alert variant="success">{success}</Alert>}
            {error && <Alert variant="danger">{error}</Alert>}
            <Form onSubmit={handleSubmit}>
              {/* Form fields */}
              <Form.Group className="mb-2" controlId="username">
                <Form.Control 
                  type="text" 
                  name="username" 
                  placeholder="Username" 
                  value={formData.username} 
                  onChange={handleChange} 
                  required 
                  disabled={loading}
                />
              </Form.Group>
              <Form.Group className="mb-2" controlId="email">
                <Form.Control 
                  type="email" 
                  name="email" 
                  placeholder="Email" 
                  value={formData.email} 
                  onChange={handleChange} 
                  required 
                  disabled={loading}
                />
              </Form.Group>
              <Form.Group className="mb-2" controlId="password">
                <Form.Control 
                  type="password" 
                  name="password" 
                  placeholder="Password" 
                  value={formData.password} 
                  onChange={handleChange} 
                  required 
                  disabled={loading}
                />
              </Form.Group>
              <Form.Group className="mb-2" controlId="firstName">
                <Form.Control 
                  type="text" 
                  name="firstName" 
                  placeholder="First Name" 
                  value={formData.firstName} 
                  onChange={handleChange} 
                  required 
                  disabled={loading}
                />
              </Form.Group>
              <Form.Group className="mb-2" controlId="lastName">
                <Form.Control 
                  type="text" 
                  name="lastName" 
                  placeholder="Last Name" 
                  value={formData.lastName} 
                  onChange={handleChange} 
                  required 
                  disabled={loading}
                />
              </Form.Group>
              <Button variant="primary" type="submit" className="w-100 mt-2" disabled={loading}>
                {loading ? 'Creating Account...' : 'Sign Up'}
              </Button>
            </Form>
          </Card.Body>
          <Card.Footer className="text-center" style={{ border: 'none', background: 'transparent' }}>
            Already have an account? <Link to="/login">Sign In</Link>
          </Card.Footer>
        </Card>
      </div>
    </div>
  );
};

export default Register; 