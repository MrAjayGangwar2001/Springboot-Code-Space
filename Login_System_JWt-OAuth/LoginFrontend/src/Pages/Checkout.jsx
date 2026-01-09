import { useStripe, useElements, PaymentElement } from "@stripe/react-stripe-js";

export default function Checkout() {
    const stripe = useStripe();
    const elements = useElements();

    const handlePay = async () => {
        const res = await fetch("http://localhost:8080/api/payment/create", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ amount: 50000 })
        });

        const { clientSecret } = await res.json();

        await stripe.confirmPayment({
            elements,
            confirmParams: {
                return_url: "http://localhost:5173/payment-success"
            }
        });
    };

    return (
        <>
            <PaymentElement />
            <button onClick={handlePay}>Pay ₹500</button>
        </>
    );
}
