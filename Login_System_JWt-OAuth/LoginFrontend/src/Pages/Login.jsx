import { useState, useEffect } from "react";
import { api } from "../Services/Api"
import "./Login.css";

export default function Login({ onLogin }) {
    const [email, setEmail] = useState("");
    const [otp, setOtp] = useState("");
    const [step, setStep] = useState(1);
    const [msg, setMsg] = useState("");

    const [timer, setTimer] = useState(0);
    const [canResend, setCanResend] = useState(true);

    const [loading, setLoading] = useState(false);



    const isValidEmail = (email) => {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    };

    const startTimer = () => {
        setTimer(30);
        setCanResend(false);
    }

    useEffect(() => {
        if (timer === 0) return;

        const interval = setInterval(() => {
            setTimer((prev) => prev - 1);
        }, 1000);

        return () => clearInterval(interval);
    }, [timer]);

    useEffect(() => {
        if (timer === 0) {
            setCanResend(true);
        }
    }, [timer]);





    const sendOtp = async () => {
        if (!email) {
            setMsg("Email is required");
            return;
        }

        if (!isValidEmail(email)) {
            setMsg("Please enter a valid email address");
            return;
        }

        try {
            setLoading(true);
            await api.post("/otp", { email });
            setStep(2);
            setMsg("OTP sent to your email ✉️");
            startTimer();
        } catch (err) {
            setMsg(
                err.response?.data?.message ||
                err.response?.data?.error ||
                "Server error"
            );
        } finally {
            setLoading(false);
        }
    };


    const resendOtp = async () => {
        try {
            await api.post("/otp", { email });
            setMsg("OTP resent successfully");
            startTimer();
        } catch (err) {
            setMsg(err.response?.data?.message ||
                err.response?.data?.error || "Please wait");
        }
    };


    const verifyOtp = async () => {
        if (!otp) {
            setMsg("OTP is required");
            return;
        }

        try {
            const res = await api.post("/verify", { email, otp });

            localStorage.setItem("token", res.data.token);
            // ✅ only login on real success
            if (res.status === 200) {
                onLogin(res.data.email);
            }

        } catch (err) {
            const message =
                typeof err.response?.data === "string"
                    ? err.response.data
                    : err.response?.data?.message ||
                    err.response?.data?.error ||
                    "OTP expired or invalid";

            setMsg(message);
        }

    };

    // useEffect(() => {
    //     fetch("http://localhost:8080/api/email/oauth2/success", {
    //         credentials: "include"
    //     })
    //         .then(res => res.json())
    //         .then(data => {
    //             onLogin(data.email);
    //             localStorage.setItem("token", data.token);
    //         });
    // }, []);



    return (
        <div className="login-container">
            <div className="card">
                <h2>🔐 Login via OTP</h2>

                {step === 1 && (
                    <>
                        <input
                            type="email"
                            placeholder="Enter email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                        <button onClick={sendOtp} disabled={loading}>
                            {loading ? "Sending OTP..." : "Send OTP"}
                        </button>

                        <button
                            className="google-btn"
                            onClick={() => {
                                window.location.href =
                                    "http://localhost:8080/oauth2/authorization/google";
                            }}
                        >
                            🔵 Login with Google
                        </button>


                    </>
                )}

                {step === 2 && (
                    <>
                        <input
                            type="text"
                            placeholder="Enter OTP"
                            value={otp}
                            onChange={(e) => setOtp(e.target.value)}
                        />

                        <button onClick={verifyOtp}>Verify OTP</button>

                        <button
                            onClick={resendOtp}
                            disabled={!canResend}
                            style={{
                                backgroundColor: canResend ? "#4f46e5" : "#ccc",
                                cursor: canResend ? "pointer" : "not-allowed"
                            }}
                        >
                            {canResend ? "Resend OTP" : `Resend in ${timer}s`}
                        </button>
                    </>
                )}


                {msg && <p className="msg">{msg}</p>}
            </div>
        </div>
    );
}
