import { OrderDetail } from "src/app/models/orderDetail";

export interface OrderResponse {
  id: number;
  user_id: number;
  fullname: string;
  email: string;
  phone_number: string;
  address: string;
  note: string;
  order_date: string;
  total_price: number;
  status: string
  shipping_method: string;
  shipping_address: string;
  shipping_date: String;
  payment_method: string;
  order_details: OrderDetail[]
}
