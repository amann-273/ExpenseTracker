const BASE = "http://localhost:8080/api/expense";

export async function getExpenses(userId, token) {
  const res = await fetch(`${BASE}/user/${userId}`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return res.json();
}

export async function addExpense(data, token) {
  const res = await fetch(`${BASE}/add`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(data),
  });
  return res.json();
}

export async function deleteExpense(id, token) {
  await fetch(`${BASE}/${id}`, {
    method: "DELETE",
    headers: { Authorization: `Bearer ${token}` },
  });
}
