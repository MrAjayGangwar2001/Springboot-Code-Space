import Payment from "../Payment";

export default function Home({ email, onLogout }) {
    return (
        <div className="home-container">

            {/* HERO SECTION */}
            <section className="hero-section d-flex align-items-center">
                <div className="container text-center text-white">
                    <h1 className="display-4 fw-bold">Welcome to Dashboard 🚀</h1>
                    <p className="lead mt-3">
                        You are logged in as <strong>{email}</strong>
                    </p>

                    <div className="mt-4">
                        <button className="btn btn-light me-3 px-4">
                            Get Started
                        </button>
                        <button
                            className="btn btn-outline-light px-4"
                            onClick={onLogout}
                        >
                            Logout
                        </button>
                    </div>
                </div>
            </section>

            {/* FEATURES SECTION */}
            <section className="py-5 bg-light">
                <div className="container">
                    <h2 className="text-center mb-5 fw-bold">
                        Our Features
                    </h2>

                    <div className="row g-4">
                        <div className="col-md-4">
                            <div className="card feature-card h-100">
                                <div className="card-body text-center">
                                    <h4>🔐 Secure Login</h4>
                                    <p>
                                        JWT & OAuth based authentication for
                                        maximum security.
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div className="col-md-4">
                            <div className="card feature-card h-100">
                                <div className="card-body text-center">
                                    <h4>💳 Payment</h4>
                                    <p>
                                        Integrated payment system with smooth
                                        checkout experience.
                                    </p>
                                    <Payment />
                                </div>
                            </div>
                        </div>

                        <div className="col-md-4">
                            <div className="card feature-card h-100">
                                <div className="card-body text-center">
                                    <h4>⚡ Fast Performance</h4>
                                    <p>
                                        Spring Boot backend with React frontend
                                        for blazing fast speed.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            {/* STATS SECTION */}
            <section className="stats-section text-white py-5">
                <div className="container">
                    <div className="row text-center">
                        <div className="col-md-4">
                            <h2>10K+</h2>
                            <p>Users</p>
                        </div>
                        <div className="col-md-4">
                            <h2>99.9%</h2>
                            <p>Uptime</p>
                        </div>
                        <div className="col-md-4">
                            <h2>24/7</h2>
                            <p>Support</p>
                        </div>
                    </div>
                </div>
            </section>

            {/* FOOTER */}
            <footer className="bg-dark text-white text-center py-3">
                <p className="mb-0">
                    © 2026 Login System | Built with ❤️ using Spring Boot & React
                </p>
            </footer>
        </div>
    );
}

