const BASE = "http://localhost:8080/api/pnl";

export const getPnL = (userId, token) =>
  fetch(`${BASE}/${userId}`, {
    headers: { Authorization: `Bearer ${token}` }
  }).then(res => res.json());
