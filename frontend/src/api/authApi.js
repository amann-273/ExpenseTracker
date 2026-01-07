// src/api/auth.js
export async function loginUser(email, password) {
  const res = await fetch(`http://localhost:8080/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password }), // <-- this is the form data
  });

  if (!res.ok) throw new Error("Login failed");
  return res.json(); // will return the response from Spring Boot
}
