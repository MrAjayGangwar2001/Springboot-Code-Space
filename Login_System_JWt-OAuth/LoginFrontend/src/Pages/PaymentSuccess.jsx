import { useSearchParams } from "react-router-dom";
import { useEffect, useState } from "react";

export default function PaymentSuccess() {
    const [params] = useSearchParams();
    const sessionId = params.get("session_id");
    const [data, setData] = useState(null);

    useEffect(() => {
        if (!sessionId) return;

        fetch(`http://localhost:8080/api/payment/session/${sessionId}`)
            .then(res => res.json())
            .then(setData)
            .catch(console.error);
    }, [sessionId]);

    if (!data) return <h2>Loading receipt...</h2>;

    return (
        <div>
            <h1>✅ Payment Successful</h1>
            <p>Amount: ₹{data.amount / 100}</p>
            <p>Email: {data.email}</p>
            <p>Payment ID: {data.paymentId}</p>
            <a href={data.receiptUrl} target="_blank">
                View Official Receipt
            </a>
        </div>
    );
}
