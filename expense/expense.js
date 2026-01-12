const API = "http://localhost:8080/api/expense";
const token = localStorage.getItem("token");

if (!token) location.href = "auth.html";

document.addEventListener("DOMContentLoaded", () => {
  addBtn.onclick = addExpense;

  filterCategory.onchange =
  fromDate.onchange =
  toDate.onchange =
  sortBy.onchange = loadExpense;

  // ✅ RESET BUTTON HANDLER
  resetFilters.onclick = resetAllFilters;

  loadExpense();
});

/* ADD EXPENSE */
async function addExpense() {
  const description = descriptionEl().value.trim();
  const amount = Number(amountEl().value);
  const category = categoryEl().value;

  if (!description || !amount) {
    alert("Fill all fields");
    return;
  }

  await fetch(API + "/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + token
    },
    body: JSON.stringify({ description, amount, category })
  });

  descriptionEl().value = "";
  amountEl().value = "";
  loadExpense();
}

/* LOAD + FILTER + SORT */
async function loadExpense() {
  const res = await fetch(API + "/all", {
    headers: { Authorization: "Bearer " + token }
  });

  let data = await res.json();

  const category = filterCategory.value;
  const from = fromDate.value ? new Date(fromDate.value) : null;
  const to = toDate.value ? new Date(toDate.value) : null;
  const sort = sortBy.value;

  // FILTER
  data = data.filter(e => {
    const d = new Date(e.date);
    return (
      (category === "ALL" || e.category === category) &&
      (!from || d >= from) &&
      (!to || d <= to)
    );
  });

  // SORT
  if (sort === "new") data.sort((a,b)=>new Date(b.date)-new Date(a.date));
  if (sort === "old") data.sort((a,b)=>new Date(a.date)-new Date(b.date));
  if (sort === "amountAsc") data.sort((a,b)=>a.amount-b.amount);
  if (sort === "amountDesc") data.sort((a,b)=>b.amount-a.amount);

  list.innerHTML = "";
  data.forEach(e => list.appendChild(card(e)));
}

/* ✅ RESET ALL FILTERS & SORTING */
function resetAllFilters() {
  filterCategory.value = "ALL";
  fromDate.value = "";
  toDate.value = "";
  sortBy.value = "new";
  loadExpense();
}

/* DELETE */
async function deleteExpense(id) {
  await fetch(API + "/" + id, {
    method: "DELETE",
    headers: { Authorization: "Bearer " + token }
  });
  loadExpense();
}

/* HELPERS */
const list = document.getElementById("list");
const descriptionEl = () => document.getElementById("description");
const amountEl = () => document.getElementById("amount");
const categoryEl = () => document.getElementById("category");

// ✅ FILTER ELEMENTS
const filterCategory = document.getElementById("filterCategory");
const fromDate = document.getElementById("fromDate");
const toDate = document.getElementById("toDate");
const sortBy = document.getElementById("sortBy");
const resetFilters = document.getElementById("resetFilters");

function card(e) {
  const d = document.createElement("div");
  d.className = "card";

  d.innerHTML = `
    <h3>${e.description}</h3>
    <div class="category">${e.category}</div>
    <div class="date">${new Date(e.date).toLocaleDateString()}</div>
    <div class="amount">₹${e.amount}</div>
    <button class="delete">Delete</button>
  `;

  d.querySelector(".delete").onclick = () => deleteExpense(e.id);
  return d;
}
