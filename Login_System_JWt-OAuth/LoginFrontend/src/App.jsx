import { useState } from "react";
import Login from "./Pages/Login";
import Home from "./Pages/Home";
import AuthSuccess from "./Pages/AuthSuccess";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import PaymentSuccess from "./Pages/PaymentSuccess";
import PaymentCancel from "./Pages/PaymentCancel"

function App() {
    const [user, setUser] = useState(null);

    return (
        <BrowserRouter>
            <Routes>
                <Route
                    path="/"
                    element={
                        user ? (
                            <Home email={user} onLogout={() => setUser(null)} />
                        ) : (
                            <Login onLogin={setUser} />
                        )
                    }
                />
                <Route
                    path="/oauth-success"
                    element={<AuthSuccess onLogin={setUser} />}
                />
                <Route path="/payment-success" element={<PaymentSuccess />} />
                <Route path="/payment-cancel" element={<PaymentCancel />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
