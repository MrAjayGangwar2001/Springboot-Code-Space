import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function AuthSuccess({ onLogin }) {
    const navigate = useNavigate();

    useEffect(() => {
        const params = new URLSearchParams(window.location.search);

        const token = params.get("token");
        const email = params.get("email");
        const name = params.get("name");

        if (!token || !email) {
            navigate("/");
            return;
        }

        localStorage.setItem("token", token);
        localStorage.setItem("email", email);
        localStorage.setItem("name", name);

        onLogin(email);   // 🔥 THIS IS THE KEY LINE
        navigate("/");
    }, []);

    return <h3>Logging in via Google...</h3>;
}
