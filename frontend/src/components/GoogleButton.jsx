const GoogleButton = () => {

  const handleGoogleLogin = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google";

  };

  return (
    <button onClick={handleGoogleLogin} className="google-btn">
      Continue with Google
    </button>
  );
};

export default GoogleButton;
