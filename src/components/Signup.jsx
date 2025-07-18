import { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const Signup = () => {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [username, setUsername] = useState("");
    const [rolenames, setRoleNames] = useState([]);

    const handleCheckboxChange = (e) => {
        const { value, checked } = e.target;
        if (checked) {
            setRoleNames((prev) => [...prev, value]);
        } else {
            setRoleNames((prev) => prev.filter((role) => role !== value));
        }
    };

    const handleSignup = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("https://springboot-ems-backend-3.onrender.com/api/auth/register", {
                name,
                email,
                password,
                username,
                rolenames,
            });
            console.log("Signup Success", response.data);
            alert("Signup Successful");
        } catch (error) {
            console.error("Signup Failed", error);
            alert("Signup Failed. Please try again.");
        }
    };

    return (
        <div className="container d-flex justify-content-center align-items-center min-vh-100">
            <div className="card shadow p-4" style={{ maxWidth: "500px", width: "100%" }}>
                <h2 className="text-center mb-4">Signup</h2>
                <form onSubmit={handleSignup}>
                    <div className="mb-3">
                        <label htmlFor="name" className="form-label">Employee Name</label>
                        <input type="text" id="name" className="form-control" value={name} onChange={(e) => setName(e.target.value)} required />
                    </div>

                    <div className="mb-3">
                        <label htmlFor="email" className="form-label">Email</label>
                        <input type="email" id="email" className="form-control" value={email} onChange={(e) => setEmail(e.target.value)} required />
                    </div>

                    <div className="mb-3">
                        <label htmlFor="username" className="form-label">Username</label>
                        <input type="text" id="username" className="form-control" value={username} onChange={(e) => setUsername(e.target.value)} required />
                    </div>

                    <div className="mb-3">
                        <label htmlFor="password" className="form-label">Password</label>
                        <input type="password" id="password" className="form-control" value={password} onChange={(e) => setPassword(e.target.value)} required />
                    </div>

                    <fieldset className="mb-3 border p-3 rounded">
                        <legend className="fs-6">Select Roles</legend>
                        <div className="form-check">
                            <input className="form-check-input" type="checkbox" value="ROLE_ADMIN" id="adminRole" onChange={handleCheckboxChange} />
                            <label className="form-check-label" htmlFor="adminRole">Admin</label>
                        </div>
                        <div className="form-check">
                            <input className="form-check-input" type="checkbox" value="ROLE_USER" id="userRole" onChange={handleCheckboxChange} />
                            <label className="form-check-label" htmlFor="userRole">User</label>
                        </div>
                    </fieldset>

                    <button type="submit" className="btn btn-primary w-100">Signup</button>
                </form>

                <p className="mt-3 text-center">
                    Already have an account?<Link    to="/">Login</Link>
                </p>
            </div>
        </div>
    );
};

export default Signup;