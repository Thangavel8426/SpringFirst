import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Signup = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userName, setUserName] = useState("");
  const [roles, setRoles] = useState([]);
  const navigate = useNavigate();

  const handleRoleChange = (e) => {
    const { value, checked } = e.target;
    if (checked) {
      setRoles([...roles, value]);
    } else {
      setRoles(roles.filter((role) => role !== value));
    }
  };

  async function addNewEmployee(e) {
    e.preventDefault();
    try {
      const req = await axios.post("http://localhost:8080/api/auth", {
        name,
        email,
        password,
        userName,
        roles,
      });
      alert(req.data || "Signup successful!");
    } catch (error) {
      alert("Signup failed. Check console.");
      console.error("Signup error:", error);
    }
  }

  // 💎 Enhanced CSS
  const containerStyle = {
    maxWidth: "480px",
    margin: "60px auto",
    padding: "40px",
    borderRadius: "15px",
    background: "rgba(255,255,255,0.15)",
    boxShadow: "0 8px 32px 0 rgba(31, 38, 135, 0.37)",
    backdropFilter: "blur(10px)",
    WebkitBackdropFilter: "blur(10px)",
    border: "1px solid rgba(255, 255, 255, 0.18)",
    fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
    animation: "fadeIn 1s ease",
    color: "#333",
  };

  const headingStyle = {
    textAlign: "center",
    fontSize: "28px",
    color: "#007bff",
    marginBottom: "30px",
    fontWeight: 600,
  };

  const labelStyle = {
    display: "block",
    marginBottom: "6px",
    fontWeight: "600",
    color: "#333",
  };

  const inputStyle = {
    width: "100%",
    padding: "12px",
    marginBottom: "20px",
    border: "1px solid #ccc",
    borderRadius: "8px",
    fontSize: "15px",
    outline: "none",
    transition: "all 0.3s ease",
    backgroundColor: "rgba(255, 255, 255, 0.9)",
  };

  const checkboxLabelStyle = {
    fontSize: "15px",
    color: "#444",
    marginBottom: "10px",
    display: "flex",
    alignItems: "center",
    gap: "8px",
  };

  const checkboxStyle = {
    width: "18px",
    height: "18px",
    accentColor: "#28a745",
  };

  const buttonStyle = {
    width: "100%",
    padding: "14px",
    background: "linear-gradient(135deg, #28a745, #218838)",
    color: "#fff",
    border: "none",
    borderRadius: "8px",
    fontSize: "17px",
    fontWeight: 600,
    cursor: "pointer",
    boxShadow: "0 0 12px rgba(40, 167, 69, 0.6)",
    transition: "all 0.3s ease",
  };

  const loginLinkStyle = {
    textAlign: "center",
    marginTop: "20px",
    color: "#007bff",
    cursor: "pointer",
    fontSize: "14px",
    textDecoration: "underline",
  };

  return (
    <>
      <style>{`
        @keyframes fadeIn {
          from { opacity: 0; transform: translateY(30px); }
          to { opacity: 1; transform: translateY(0); }
        }
        button:hover {
          transform: scale(1.03);
          box-shadow: 0 0 18px rgba(40, 167, 69, 0.8);
        }
      `}</style>

      <section style={containerStyle}>
        <h2 style={headingStyle}>Create an Account</h2>
        <form onSubmit={addNewEmployee}>
          <label htmlFor="name" style={labelStyle}>Full Name</label>
          <input
            type="text"
            id="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            style={inputStyle}
            required
          />

          <label htmlFor="email" style={labelStyle}>Email Address</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            style={inputStyle}
            required
          />

          <label htmlFor="password" style={labelStyle}>Password</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            style={inputStyle}
            required
          />

          <label htmlFor="userName" style={labelStyle}>Username</label>
          <input
            type="text"
            id="userName"
            value={userName}
            onChange={(e) => setUserName(e.target.value)}
            style={inputStyle}
            required
          />

          <label style={labelStyle}>Assign Roles</label>
          <label style={checkboxLabelStyle}>
            <input
              type="checkbox"
              value="ROLE_ADMIN"
              checked={roles.includes("ROLE_ADMIN")}
              onChange={handleRoleChange}
              style={checkboxStyle}
            />
            Admin
          </label>
          <label style={checkboxLabelStyle}>
            <input
              type="checkbox"
              value="ROLE_USER"
              checked={roles.includes("ROLE_USER")}
              onChange={handleRoleChange}
              style={checkboxStyle}
            />
            User
          </label>

          <button type="submit" style={buttonStyle}>Sign Up</button>
        </form>

        <div style={loginLinkStyle} onClick={() => navigate("/login")}>
          Already registered? Login
        </div>
      </section>
    </>
  );
};

export default Signup;
