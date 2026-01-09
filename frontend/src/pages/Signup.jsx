import "../styles/auth.css";
import { useState } from "react";
import { signupUser } from "../api/auth";
import { useNavigate } from "react-router-dom";

export default function Signup() {
  const [data, setData] = useState({});
  const nav = useNavigate();

  async function handleSignup(e) {
    e.preventDefault();
    try {
      await signupUser(data);
      alert("Signup success");
      nav("/login");
    } catch (e) {
      alert(e.message);
    }
  }

  return (
    <div className="auth-container">
      <form className="auth-card" onSubmit={handleSignup}>
        <h2>Signup</h2>
        <input placeholder="Username" onChange={e => setData({ ...data, username: e.target.value })} />
        <input placeholder="Email" onChange={e => setData({ ...data, email: e.target.value })} />
        <input type="password" placeholder="Password" onChange={e => setData({ ...data, password: e.target.value })} />
        <button>Signup</button>
      </form>
    </div>
  );
}
