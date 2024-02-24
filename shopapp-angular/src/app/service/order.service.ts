import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Product } from '../models/product';
import { OrderDto } from '../DTO/orders/orderDto';
import { UpdateOrderStatusDto } from '../DTO/orders/updateOrderStatusDto';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private orderApi = `${environment.apiBaseUrl}/orders`;

  constructor(private http: HttpClient) { }
  createOrder(orderData: OrderDto): Observable<any> {
    return this.http.post(this.orderApi, orderData)
  }

  getOrders(keyword: string, page: number, limit: number): Observable<any> {
    debugger
    let params = new HttpParams();
    params = params.set('page', page.toString());
    params = params.set('limit', limit.toString());
    params = params.set('keyword', keyword);
    return this.http.get(`${environment.apiBaseUrl}/orders/keyword`, { params });
  }

  getOrderById(orderId: number): Observable<any> {
    debugger
    return this.http.get(`${environment.apiBaseUrl}/orders/${orderId}`)
  }

  updateOrderStatus(orderId: number, updateOrderStatusDto: UpdateOrderStatusDto): Observable<any> {
    return this.http.put(`${environment.apiBaseUrl}/orders/status/${orderId}`, updateOrderStatusDto)
  }

  deleteOrder(orderId: number): Observable<any> {
    return this.http.delete(`${environment.apiBaseUrl}/orders/${orderId}`, { responseType: 'text' })
  }

}
