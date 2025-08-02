import React, { useEffect, useState } from 'react';
import { experimentAPI } from '../services/api';
import { useSearch, useExperimentEvents } from '../context/SearchContext';
import { Card, Row, Col, Badge, Button, Alert } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const Dashboard = () => {
  const [experiments, setExperiments] = useState([]); // Ensure initial state is array
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [deletingId, setDeletingId] = useState(null);
  const { searchTerm } = useSearch();
  const { refreshCount } = useExperimentEvents();

  // Debug: log the current search term
  console.log('Dashboard searchTerm:', searchTerm);
  console.log('Dashboard refreshCount:', refreshCount);

  const fetchExperiments = async () => {
    setLoading(true);
    setError('');
    try {
      console.log('Fetching experiments...');
      const response = await experimentAPI.getAll();
      console.log('API Response:', response);
      const data = response.data;
      console.log('Experiments data:', data);
      console.log('Is data array?', Array.isArray(data));
      console.log('Number of experiments:', data ? data.length : 0);
      
      setExperiments(Array.isArray(data) ? data : []); // Defensive: always set array
      setLoading(false);
    } catch (err) {
      console.error('Error fetching experiments:', err);
      setExperiments([]); // Set to empty array on error
      setError('Failed to fetch experiments: ' + (err.message || 'Unknown error'));
      setLoading(false);
    }
  };

  useEffect(() => {
    console.log('Dashboard useEffect triggered, refreshCount:', refreshCount);
    fetchExperiments();
    // eslint-disable-next-line
  }, [refreshCount]);

  // Debug: log experiments state
  console.log('Current experiments state:', experiments);
  console.log('Number of experiments in state:', experiments.length);

  // Defensive check before filtering
  const filteredExperiments = Array.isArray(experiments)
    ? experiments.filter(exp => {
        const matches = exp.title && exp.title.toLowerCase().includes(searchTerm.toLowerCase());
        console.log(`Experiment "${exp.title}" matches search "${searchTerm}":`, matches);
        return matches;
      })
    : [];

  console.log('Filtered experiments:', filteredExperiments);
  console.log('Number of filtered experiments:', filteredExperiments.length);

  const handleDelete = async (id) => {
    setDeletingId(id);
    setError('');
    try {
      await experimentAPI.delete(id);
      fetchExperiments();
    } catch (err) {
      setError('Failed to delete experiment');
    } finally {
      setDeletingId(null);
    }
  };

  if (loading) return (
    <div className="dashboard-container">
      <div className="dashboard-content">
        <div className="text-center">
          <h3 style={{ color: '#fff', textShadow: '2px 2px 4px rgba(0,0,0,0.5)' }}>Loading experiments...</h3>
        </div>
      </div>
    </div>
  );
  
  if (error) return (
    <div className="dashboard-container">
      <div className="dashboard-content">
        <Alert variant="danger">{error}</Alert>
      </div>
    </div>
  );

  const getStatusBadge = (status) => {
    const variants = {
      DRAFT: 'secondary',
      IN_PROGRESS: 'warning',
      COMPLETED: 'success',
      SUBMITTED: 'info',
      APPROVED: 'primary',
      REJECTED: 'danger'
    };
    return <Badge bg={variants[status] || 'secondary'}>{status}</Badge>;
  };

  return (
    <div className="dashboard-container">
      <div className="dashboard-content">
        <h2 className="dashboard-title mb-4">Experiments ({filteredExperiments.length})</h2>
        
        {filteredExperiments.length === 0 && !loading && (
          <Alert variant="info" className="dashboard-alert">
            No experiments found. {searchTerm ? `No experiments match "${searchTerm}"` : 'Create your first experiment!'}
          </Alert>
        )}
        
        <Row xs={1} md={2} lg={3} className="g-4">
          {filteredExperiments.map(exp => (
            <Col key={exp.id}>
              <Card className="dashboard-card h-100 shadow-lg border-0">
                <Card.Body>
                  <div className="d-flex justify-content-between align-items-center mb-2">
                    <Card.Title className="fw-bold dashboard-card-title">{exp.title}</Card.Title>
                    {getStatusBadge(exp.status)}
                  </div>
                  <Card.Text className="dashboard-card-text">
                    <strong>Date:</strong> {exp.experimentDate ? new Date(exp.experimentDate).toLocaleDateString() : 'N/A'}
                  </Card.Text>
                  <div className="d-flex justify-content-end gap-2 mt-3">
                    <Button
                      as={Link}
                      to={`/experiment/${exp.id}/edit`}
                      variant="outline-primary"
                      size="sm"
                      className="dashboard-btn"
                    >
                      Edit
                    </Button>
                    <Button
                      onClick={() => handleDelete(exp.id)}
                      disabled={deletingId === exp.id}
                      variant="outline-danger"
                      size="sm"
                      className="dashboard-btn"
                    >
                      {deletingId === exp.id ? 'Deleting...' : 'Delete'}
                    </Button>
                  </div>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      </div>
    </div>
  );
};

export default Dashboard; 