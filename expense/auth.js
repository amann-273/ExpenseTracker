const API = "http://localhost:8080";

/* =========================
   AUTH PAGE GUARD
========================= */
(function blockIfLoggedIn() {
  const token = localStorage.getItem("token");
  if (token) {
    window.location.replace("dashboard.html");
  }
})();

/* =========================
   FLIP CARD
========================= */
function flip() {
  document.getElementById("card").classList.toggle("flipped");
}

/* =========================
   LOGIN
========================= */
async function login() {
  localStorage.removeItem("token");

  const email = loginEmail.value.trim();
  const password = loginPassword.value.trim();
  const errorEl = document.getElementById("loginError");

  errorEl.style.display = "none";
  errorEl.innerText = "";

  if (!email || !password) {
    errorEl.innerText = "Email and password required";
    errorEl.style.display = "block";
    return;
  }

  try {
    const res = await fetch(`${API}/api/auth/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password })
    });

    if (!res.ok) {
      const msg = await res.text();
      throw new Error(msg || "Invalid email or password");
    }

    const data = await res.json();
    localStorage.setItem("token", data.token);
    window.location.replace("dashboard.html");

  } catch (e) {
  if (e instanceof TypeError) {
    // Network / CORS / Backend down
    errorEl.innerText = "Server unavailable. Please try again later.";
  } else {
    // Actual backend error (401, wrong password)
    errorEl.innerText = e.message || "Invalid email or password";
  }
  errorEl.style.display = "block";
}

}

/* =========================
   SIGNUP
========================= */
async function signup() {
  localStorage.removeItem("token");

  const name = signupName.value.trim();
  const email = signupEmail.value.trim();
  const password = signupPassword.value.trim();

  if (!name || !email || !password) {
    alert("All fields required");
    return;
  }

  try {
    const res = await fetch(`${API}/api/auth/signup`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username: name, email, password })
    });

    if (!res.ok) throw new Error(await res.text());

    alert("Signup successful. Login now.");
    flip();

  } catch (e) {
    alert("Signup failed");
    console.error(e);
  }
}

/* =========================
   GOOGLE LOGIN
========================= */
function googleLogin() {
  localStorage.removeItem("token");
  window.location.href = `${API}/oauth2/authorization/google?prompt=consent%20select_account`;
}
