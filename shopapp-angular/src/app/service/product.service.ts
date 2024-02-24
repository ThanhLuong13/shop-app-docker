import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private getProductsApi = `${environment.apiBaseUrl}/products`;

  constructor(private http: HttpClient) { }
  //getter/setter
  getProducts(keyword: string, categoryId: number, page: number, limit: number): Observable<any> {
    let params = new HttpParams();
    params = params.set('page', page.toString());
    params = params.set('limit', limit.toString());
    params = params.set('keyword', keyword);
    params = params.set('categoryId', categoryId.toString())
    return this.http.get<Product[]>(this.getProductsApi, { params });
  }

  getProductDetail(productId: number): Observable<any> {
    return this.http.get(`${environment.apiBaseUrl}/products/${productId}`)
  }

  getProductsByIds(productsIds: number[]): Observable<any> {
    return this.http.get(`${environment.apiBaseUrl}/products/ids/${productsIds.toString()}`)
  }


}
