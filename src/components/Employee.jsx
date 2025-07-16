import { useEffect, useState } from "react";
import axios from "axios";

const Employee = () => {
  const [employees, setEmployees] = useState([]);

  const fetchEmployees = async () => {
    try {
      const token = localStorage.getItem("token");
      const res = await axios.get("http://localhost:8080/employee", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setEmployees(res.data);
    } catch (err) {
      console.error("Error fetching employees", err);
      alert("Access Denied. You are not authorized.");
    }
  };

  useEffect(() => {
    fetchEmployees();
  }, []);

  const containerStyle = {
    width: "90%",
    margin: "50px auto",
    fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
    animation: "fadeIn 1.5s ease",
  };

  const tableContainer = {
    overflowX: "auto",
    background: "rgba(255, 255, 255, 0.7)",
    borderRadius: "15px",
    boxShadow: "0 10px 30px rgba(0, 0, 0, 0.1)",
    backdropFilter: "blur(10px)",
    padding: "20px",
  };

  const tableStyle = {
    width: "100%",
    borderCollapse: "collapse",
    borderRadius: "15px",
    overflow: "hidden",
    textAlign: "left",
  };

  const thStyle = {
    backgroundColor: "#007bff",
    color: "white",
    padding: "12px 15px",
    fontSize: "16px",
    fontWeight: "600",
  };

  const tdStyle = {
    padding: "12px 15px",
    borderBottom: "1px solid #ddd",
    transition: "background 0.3s, transform 0.2s",
  };

  const rowStyleEven = {
    backgroundColor: "#f9f9f9",
  };

  return (
    <>
      <style>
        {`
          @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
          }

          tr:hover {
            background-color: #e3f2fd !important;
            transform: scale(1.01);
          }

          ::-webkit-scrollbar {
            height: 8px;
          }

          ::-webkit-scrollbar-thumb {
            background: #007bff;
            border-radius: 4px;
          }
        `}
      </style>

      <section style={containerStyle}>
        <h2 style={{ textAlign: "center", marginBottom: "30px", color: "#007bff" }}>
          👨‍💼 Employee List
        </h2>
        {employees.length === 0 ? (
          <p style={{ textAlign: "center", fontSize: "18px" }}>No employees found.</p>
        ) : (
          <div style={tableContainer}>
            <table style={tableStyle}>
              <thead>
                <tr>
                  <th style={thStyle}>ID</th>
                  <th style={thStyle}>Name</th>
                  <th style={thStyle}>UserName</th>
                  <th style={thStyle}>Email</th>
                  <th style={thStyle}>Role Name</th>
                </tr>
              </thead>
              <tbody>
                {employees.map((emp, index) => (
                  <tr key={emp.id} style={index % 2 === 0 ? rowStyleEven : {}}>
                    <td style={tdStyle}>{emp.empId}</td>
                    <td style={tdStyle}>{emp.name}</td>
                    <td style={tdStyle}>{emp.userName}</td>
                    <td style={tdStyle}>{emp.email}</td>
                    <td style={tdStyle}>
                      {emp.roles.map((role) => role.roleName).join(", ")}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </section>
    </>
  );
};

export default Employee;
