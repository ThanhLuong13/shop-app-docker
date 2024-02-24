import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OrderDto } from 'src/app/DTO/orders/orderDto';
import { Product } from 'src/app/models/product';
import { CartService } from 'src/app/service/cart.service';
import { OrderService } from 'src/app/service/order.service';
import { ProductService } from 'src/app/service/product.service';
import { TokenService } from 'src/app/service/token.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
  orderForm: FormGroup;
  cartItems: { product: Product, quantity: number }[] = []
  totalAmount: number = 0
  couponCode: string = ''

  orderData: OrderDto = {
    user_id: 0,
    fullname: "",
    email: "",
    phone_number: "",
    address: "",
    note: "",
    total_price: 0,
    shipping_method: "",
    shipping_address: "",
    payment_method: "",
    coupon_code: '',
    cart_items: [],
  }
  constructor(private orderService: OrderService,
    private cartService: CartService,
    private productService: ProductService,
    private tokenService: TokenService,
    private fb: FormBuilder,
    private router: Router) {
    // Tao form froup
    this.orderForm = this.fb.group({
      fullname: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      phone_number: ['', [Validators.required, Validators.minLength(6)]],
      address: ['', [Validators.required]],
      note: [''],
      shipping_method: ['Express'],
      payment_method: ['COD']
    });
  }


  ngOnInit(): void {
    debugger
    const userId = this.tokenService.getUserId();
    const cart = this.cartService.getCart()
    const productIds = Array.from(cart.keys())
    this.orderData.user_id = userId
    debugger
    if (productIds.length === 0) { return; }
    this.productService.getProductsByIds(productIds).subscribe({
      next: (products) => {
        debugger
        this.cartItems = productIds.map(productId => {
          const product = products.find((p: Product) => p.id === productId)
          if (product) {
            product.thumbnail = `${environment.apiBaseUrl}/products/images/${product.thumbnail}`
          }
          return {
            product: product!,
            quantity: cart.get(productId)!
          }
        })
      },
      complete: () => {
        debugger
        this.caculateTotalPrice()
      },
      error: (error: any) => {
        alert(`Cannot get product detail, error: ${error.error}`)
      }
    })

  }

  caculateTotalPrice(): void {
    this.totalAmount = this.cartItems.reduce(
      (total, item) => total + item.product.price * item.quantity
      , 0)
  }

  placeOrder(): void {
    debugger
    if (this.orderForm.valid) {
      this.orderData.fullname = this.orderForm.get('fullname')!.value;
      this.orderData.email = this.orderForm.get('email')!.value;
      this.orderData.phone_number = this.orderForm.get('phone_number')!.value;
      this.orderData.address = this.orderForm.get('address')!.value;
      this.orderData.note = this.orderForm.get('note')!.value;
      this.orderData.shipping_method = this.orderForm.get('shipping_method')!.value;
      this.orderData.shipping_address = this.orderForm.get('address')!.value;
    }
    this.orderData.cart_items = this.cartItems.map(item => ({
      product_id: item.product.id,
      quantity: item.quantity
    }))
    this.orderData.total_price = this.totalAmount
    this.orderService.createOrder(this.orderData).subscribe({
      next: (response: any) => {
        debugger
        alert("Create Order successfully");
        const userId = this.tokenService.getUserId();
        this.cartService.clearCart();
        this.router.navigate(['/'])
      },
      complete: () => {
        debugger
      },
      error: (error: any) => {
        alert(`Create order fail, error: ${error.error}`)
      }
    })
  }

  applyCoupon() { }
}
