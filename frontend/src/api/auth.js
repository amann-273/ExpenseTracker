const BASE_URL = "http://localhost:8080/api/auth";

/**
 * LOGIN USER
 * @param {{ email: string, password: string }} credentials
 * @returns {Promise<{ token: string, user: object }>}
 */
export async function loginUser(credentials) {
  const res = await fetch(`${BASE_URL}/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(credentials),
  });

  if (!res.ok) {
    throw new Error("Invalid email or password");
  }

  return res.json(); // { token, user }
}

/**
 * SIGNUP USER
 * @param {{ username: string, email: string, password: string }} data
 * @returns {Promise<object>}
 */
export async function signupUser(data) {
  const res = await fetch(`${BASE_URL}/signup`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  if (!res.ok) {
    throw new Error("Signup failed");
  }

  return res.json();
}

/**
 * LOGOUT (frontend only)
 */
export function logoutUser() {
  localStorage.removeItem("token");
  localStorage.removeItem("user");
}

/**
 * GET TOKEN
 */
export function getToken() {
  return localStorage.getItem("token");
}

/**
 * CHECK AUTH
 */
export function isAuthenticated() {
  return !!localStorage.getItem("token");
}
