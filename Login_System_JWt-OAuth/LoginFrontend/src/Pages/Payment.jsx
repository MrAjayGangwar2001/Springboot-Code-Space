import { Elements } from "@stripe/react-stripe-js";
import { stripePromise } from "./stripe";

<Elements stripe={stripePromise}>
   <Checkout />
</Elements>
