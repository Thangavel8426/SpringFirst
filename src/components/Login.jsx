import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = ({ setIsLoggedIn }) => {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  async function handleLogin(event) {
    event.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/auth/login", {
        userName,
        password,
      });

      const token = response.data.token;
      if (token) {
        localStorage.setItem("token", token);
        setIsLoggedIn(true);
        alert("Login Successful");
        navigate("/");
      } else {
        alert("Token not received from backend.");
      }
    } catch (e) {
      console.log("Login Error", e);
      alert("Invalid Credentials");
    }
  }

  // Updated Stylish CSS
  const containerStyle = {
    maxWidth: "420px",
    margin: "80px auto",
    padding: "40px",
    borderRadius: "20px",
    background: "linear-gradient(135deg, #e3f2fd, #ffffff)",
    boxShadow: "0 20px 40px rgba(0, 0, 0, 0.2)",
    fontFamily: "'Poppins', sans-serif",
    animation: "float 4s ease-in-out infinite",
    transformStyle: "preserve-3d",
  };

  const headingStyle = {
    textAlign: "center",
    color: "#0d6efd",
    fontWeight: "700",
    fontSize: "28px",
    marginBottom: "30px",
  };

  const labelStyle = {
    display: "block",
    marginBottom: "8px",
    fontWeight: "600",
    color: "#333",
    fontSize: "14px",
  };

  const inputStyle = {
    width: "100%",
    padding: "12px",
    marginBottom: "25px",
    border: "1px solid #ccc",
    borderRadius: "10px",
    fontSize: "14px",
    transition: "0.3s",
    outline: "none",
    boxShadow: "inset 0 1px 3px rgba(0,0,0,0.1)",
  };

  const inputFocusStyle = {
    border: "1px solid #0d6efd",
    boxShadow: "0 0 8px rgba(13, 110, 253, 0.3)",
  };

  const buttonStyle = {
    width: "100%",
    padding: "14px",
    background: "#0d6efd",
    color: "#fff",
    border: "none",
    borderRadius: "50px",
    fontSize: "16px",
    fontWeight: "600",
    cursor: "pointer",
    transition: "all 0.3s ease",
    boxShadow: "0 10px 20px rgba(13, 110, 253, 0.3)",
  };

  const buttonHoverStyle = {
    transform: "translateY(-3px)",
    boxShadow: "0 15px 25px rgba(13, 110, 253, 0.4)",
  };

  return (
    <>
      <style>
        {`
          @keyframes float {
            0% { transform: translateY(0px); }
            50% { transform: translateY(-10px); }
            100% { transform: translateY(0px); }
          }

          input:focus {
            border: 1px solid #0d6efd !important;
            box-shadow: 0 0 8px rgba(13, 110, 253, 0.3) !important;
          }

          button:hover {
            transform: translateY(-3px);
            box-shadow: 0 15px 25px rgba(13, 110, 253, 0.4);
          }
        `}
      </style>
      <div style={containerStyle}>
        <h2 style={headingStyle}>🔐 Login Portal</h2>
        <form onSubmit={handleLogin}>
          <label htmlFor="userName" style={labelStyle}>User Name</label>
          <input
            id="userName"
            name="userName"
            value={userName}
            type="text"
            onChange={(e) => setUserName(e.target.value)}
            style={inputStyle}
          />

          <label htmlFor="password" style={labelStyle}>Password</label>
          <input
            id="password"
            name="password"
            value={password}
            type="password"
            onChange={(e) => setPassword(e.target.value)}
            style={inputStyle}
          />

          <button type="submit" style={buttonStyle}> Login</button>
        </form>
      </div>
    </>
  );
};

export default Login;
