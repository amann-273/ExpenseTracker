import "../styles/dashboard.css";
import { useEffect, useState } from "react";
import { getExpenses, deleteExpense } from "../api/expense";
import { getIncome, deleteIncome } from "../api/income";
import { useNavigate } from "react-router-dom";

export default function Dashboard() {
  const navigate = useNavigate();

  const [user, setUser] = useState(null);
  const [token, setToken] = useState(null);
  const [expenses, setExpenses] = useState([]);
  const [income, setIncome] = useState([]);
  const [error, setError] = useState("");

  // ✅ AUTH CHECK
  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    const storedUser = localStorage.getItem("user");

    if (!storedToken || !storedUser) {
      localStorage.clear();
      navigate("/login", { replace: true });
      return;
    }

    try {
      setToken(storedToken);
      setUser(JSON.parse(storedUser));
    } catch {
      localStorage.clear();
      navigate("/login", { replace: true });
    }
  }, [navigate]);

  // ✅ LOAD DATA AFTER AUTH
  useEffect(() => {
    if (!user || !token) return;
    loadAll();
  }, [user, token]);

  async function loadAll() {
    try {
      const exp = await getExpenses(user.id, token);
      const inc = await getIncome(user.id, token);

      setExpenses(Array.isArray(exp) ? exp : []);
      setIncome(Array.isArray(inc) ? inc : []);
    } catch {
      setError("Session expired. Please login again.");
    }
  }

  function logout() {
    localStorage.clear();
    navigate("/login", { replace: true });
  }

  if (!user) return null; // ⏳ wait until auth check finishes

  const totalIncome = income.reduce((s, i) => s + (i.amount || 0), 0);
  const totalExpense = expenses.reduce((s, e) => s + (e.amount || 0), 0);

  return (
    <div className="dashboard">
      <div className="top-bar">
        <h2>Expense Tracker</h2>
        <button onClick={logout}>Logout</button>
      </div>

      {error && <p style={{ color: "red" }}>{error}</p>}

      <div className="summary">
        <div className="card">Income ₹{totalIncome}</div>
        <div className="card">Expense ₹{totalExpense}</div>
        <div className="card">PnL ₹{totalIncome - totalExpense}</div>
      </div>

      <div className="list">
        <div style={{ flex: 1 }}>
          <h3>Expenses</h3>
          {expenses.map(e => (
            <div className="item" key={e.id}>
              ₹{e.amount}
              <button onClick={async () => {
                await deleteExpense(e.id, token);
                loadAll();
              }}>❌</button>
            </div>
          ))}
        </div>

        <div style={{ flex: 1 }}>
          <h3>Income</h3>
          {income.map(i => (
            <div className="item" key={i.id}>
              ₹{i.amount}
              <button onClick={async () => {
                await deleteIncome(i.id, token);
                loadAll();
              }}>❌</button>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
