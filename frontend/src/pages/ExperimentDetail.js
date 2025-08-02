import React, { useState, useEffect } from 'react';
import { Card, Row, Col, Button, Badge, Alert, Spinner } from 'react-bootstrap';
import { useParams, useNavigate, Link } from 'react-router-dom';
import { experimentAPI } from '../services/api';

const ExperimentDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [experiment, setExperiment] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchExperiment = async () => {
      try {
        setLoading(true);
        const response = await experimentAPI.getById(id);
        setExperiment(response.data);
      } catch (err) {
        setError('Failed to fetch experiment details.');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchExperiment();
  }, [id]);

  const handleDelete = async () => {
    if (window.confirm('Are you sure you want to delete this experiment?')) {
      try {
        await experimentAPI.delete(id);
        navigate('/');
      } catch (err) {
        setError('Failed to delete experiment.');
        console.error(err);
      }
    }
  };

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

  if (loading) {
    return (
      <div className="text-center">
        <Spinner animation="border" />
        <p>Loading experiment...</p>
      </div>
    );
  }

  if (error) {
    return <Alert variant="danger">{error}</Alert>;
  }
  
  if (!experiment) {
    return (
      <Alert variant="info">
        Experiment not found.
        <Button as={Link} to="/" variant="outline-primary" className="ms-3">
          Back to Dashboard
        </Button>
      </Alert>
    );
  }

  return (
    <div>
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2>🧪 Experiment Details</h2>
        <div>
          <Button
            as={Link}
            to={`/experiment/${id}/edit`}
            variant="primary"
            className="me-2"
          >
            ✏️ Edit
          </Button>
          <Button
            variant="danger"
            onClick={handleDelete}
          >
            🗑️ Delete
          </Button>
        </div>
      </div>

      <Card className="mb-4">
        <Card.Header>
          <div className="d-flex justify-content-between align-items-center">
            <h4>{experiment.title}</h4>
            {getStatusBadge(experiment.status)}
          </div>
        </Card.Header>
        <Card.Body>
          <Row>
            <Col md={8}>
              <p className="text-muted">
                <strong>Created:</strong> {new Date(experiment.createdAt).toLocaleDateString()}
                {experiment.updatedAt && new Date(experiment.updatedAt).getTime() !== new Date(experiment.createdAt).getTime() && (
                  <span className="ms-3">
                    <strong>Updated:</strong> {new Date(experiment.updatedAt).toLocaleDateString()}
                  </span>
                )}
              </p>
            </Col>
            <Col md={4} className="text-end">
              <small className="text-muted">
                By: {experiment.userFullName || 'Unknown User'}
              </small>
            </Col>
          </Row>
        </Card.Body>
      </Card>

      <Row>
        <Col lg={8}>
          <Card className="mb-4">
            <Card.Header>
              <h5>Objective</h5>
            </Card.Header>
            <Card.Body>
              <p>{experiment.objective || 'No objective specified.'}</p>
            </Card.Body>
          </Card>

          <Card className="mb-4">
            <Card.Header>
              <h5>Hypothesis</h5>
            </Card.Header>
            <Card.Body>
              <p>{experiment.hypothesis || 'No hypothesis specified.'}</p>
            </Card.Body>
          </Card>

          <Card className="mb-4">
            <Card.Header>
              <h5>Materials</h5>
            </Card.Header>
            <Card.Body>
              <p style={{ whiteSpace: 'pre-wrap' }}>
                {experiment.materials || 'No materials specified.'}
              </p>
            </Card.Body>
          </Card>

          <Card className="mb-4">
            <Card.Header>
              <h5>Procedure</h5>
            </Card.Header>
            <Card.Body>
              <p style={{ whiteSpace: 'pre-wrap' }}>
                {experiment.procedureSteps || 'No procedure specified.'}
              </p>
            </Card.Body>
          </Card>

          <Card className="mb-4">
            <Card.Header>
              <h5>Observations</h5>
            </Card.Header>
            <Card.Body>
              <p style={{ whiteSpace: 'pre-wrap' }}>
                {experiment.observations || 'No observations recorded.'}
              </p>
            </Card.Body>
          </Card>

          <Card className="mb-4">
            <Card.Header>
              <h5>Results</h5>
            </Card.Header>
            <Card.Body>
              <p style={{ whiteSpace: 'pre-wrap' }}>
                {experiment.results || 'No results recorded.'}
              </p>
            </Card.Body>
          </Card>

          <Card className="mb-4">
            <Card.Header>
              <h5>Conclusion</h5>
            </Card.Header>
            <Card.Body>
              <p style={{ whiteSpace: 'pre-wrap' }}>
                {experiment.conclusion || 'No conclusion drawn.'}
              </p>
            </Card.Body>
          </Card>
        </Col>

        <Col lg={4}>
          <Card className="mb-4">
            <Card.Header>
              <h5>Experiment Info</h5>
            </Card.Header>
            <Card.Body>
              <p><strong>Status:</strong> {getStatusBadge(experiment.status)}</p>
              <p><strong>Created:</strong> {new Date(experiment.createdAt).toLocaleString()}</p>
              {experiment.experimentDate && (
                <p><strong>Experiment Date:</strong> {new Date(experiment.experimentDate).toLocaleDateString()}</p>
              )}
              <p><strong>Author:</strong> {experiment.userFullName || 'Unknown'}</p>
            </Card.Body>
          </Card>

          <Card>
            <Card.Header>
              <h5>Actions</h5>
            </Card.Header>
            <Card.Body>
              <Button
                as={Link}
                to={`/experiment/${id}/edit`}
                variant="primary"
                className="w-100 mb-2"
              >
                ✏️ Edit Experiment
              </Button>
              <Button
                as={Link}
                to="/"
                variant="secondary"
                className="w-100"
              >
                ← Back to Dashboard
              </Button>
            </Card.Body>
          </Card>
        </Col>
      </Row>
    </div>
  );
};

export default ExperimentDetail; 