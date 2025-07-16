import React from 'react';

const Footer = () => {
  const styles = {
    footer: {
      backgroundColor: '#2c3e50',
      color: 'white',
      textAlign: 'center',
      padding: '1rem',
    }
  };

  return (
    <footer style={styles.footer}>
      <p>© 2025 EMS. All rights reserved.</p>
    </footer>
  );
};

export default Footer;
