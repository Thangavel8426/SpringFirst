import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "./Navbar";

const GetEmployees = () => {
    const [employees, setEmployees] = useState([]);
    const [editingEmployee, setEditingEmployee] = useState(null);
    const [editedData, setEditedData] = useState({
        name: "",
        email: "",
        username: "",
        password: "",
        rolenames: [],
    });

    const token = localStorage.getItem("token");
    const roles = JSON.parse(localStorage.getItem("roles") || "[]");
    const isAdmin = roles.includes("ROLE_ADMIN");

    useEffect(() => {
        const fetchEmployees = async () => {
            try {
                const response = await axios.get("https://springboot-ems-backend.onrender.com/employee", {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setEmployees(response.data);
            } catch (err) {
                console.error("Error fetching employees:", err);
                alert("Unauthorized or error fetching employees.");
            }
        };

        if (token) {
            fetchEmployees();
        }
    }, [token]);

    const handleDelete = async (empID) => {
        try {
            await axios.delete(`https://springboot-ems-backend.onrender.com/employee/${empID}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            setEmployees(employees.filter((emp) => emp.empID !== empID));
            alert("Employee deleted successfully");
        } catch (err) {
            console.error("Error deleting employee:", err);
            alert("Delete failed. Check your permissions.");
        }
    };

    const handleEditClick = (employee) => {
        setEditingEmployee(employee.empID);
        setEditedData({
            name: employee.name,
            email: employee.email,
            username: employee.username,
            password: "",
            rolenames: employee.roles?.map(role => role.roleName) || [],
        });
    };

    const handleEditChange = (e) => {
        setEditedData({ ...editedData, [e.target.name]: e.target.value });
    };

    const handleEditSubmit = async () => {
        try {
            await axios.put(
                `https://springboot-ems-backend.onrender.com/employee/${editingEmployee}`,
                editedData,
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );

            setEmployees((prev) =>
                prev.map((emp) =>
                    emp.empID === editingEmployee
                        ? {
                            ...emp,
                            name: editedData.name,
                            email: editedData.email,
                            username: editedData.username,
                        }
                        : emp
                )
            );

            alert("Employee updated successfully");
            setEditingEmployee(null);
        } catch (err) {
            console.error("Error updating employee:", err);
            alert("Update failed");
        }
    };                                                           //23ME026-----THANGAVEL P 

    return (
        <>
            <Navbar />
            <div className="container mt-4">
                <h2 className="mb-4">Employee List</h2>
                {employees.length === 0 ? (
                    <p>Please wait .... </p>
                ) : (
                    <table className="table table-bordered">
                        <thead className="thead-dark">
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Username</th>
                                {isAdmin && <th>Actions</th>}
                            </tr>
                        </thead>
                        <tbody>
                            {employees.map((emp) => (
                                <tr key={emp.empID}>
                                    <td>{emp.empID}</td>
                                    <td>
                                        {editingEmployee === emp.empID ? (
                                            <input
                                                type="text"
                                                name="name"
                                                value={editedData.name}
                                                onChange={handleEditChange}
                                            />
                                        ) : (
                                            emp.name
                                        )}
                                    </td>
                                    <td>
                                        {editingEmployee === emp.empID ? (
                                            <input
                                                type="email"
                                                name="email"
                                                value={editedData.email}
                                                onChange={handleEditChange}
                                            />
                                        ) : (
                                            emp.email
                                        )}
                                    </td>
                                    <td>
                                        {editingEmployee === emp.empID ? (
                                            <input
                                                type="text"
                                                name="username"
                                                value={editedData.username}
                                                onChange={handleEditChange}
                                            />
                                        ) : (
                                            emp.username
                                        )}
                                    </td>
                                    {isAdmin && (
                                        <td>
                                            {editingEmployee === emp.empID ? (
                                                <>
                                                    <input
                                                        type="password"
                                                        name="password"
                                                        placeholder="New Password"
                                                        value={editedData.password}
                                                        onChange={handleEditChange}
                                                        className="form-control my-1"
                                                    />
                                                    <button
                                                        className="btn btn-success btn-sm me-2"
                                                        onClick={handleEditSubmit}
                                                    >
                                                        Save
                                                    </button>
                                                    <button
                                                        className="btn btn-secondary btn-sm"
                                                        onClick={() => setEditingEmployee(null)}
                                                    >
                                                        Cancel
                                                    </button>
                                                </>
                                            ) : (
                                                <>
                                                    <button
                                                        onClick={() => handleDelete(emp.empID)}
                                                        className="btn btn-danger btn-sm me-2"
                                                    >
                                                        Delete
                                                    </button>
                                                    <button
                                                        className="btn btn-primary btn-sm"
                                                        onClick={() => handleEditClick(emp)}
                                                    >
                                                        Edit
                                                    </button>
                                                </>
                                            )}
                                        </td>
                                    )}
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}
            </div>
        </>
    );
};

export default GetEmployees;
