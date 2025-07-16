import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Loginform from "./components/Loginform";
import Signupform from "./components/Signupform";

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/login" element={<Loginform />} />
        <Route path="/signup" element={<Signupform />} />
      </Routes>
    </Router>
  );
}

export default App;
