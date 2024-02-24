import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Product } from '../models/product';
import { ProductService } from './product.service';
import { ReturnStatement } from '@angular/compiler';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(private productService: ProductService, private tokenService: TokenService) { }

  private getUserId(): number {
    return this.tokenService.getUserId();
  }

  addToCart(productId: number, quantity: number): void {
    debugger
    const cart = this.getCart() || new Map<number, number>();
    if (cart.has(productId)) {
      cart.set(productId, cart.get(productId)! + quantity);
    } else {
      cart.set(productId, quantity);
    }
    this.saveCartToLocalStorage(cart);
  }

  getCart(): Map<number, number> {
    const userId = this.getUserId();
    const userCartData = localStorage.getItem(`cart_${userId}`);
    if (userCartData) {
      return new Map(JSON.parse(userCartData));
    }
    return new Map<number, number>();
  }



  clearCart(): void {
    const userId = this.getUserId();
    if (userId !== null) {
      localStorage.removeItem(`cart_${userId}`);
    }
  }

  private saveCartToLocalStorage(cart: Map<number, number>): void {
    const userId = this.getUserId();
    if (userId !== null) {
      localStorage.setItem(`cart_${userId}`, JSON.stringify(Array.from(cart.entries())));
    }
  }
}

