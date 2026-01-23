import { motion, AnimatePresence } from "framer-motion";
import { useState, useEffect } from "react";
import { api } from "../Services/Api"
import "../css/Login.css";

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
                onLogin(res.data.email, res.data.token);

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




const cardVariant = {
    hidden: { opacity: 0, scale: 0.9 },
    visible: {
        opacity: 1,
        scale: 1,
        transition: { duration: 0.5, ease: "easeOut" }
    }
};

const stepVariant = {
    hidden: { opacity: 0, x: 40 },
    visible: {
        opacity: 1,
        x: 0,
        transition: { duration: 0.4 }
    },
    exit: {
        opacity: 0,
        x: -40,
        transition: { duration: 0.3 }
    }
};

    return (
        <div className="login-wrapper">
            <motion.div
                className="login-card"
                variants={cardVariant}
                initial="hidden"
                animate="visible"
            >
                <h3 className="text-center fw-bold mb-2">Login to Your Account</h3>
                <p className="text-center text-muted mb-4">
                    Secure login using Email OTP
                </p>

                <AnimatePresence mode="wait">
                    {step === 1 && (
                        <motion.div
                            key="step1"
                            variants={stepVariant}
                            initial="hidden"
                            animate="visible"
                            exit="exit"
                        >
                            <div className="mb-3">
                                <label className="form-label">Email</label>
                                <input
                                    type="email"
                                    className="form-control"
                                    placeholder="name@example.com"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                            </div>

                            <motion.button
                                whileHover={{ scale: 1.02 }}
                                whileTap={{ scale: 0.97 }}
                                className="btn btn-primary w-100"
                                onClick={sendOtp}
                                disabled={loading}
                            >
                                {loading ? "Sending OTP..." : "Send OTP"}
                            </motion.button>

                            <div className="divider my-4">OR</div>

                            <motion.button
                                whileHover={{ scale: 1.02 }}
                                whileTap={{ scale: 0.97 }}
                                className="btn btn-outline-dark w-100 google-btn"
                                onClick={() =>
                                    (window.location.href =
                                        "http://localhost:8080/oauth2/authorization/google")
                                }
                            >
                                <img
                                    src="https://www.svgrepo.com/show/475656/google-color.svg"
                                    alt="google"
                                    width="18"
                                    className="me-2"
                                />
                                Continue with Google
                            </motion.button>
                        </motion.div>
                    )}

                    {step === 2 && (
                        <motion.div
                            key="step2"
                            variants={stepVariant}
                            initial="hidden"
                            animate="visible"
                            exit="exit"
                        >
                            <div className="mb-3">
                                <label className="form-label">Enter OTP</label>
                                <motion.input
                                    type="text"
                                    className="form-control text-center otp-input"
                                    placeholder="••••••"
                                    value={otp}
                                    onChange={(e) => setOtp(e.target.value)}
                                    whileFocus={{ scale: 1.05 }}
                                />
                            </div>

                            <motion.button
                                whileHover={{ scale: 1.02 }}
                                whileTap={{ scale: 0.97 }}
                                className="btn btn-success w-100 mb-2"
                                onClick={verifyOtp}
                            >
                                Verify OTP
                            </motion.button>

                            <button
                                className="btn btn-link w-100 resend-btn"
                                onClick={resendOtp}
                                disabled={!canResend}
                            >
                                {canResend ? "Resend OTP" : `Resend in ${timer}s`}
                            </button>
                        </motion.div>
                    )}
                </AnimatePresence>

                {msg && (
                    <motion.div
                        initial={{ opacity: 0, y: 10 }}
                        animate={{ opacity: 1, y: 0 }}
                        className="alert alert-info mt-3 text-center"
                    >
                        {msg}
                    </motion.div>
                )}
            </motion.div>
        </div>
    );
}
