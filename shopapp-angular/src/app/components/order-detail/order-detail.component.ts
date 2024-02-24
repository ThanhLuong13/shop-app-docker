import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { CartService } from 'src/app/service/cart.service';
import { ProductService } from 'src/app/service/product.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.scss']
})
export class OrderDetailComponent implements OnInit {

  cartItems: { product: Product, quantity: number }[] = []
  totalAmount: number = 0
  couponCode: string = ''

  constructor(private cartService: CartService, private productService: ProductService) { }

  ngOnInit(): void {
    const cart = this.cartService.getCart()
    const productIds = Array.from(cart.keys())
    debugger
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

  applyCoupon() { }
}




