import React, { useState, useEffect } from 'react';
import { Form, Button, Card, Row, Col, Alert, Spinner } from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';
import { experimentAPI } from '../services/api';
import { toast } from 'react-toastify';
import { useExperimentEvents } from '../context/SearchContext';

const ExperimentForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const isEditing = id && id !== 'new';
  
  const [formData, setFormData] = useState({
    title: '',
    objective: '',
    hypothesis: '',
    materials: '',
    procedureSteps: '',
    observations: '',
    results: '',
    conclusion: '',
    status: 'DRAFT',
    experimentDate: new Date().toISOString().split('T')[0] // Set today's date as default
  });

  const [loading, setLoading] = useState(isEditing);
  const [error, setError] = useState('');
  const { triggerRefresh } = useExperimentEvents();

  useEffect(() => {
    if (isEditing) {
      const loadExperiment = async () => {
        try {
          const response = await experimentAPI.getById(id);
          setFormData(response.data);
        } catch (error) {
          toast.error('Failed to load experiment data.');
          navigate('/');
        } finally {
          setLoading(false);
        }
      };
      loadExperiment();
    }
  }, [id, isEditing, navigate]);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError('');

    try {
      if (isEditing) {
        await experimentAPI.update(id, formData);
        toast.success('Experiment updated successfully!');
      } else {
        await experimentAPI.create(formData);
        toast.success('Experiment created successfully!');
      }
      triggerRefresh();
      navigate(`/`);
    } catch (err) {
      const errorMessage = err.response?.data?.message || err.message || 'Failed to save the experiment.';
      setError(errorMessage);
      toast.error(errorMessage);
    } finally {
      setLoading(false);
    }
  };

  if (loading && isEditing) {
    return <div className="text-center"><Spinner animation="border" /><p>Loading form...</p></div>;
  }
  
  return (
    <div>
      <h2 className="mb-4">
        {isEditing ? '✏️ Edit Experiment' : '🧪 New Experiment'}
      </h2>

      <Card>
        <Card.Body>
          {error && <Alert variant="danger">{error}</Alert>}
          
          <Form onSubmit={handleSubmit}>
            <Row>
              <Col md={8}>
                <Form.Group className="mb-3">
                  <Form.Label>Title *</Form.Label>
                  <Form.Control
                    type="text"
                    name="title"
                    value={formData.title}
                    onChange={handleChange}
                    required
                    placeholder="Enter experiment title"
                  />
                </Form.Group>
              </Col>
              <Col md={4}>
                <Form.Group className="mb-3">
                  <Form.Label>Status</Form.Label>
                  <Form.Select
                    name="status"
                    value={formData.status}
                    onChange={handleChange}
                  >
                    <option value="DRAFT">Draft</option>
                    <option value="IN_PROGRESS">In Progress</option>
                    <option value="COMPLETED">Completed</option>
                  </Form.Select>
                </Form.Group>
              </Col>
            </Row>

            <Row>
              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Experiment Date</Form.Label>
                  <Form.Control
                    type="date"
                    name="experimentDate"
                    value={formData.experimentDate}
                    onChange={handleChange}
                    required
                  />
                </Form.Group>
              </Col>
            </Row>

            <Form.Group className="mb-3">
              <Form.Label>Objective</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                name="objective"
                value={formData.objective}
                onChange={handleChange}
                placeholder="What is the purpose of this experiment?"
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Hypothesis</Form.Label>
              <Form.Control
                as="textarea"
                rows={2}
                name="hypothesis"
                value={formData.hypothesis}
                onChange={handleChange}
                placeholder="What do you expect to happen?"
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Materials</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                name="materials"
                value={formData.materials}
                onChange={handleChange}
                placeholder="List all materials and equipment needed"
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Procedure</Form.Label>
              <Form.Control
                as="textarea"
                rows={5}
                name="procedureSteps"
                value={formData.procedureSteps}
                onChange={handleChange}
                placeholder="Step-by-step procedure"
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Observations</Form.Label>
              <Form.Control
                as="textarea"
                rows={4}
                name="observations"
                value={formData.observations}
                onChange={handleChange}
                placeholder="What did you observe during the experiment?"
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Results</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                name="results"
                value={formData.results}
                onChange={handleChange}
                placeholder="What were the results?"
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Conclusion</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                name="conclusion"
                value={formData.conclusion}
                onChange={handleChange}
                placeholder="What conclusions can you draw?"
              />
            </Form.Group>

            <div className="d-flex gap-2">
              <Button
                type="submit"
                variant="primary"
                disabled={loading}
              >
                {loading ? 'Saving...' : (isEditing ? 'Update Experiment' : 'Create Experiment')}
              </Button>
              <Button
                variant="secondary"
                onClick={() => navigate('/')}
                disabled={loading}
              >
                Cancel
              </Button>
            </div>
          </Form>
        </Card.Body>
      </Card>
    </div>
  );
};

export default ExperimentForm; 