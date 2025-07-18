import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddEmployee from "./components/AddEmployee";
import GetEmployees from "./components/GetEmployees";
import Login from "./components/Login";
import ProtectedRoute from "./components/ProtectedRoute";
import Signup from "./components/Signup";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
         <Route path="/signup" element={<Signup />} />
        <Route
          path="/add"
          element={
            <ProtectedRoute adminOnly={true}>
              <AddEmployee />
            </ProtectedRoute>
          }
        />
        <Route
          path="/getemployees"
          element={
            <ProtectedRoute>
              <GetEmployees />
            </ProtectedRoute>
          }
        />
        <Route path="/unauthorized" element={<h3>Access Not for you</h3>} />
      </Routes>
    </Router>
  );
}

export default App;
