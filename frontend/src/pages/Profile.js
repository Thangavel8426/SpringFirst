import React, { useState } from 'react';
import { Container, Card, Row, Col, Button, Image, Form, Alert } from 'react-bootstrap';
import { useAuth } from '../context/AuthContext';
import { Link } from 'react-router-dom';
import { userAPI } from '../services/api';

const Profile = () => {
  const { user } = useAuth();
  const [profilePic, setProfilePic] = useState(null);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [imageKey, setImageKey] = useState(0); // Add this to force image refresh

  const handleFileChange = (e) => {
    setProfilePic(e.target.files[0]);
  };

  const handleFileUpload = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    if (!profilePic) {
      setError('Please select a file to upload.');
      return;
    }

    console.log('Starting upload for user:', user.sub);
    console.log('File details:', {
      name: profilePic.name,
      size: profilePic.size,
      type: profilePic.type
    });

    const formData = new FormData();
    formData.append('file', profilePic);

    try {
      console.log('Uploading profile picture for user:', user.sub);
      const response = await userAPI.uploadProfilePicture(user.sub, formData);
      console.log('Upload response:', response);
      setSuccess('Profile picture updated successfully!');
      
      // Force refresh the image by updating the key
      setImageKey(prevKey => prevKey + 1);
      
      // Clear the file input
      setProfilePic(null);
      
      // Clear success message after 3 seconds
      setTimeout(() => {
        setSuccess('');
      }, 3000);
      
    } catch (err) {
      console.error('Profile picture upload error:', err);
      console.error('Error response:', err.response);
      const errorMessage = err.response?.data?.message || err.message || 'Failed to upload profile picture.';
      setError('Failed to upload profile picture: ' + errorMessage);
      
      // Clear error message after 5 seconds
      setTimeout(() => {
        setError('');
      }, 5000);
    }
  };

  if (!user) {
    return (
      <Container>
        <p>Loading profile...</p>
      </Container>
    );
  }

  const profilePicUrl = `http://localhost:8080/api/profile-pictures/${user.sub}?t=${Date.now()}&k=${imageKey}`;
  const defaultProfilePicUrl = 'https://cdn.pixabay.com/photo/2017/06/13/12/53/profile-2398782_640.png';
  console.log('Profile picture URL:', profilePicUrl);
  console.log('User object:', user);

  const handleImageError = (e) => {
    console.log('User-specific image failed to load, using default image');
    // Fall back to default image
    e.target.src = defaultProfilePicUrl;
    setError(''); // Clear any previous errors
  };

  const handleImageLoad = (e) => {
    console.log('Image loaded successfully:', e.target.src);
    setError(''); // Clear any previous errors
    e.target.style.display = 'block';
  };

  return (
    <Container>
      <Row className="justify-content-center">
        <Col md={8}>
          <Card>
            <Card.Header as="h3">User Profile</Card.Header>
            <Card.Body>
              <Row>
                <Col md={4} className="text-center">
                  <Image
                    src={profilePicUrl}
                    alt="Profile Picture"
                    className="profile-image mb-3"
                    style={{ width: '150px', height: '150px', objectFit: 'cover', borderRadius: '50%' }}
                    onError={handleImageError}
                    onLoad={handleImageLoad}
                    key={imageKey} // Force re-render when key changes
                  />
                  {error && <div className="text-danger mb-2">{error}</div>}
                  <Form onSubmit={handleFileUpload} className="mt-3">
                    <Form.Group controlId="formFile" className="mb-3">
                      <Form.Control 
                        type="file" 
                        onChange={handleFileChange}
                        accept="image/*"
                        key={imageKey} // Force re-render of file input
                      />
                    </Form.Group>
                    <Button variant="secondary" type="submit" size="sm" disabled={!profilePic}>
                      {profilePic ? 'Upload Picture' : 'Select a file first'}
                    </Button>
                  </Form>
                  {error && <Alert variant="danger" className="mt-3">{error}</Alert>}
                  {success && <Alert variant="success" className="mt-3">{success}</Alert>}
                </Col>
                <Col md={8}>
                  <Row className="mb-3">
                    <Col sm={4}>
                      <strong>Username</strong>
                    </Col>
                    <Col sm={8}>{user.sub}</Col>
                  </Row>
                  <Row className="mb-3">
                    <Col sm={4}>
                      <strong>Email</strong>
                    </Col>
                    <Col sm={8}>{user.email}</Col>
                  </Row>
                  <Row className="mb-3">
                    <Col sm={4}>
                      <strong>First Name</strong>
                    </Col>
                    <Col sm={8}>{user.firstName}</Col>
                  </Row>
                  <Row className="mb-3">
                    <Col sm={4}>
                      <strong>Last Name</strong>
                    </Col>
                    <Col sm={8}>{user.lastName}</Col>
                  </Row>
                  <Row className="mb-3">
                    <Col sm={4}>
                      <strong>Role</strong>
                    </Col>
                    <Col sm={8}>{user.role}</Col>
                  </Row>
                </Col>
              </Row>
            </Card.Body>
            <Card.Footer className="text-end">
              <Button as={Link} to="/edit-profile" variant="primary">Edit Profile</Button>
            </Card.Footer>
          </Card>
        </Col>
      </Row>
    </Container>
  );
};

export default Profile; 