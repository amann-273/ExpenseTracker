const API = "http://localhost:8080";

/* =========================
   AUTH GUARD
========================= */
const token = localStorage.getItem("token");
if (!token) {
  window.location.href = "auth.html";
}

/* =========================
   LOGOUT
========================= */
function logout() {
  localStorage.removeItem("token");
  window.location.href = "auth.html";
}

/* =========================
   USERNAME FROM JWT
========================= */
function getUsernameFromToken(token) {
  try {
    const payload = JSON.parse(atob(token.split(".")[1]));
    return payload.name || payload.sub || payload.email || "User";
  } catch {
    return "User";
  }
}

document.getElementById("username").innerText =
  getUsernameFromToken(token);

/* =========================
   LOAD DASHBOARD
========================= */
async function loadDashboard() {
  const headers = {
    Authorization: `Bearer ${token}`
  };

  try {
    /* -------- INCOME -------- */
    const incomeRes = await fetch(`${API}/api/income/all`, { headers });
    const incomes = incomeRes.ok ? await incomeRes.json() : [];
    

    /* -------- EXPENSE -------- */
    const expenseRes = await fetch(`${API}/api/expense/all`, { headers });
    const expenses = expenseRes.ok ? await expenseRes.json() : [];

    /* -------- PNL -------- */
    const pnlRes = await fetch(`${API}/api/pnl`, { headers });
    const pnl = pnlRes.ok ? await pnlRes.json() : 0;

    /* -------- TOTALS -------- */
    const totalIncome = incomes.reduce((s, i) => s + i.amount, 0);
    const totalExpense = expenses.reduce((s, e) => s + e.amount, 0);

    document.getElementById("totalIncome").innerText = `₹${totalIncome}`;
    document.getElementById("totalExpense").innerText = `₹${totalExpense}`;
    document.getElementById("pnl").innerText = `₹${pnl}`;

    /* -------- LAST 2 ENTRIES -------- */
    renderRecent(
      "recentIncome",
      incomes.slice(0,5).reverse(),
      "income"
    );

    renderRecent(
      "recentExpense",
      expenses.slice(0,5).reverse(),
      "expense"
    );

  } catch (err) {
    console.error(err);
  }
}


document.querySelector(".nav-item.income")
  .addEventListener("click", () => {
    window.open("income.html", "_blank");
  });

document.querySelector(".nav-item.expense")
  .addEventListener("click", () => {
    window.open("expense.html", "_blank");
  });




/* =========================
   RENDER RECENT LIST
========================= */
function renderRecent(elementId, items, type) {
  const ul = document.getElementById(elementId);
  ul.innerHTML = "";

  if (items.length === 0) {
    ul.innerHTML = "<li>No entries</li>";
    return;
  }

  items.forEach(item => {
    const li = document.createElement("li");
    li.innerText =
      type === "income"
        ? `₹${item.amount} - ${item.source}`
        : `₹${item.amount} - ${item.category}`;
    ul.appendChild(li);
  });
}

/* =========================
   INIT
========================= */
loadDashboard();
