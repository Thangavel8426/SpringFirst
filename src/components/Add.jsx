import { useState } from "react";
import axios from "axios";

const Add = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userName, setUserName] = useState("");
  const [roles, setRoles] = useState([]);

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
    const req = await axios.post("http://localhost:8080/api/auth", {
      name,
      email,
      password,
      userName,
      roles,
    });
    console.log(req.data);
    if (req.data) {
      alert(req.data);
    } else {
      alert("Failed");
    }
  }

  // ✅ Inline CSS Styles
  const formContainer = {
    maxWidth: "500px",
    margin: "40px auto",
    padding: "30px",
    borderRadius: "15px",
    backgroundColor: "#f9f9f9",
    boxShadow: "0 0 10px rgba(0, 0, 0, 0.1)",
    fontFamily: "Arial, sans-serif",
  };

  const headingStyle = {
    textAlign: "center",
    marginBottom: "20px",
    color: "#333",
  };

  const labelStyle = {
    display: "block",
    marginBottom: "5px",
    fontWeight: "bold",
    color: "#444",
  };

  const inputStyle = {
    width: "100%",
    padding: "10px",
    marginBottom: "20px",
    border: "1px solid #ccc",
    borderRadius: "5px",
    fontSize: "14px",
  };

  const checkboxGroup = {
    marginBottom: "20px",
  };

  const checkboxLabel = {
    display: "block",
    marginBottom: "10px",
    fontWeight: "500",
    fontSize: "14px",
  };

  const buttonStyle = {
    width: "100%",
    padding: "12px",
    backgroundColor: "#0066cc",
    color: "white",
    border: "none",
    borderRadius: "5px",
    fontWeight: "bold",
    fontSize: "16px",
    cursor: "pointer",
  };

  return (
    <section style={formContainer}>
      <h2 style={headingStyle}>Add Employee</h2>
      <form onSubmit={addNewEmployee}>
        <label style={labelStyle} htmlFor="name">Employee Name:</label>
        <input
          type="text"
          id="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          style={inputStyle}
        />

        <label style={labelStyle} htmlFor="email">Employee Email:</label>
        <input
          type="email"
          id="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          style={inputStyle}
        />

        <label style={labelStyle} htmlFor="password">Employee Password:</label>
        <input
          type="password"
          id="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          style={inputStyle}
        />

        <label style={labelStyle} htmlFor="userName">Employee Username:</label>
        <input
          type="text"
          id="userName"
          value={userName}
          onChange={(e) => setUserName(e.target.value)}
          style={inputStyle}
        />

        <div style={checkboxGroup}>
          <span style={labelStyle}>Employee Roles:</span>
          <label style={checkboxLabel}>
            <input
              type="checkbox"
              value="ROLE_ADMIN"
              checked={roles.includes("ROLE_ADMIN")}
              onChange={handleRoleChange}
            />{" "}
            Admin
          </label>
          <label style={checkboxLabel}>
            <input
              type="checkbox"
              value="ROLE_USER"
              checked={roles.includes("ROLE_USER")}
              onChange={handleRoleChange}
            />{" "}
            User
          </label>
        </div>

        <button type="submit" style={buttonStyle}>Submit</button>
      </form>
    </section>
  );
};

export default Add;
