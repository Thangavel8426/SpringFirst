import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { FaBook, FaPlusSquare, FaUser, FaSignInAlt, FaSignOutAlt, FaSearch } from 'react-icons/fa';
import { Image } from 'react-bootstrap';
import '../styles/App.css';
import { useSearch, useExperimentEvents } from '../context/SearchContext';

const NavigationBar = () => {
  const { isAuthenticated, logout, user } = useAuth();
  const navigate = useNavigate();
  const { searchTerm, setSearchTerm } = useSearch();
  const { triggerRefresh } = useExperimentEvents();

  const profilePicUrl = user ? `http://localhost:8080/api/profile-pictures/${user.sub}?t=${Date.now()}` : '';
  const defaultProfilePicUrl = 'https://cdn.pixabay.com/photo/2017/06/13/12/53/profile-2398782_640.png';

  const handleImageError = (e) => {
    console.log('User-specific image failed to load, using default image');
    e.target.src = defaultProfilePicUrl;
  };

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <nav className="animated-navbar">
      <ul className="animated-nav-list">
        <li>
          <Link to="/">
            <FaBook style={{ marginRight: 6 }} /> Dashboard
          </Link>
        </li>
        {isAuthenticated && (
          <>
            <li>
              <Link to="/experiment/new">
                <FaPlusSquare style={{ marginRight: 6 }} /> New Experiment
              </Link>
            </li>
            <li>
              <Link to="/profile" style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
                {user && (
                  <Image
                    src={profilePicUrl}
                    alt="Profile"
                    style={{ 
                      width: '24px', 
                      height: '24px', 
                      borderRadius: '50%', 
                      objectFit: 'cover',
                      border: '2px solid white'
                    }}
                    onError={handleImageError}
                  />
                )}
                <FaUser style={{ marginRight: 6 }} /> Profile
              </Link>
            </li>
            <li>
              <a href="#logout" onClick={handleLogout}>
                <FaSignOutAlt style={{ marginRight: 6 }} /> Logout
              </a>
            </li>
          </>
        )}
        {!isAuthenticated && (
          <li>
            <Link to="/login">
              <FaSignInAlt style={{ marginRight: 6 }} /> Login
            </Link>
          </li>
        )}
      </ul>
      {/* Modern expanding search bar */}
      <div className="searchBox" style={{ position: 'relative', marginLeft: '2rem' }}>
        <input
          className="searchInput"
          type="text"
          placeholder="Search experiments..."
          value={searchTerm}
          onChange={e => setSearchTerm(e.target.value)}
        />
        <button className="searchButton" type="button">
          <FaSearch />
        </button>
      </div>
    </nav>
  );
};

export default NavigationBar; 