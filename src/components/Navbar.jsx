import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();
  const isLoggedIn = !!localStorage.getItem("token");
  const roles = JSON.parse(localStorage.getItem("roles") || "[]");
  const isAdmin = roles.some(role => role.includes("ADMIN"));

  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark px-3">
      <Link className="navbar-brand" to="/">EMS</Link>

      <ul className="navbar-nav ms-auto">
        {isLoggedIn && isAdmin && (
          <li className="nav-item">
            <Link className="nav-link" to="/add">Add</Link>
          </li>
        )}

        {isLoggedIn && (
          <>
            <li className="nav-item">
              <Link className="nav-link" to="/getemployees">Employees</Link>
            </li>
            <li className="nav-item">
              <button className="btn btn-link nav-link" onClick={handleLogout}>Logout</button>
            </li>
          </>
        )}

        {!isLoggedIn && (
          <li className="nav-item">
            <Link className="nav-link" to="/">Login</Link>
          </li>
        )}
      </ul>
    </nav>
  );
};

export default Navbar;
