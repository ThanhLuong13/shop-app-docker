import { Product } from "./product";


export interface OrderDetail {
  id: number;
  order_id: number;
  product: Product;
  price: number;
  number_of_products: number;
  total_price: number;
  color: string;
}

