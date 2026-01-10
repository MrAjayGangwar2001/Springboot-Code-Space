import { loadStripe } from "@stripe/stripe-js";


export default function Payment() {

   const handlePayment = async () => {
      console.log("Enter Handle click Button");
      
      const res = await fetch("http://localhost:8080/api/payment/create", {
         method: "POST",
      });
      console.log("After API Fetch");

      const data = await res.json();

      const stripe = await stripePromise;
      await stripe.redirectToCheckout({
         sessionId: data.id,
      });
   };

   return (
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
   );
}
