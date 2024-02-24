import {
  IsString,
  IsNotEmpty,
  IsPhoneNumber,
  IsDate
} from 'class-validator';
import { Product } from 'src/app/models/product';

export class OrderDto {

  user_id: number

  fullname: string

  email: string

  phone_number: string

  address: string

  note: string

  total_price: number

  payment_method: string

  shipping_method: string

  shipping_address: string

  coupon_code: string

  cart_items: { product_id: number, quantity: number }[]

  constructor(data: any) {
    this.user_id = data.user_id;
    this.fullname = data.fullname;
    this.email = data.email;
    this.phone_number = data.phone_number;
    this.address = data.address;
    this.note = data.note;
    this.total_price = data.total_price;
    this.payment_method = data.payment_method;
    this.shipping_method = data.shipping_method;
    this.shipping_address = data.shipping_address;
    this.coupon_code = data.coupon_code;
    this.cart_items = data.cart_items || [];
  }
}
