

interface OrderResponse {
  id: number;
  userId: number;
  fullName: string;
  email: string;
  phoneNumber: string;
  address: string;
  note: string;
  totalPrice: number;
  shippingMethod: string;
  shippingAddress: string;
  shippingDate: Date;
  paymentMethod: string;
}
