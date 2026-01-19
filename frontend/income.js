const API = "http://localhost:8080/api/income";
const token = localStorage.getItem("token");

if (!token) location.href = "auth.html";

document.addEventListener("DOMContentLoaded", () => {
  addBtn.onclick = addIncome;

  filterSource.onchange =
  fromDate.onchange =
  toDate.onchange =
  sortBy.onchange = loadIncome;

  resetFilters.onclick = resetAllFilters;

  loadIncome();
});

/* ADD INCOME */
async function addIncome() {
  const description = descriptionEl().value.trim();
  const amount = Number(amountEl().value);
  const source = categoryEl().value;

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
    body: JSON.stringify({ description, amount, source })
  });

  descriptionEl().value = "";
  amountEl().value = "";
  loadIncome();
}

/* LOAD + FILTER + SORT */
async function loadIncome() {
  const res = await fetch(API + "/all", {
    headers: { Authorization: "Bearer " + token }
  });

  let data = await res.json();

  const source = filterSource.value;
  const from = fromDate.value ? new Date(fromDate.value) : null;
  const to = toDate.value ? new Date(toDate.value) : null;
  const sort = sortBy.value;

  data = data.filter(i => {
    const d = new Date(i.date);
    return (
      (source === "ALL" || i.source === source) &&
      (!from || d >= from) &&
      (!to || d <= to)
    );
  });

  if (sort === "new") data.sort((a,b)=>new Date(b.date)-new Date(a.date));
  if (sort === "old") data.sort((a,b)=>new Date(a.date)-new Date(a.date));
  if (sort === "amountAsc") data.sort((a,b)=>a.amount-b.amount);
  if (sort === "amountDesc") data.sort((a,b)=>b.amount-a.amount);

  list.innerHTML = "";
  data.forEach(i => list.appendChild(card(i)));
}

/* RESET */
function resetAllFilters() {
  filterSource.value = "ALL";
  fromDate.value = "";
  toDate.value = "";
  sortBy.value = "new";
  loadIncome();
}

/* DELETE */
async function deleteIncome(id) {
  await fetch(API + "/" + id, {
    method: "DELETE",
    headers: { Authorization: "Bearer " + token }
  });
  loadIncome();
}

/* HELPERS */
const list = document.getElementById("list");
const descriptionEl = () => document.getElementById("description");
const amountEl = () => document.getElementById("amount");
const categoryEl = () => document.getElementById("category");

const filterSource = document.getElementById("filterSource");
const fromDate = document.getElementById("fromDate");
const toDate = document.getElementById("toDate");
const sortBy = document.getElementById("sortBy");
const resetFilters = document.getElementById("resetFilters");

function card(i) {
  const d = document.createElement("div");
  d.className = "card";

  d.innerHTML = `
    <h3>${i.description}</h3>
    <div class="category">${i.source}</div>
    <div class="date">${new Date(i.date).toLocaleDateString()}</div>
    <div class="amount">₹${i.amount}</div>
    <button class="delete">Delete</button>
  `;

  d.querySelector(".delete").onclick = () => deleteIncome(i.id);
  return d;
}
