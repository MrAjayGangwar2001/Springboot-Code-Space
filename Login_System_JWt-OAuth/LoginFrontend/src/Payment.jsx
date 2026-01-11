import { loadStripe } from "@stripe/stripe-js";


export default function Payment() {

   const handlePayment = async () => {
      console.log("Enter Handle click Button");

      const amount = document.getElementById("amount").value;

      if (!amount || amount <= 0) {
         alert("Minimum Amount should be 10");
         return;
      }

      const res = await fetch("http://localhost:8080/api/payment/create", {
         method: "POST",
         headers: {
            "Content-Type": "application/json",
         },
         body: JSON.stringify({
            amount: Number(amount), email// ₹
         }),
      });
      console.log("After API Fetch");

      const data = await res.json();

      const stripe = await stripePromise;
      await stripe.redirectToCheckout({
         sessionId: data.id,
      });
   };

   return (

      <div style={{ textAlign: "center" }}>
         <h2>Enter Amount (₹)</h2>
         <input
            id="amount"
            type="number"
            placeholder="Enter amount"
            style={{ padding: "10px", margin: "10px" }}
         />
         <br />

         <button
            style={{
               padding: "12px 20px",
               background: "#0f172a",
               color: "#fff",
               borderRadius: "6px",
               marginTop: "20px"
            }}
            onClick={handlePayment}>
            💳 Make Payment
         </button>
      </div>
   );
}
