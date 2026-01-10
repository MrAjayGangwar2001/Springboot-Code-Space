import Payment from "./Payment"

export default function Home({ email, onLogout }) {
    return (
        <div style={styles.container}>
            <div style={styles.card}>
                <h1>🎉 Welcome!</h1>
                <p>You are logged in as</p>
                <strong>{email}</strong>
                <br />
                <Payment />


                <button onClick={onLogout} style={styles.btn}>
                    Logout
                </button>
            </div>
        </div>
    );
}

const styles = {
    container: {
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        background: "linear-gradient(135deg,#667eea,#764ba2)",
    },
    card: {
        background: "#29e075ff",
        padding: "40px",
        borderRadius: "12px",
        textAlign: "center",
        boxShadow: "0 15px 30px rgba(0,0,0,.2)",
    },
    btn: {
        width: "30%",
        margin: "20px",
        padding: "10px 20px",
        border: "none",
        background: "#667eea",
        color: "#fff",
        borderRadius: "6px",
        cursor: "pointer",

    },
};
