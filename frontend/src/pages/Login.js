import React, { useState } from 'react';
import { Form, Button, Alert, Card } from 'react-bootstrap';
import { useNavigate, Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import '../styles/AnimatedLogin.css'; // Import the new CSS

const Login = () => {
  const { login } = useAuth();
  const navigate = useNavigate();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);
    
    console.log('Attempting login with username:', username);
    
    try {
      await login(username, password);
      console.log('Login successful, navigating to dashboard');
      navigate('/');
    } catch (err) {
      console.error('Login error:', err);
      console.error('Error response:', err.response);
      
      let errorMessage = 'Invalid username or password.';
      
      if (err.response) {
        // Server responded with error
        if (err.response.status === 401) {
          errorMessage = 'Invalid username or password.';
        } else if (err.response.status === 500) {
          errorMessage = 'Server error. Please try again later.';
        } else if (err.response.data) {
          errorMessage = err.response.data || 'Login failed.';
        }
      } else if (err.request) {
        // Network error
        errorMessage = 'Network error. Please check your connection.';
      } else {
        // Other error
        errorMessage = err.message || 'Login failed.';
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
            Please Sign In
          </Card.Header>
          <Card.Body>
            {error && <Alert variant="danger">{error}</Alert>}
            <Form onSubmit={handleSubmit}>
              <Form.Group className="mb-3" controlId="formBasicUsername">
                <Form.Control
                  type="text"
                  placeholder="Username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
                  disabled={loading}
                />
              </Form.Group>

              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Control
                  type="password"
                  placeholder="Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                  disabled={loading}
                />
              </Form.Group>
              <Button variant="primary" type="submit" className="w-100" disabled={loading}>
                {loading ? 'Logging in...' : 'Login'}
              </Button>
            </Form>
            
          </Card.Body>
          <Card.Footer className="text-center" style={{ border: 'none', background: 'transparent' }}>
            Don't have an account? <Link to="/register">Sign Up</Link>
          </Card.Footer>
        </Card>
      </div>
    </div>
  );
};

export default Login; 