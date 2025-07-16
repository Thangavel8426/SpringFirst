import { useState } from "react";
import axios from "axios";
import "./form.css";
import "../index.css";

const Signupform = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userName, setUserName] = useState("");
  const [roleNames, setRoleNames] = useState([]);

  const handleRoleChange = (e) => {
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
      const response = await axios.post(
        "https://springboot-ems-backend-3.onrender.com/api/auth/register",
        {
          name,
          email,
          password,
          userName,
          roleNames,
        }
      );
      console.log("Signup Success", response.data);
      alert("Signup Successful");
    } catch (error) {
      console.error("Signup Failed", error);
      alert("Signup Failed. Please try again.");
    }
  };

  return (
    <div className="login-wrap">
      <div className="login-html">
        <input id="tab-2" type="radio" name="tab" className="sign-up" checked readOnly />
        <label htmlFor="tab-2" className="tab">Sign Up</label>
        {/* Only Sign Up tab for signup form */}
        <div className="login-form">
          <div className="sign-up-htm" style={{transform: 'rotateY(0deg)'}}>
            <form onSubmit={handleSignup}>
              <div className="group">
                <label htmlFor="name" className="label">Employee Name</label>
                <input id="name" type="text" className="input" value={name} onChange={e => setName(e.target.value)} required />
              </div>
              <div className="group">
                <label htmlFor="email" className="label">Email</label>
                <input id="email" type="email" className="input" value={email} onChange={e => setEmail(e.target.value)} required />
              </div>
              <div className="group">
                <label htmlFor="userName" className="label">Username</label>
                <input id="userName" type="text" className="input" value={userName} onChange={e => setUserName(e.target.value)} required />
              </div>
              <div className="group">
                <label htmlFor="password" className="label">Password</label>
                <input id="password" type="password" className="input" data-type="password" value={password} onChange={e => setPassword(e.target.value)} required />
              </div>
              <fieldset className="group" style={{border:'none',padding:0}}>
                <legend className="label" style={{color:'#fff'}}>Select Roles</legend>
                <div className="role-checkbox-group" style={{display:'flex',gap:'1rem'}}>
                  <label className="role-checkbox">
                    <input type="checkbox" value="ROLE_ADMIN" onChange={handleRoleChange} />
                    Admin
                  </label>
                  <label className="role-checkbox">
                    <input type="checkbox" value="ROLE_USER" onChange={handleRoleChange} />
                    User
                  </label>
                </div>
              </fieldset>
              <div className="group">
                <input type="submit" className="button" value="Sign Up" />
              </div>
              <div className="hr"></div>
              <div className="foot-lnk">
                <label htmlFor="tab-1">Already Member?</label>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Signupform;
