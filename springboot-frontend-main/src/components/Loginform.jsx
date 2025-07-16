import { useState } from "react";
import axios from "axios";
import "./form.css";
import "../index.css";

const Loginform = () => {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("https://springboot-ems-backend-1.onrender.com/api/auth/login", {
        username: userName,
        password: password,
      });
      console.log("Token:", res.data.token);
      alert("Login Successful");
    } catch (e) {
      console.error("Login Error", e);
      alert("Invalid Credentials");
    }                                                         
  };

  return (
    <div className="login-wrap">
      <div className="login-html">
        <input id="tab-1" type="radio" name="tab" className="sign-in" checked readOnly />
        <label htmlFor="tab-1" className="tab">Sign In</label>
        {/* Only Sign In tab for login form */}
        <div className="login-form">
          <div className="sign-in-htm" style={{transform: 'rotateY(0deg)'}}>
            <form onSubmit={handleLogin}>
              <div className="group">
                <label htmlFor="user" className="label">Username</label>
                <input id="user" type="text" className="input" value={userName} onChange={e => setUserName(e.target.value)} required />
              </div>
              <div className="group">
                <label htmlFor="pass" className="label">Password</label>
                <input id="pass" type="password" className="input" data-type="password" value={password} onChange={e => setPassword(e.target.value)} required />
              </div>
              <div className="group">
                <input id="check" type="checkbox" className="check" defaultChecked />
                <label htmlFor="check"><span className="icon"></span> Keep me Signed in</label>
              </div>
              <div className="group">
                <input type="submit" className="button" value="Sign In" />
              </div>
              <div className="hr"></div>
              <div className="foot-lnk">
                <a href="#forgot">Forgot Password?</a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Loginform;
