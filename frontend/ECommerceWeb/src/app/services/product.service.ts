import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseApi = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseApi);
  } 

  getProductsByCategory(categoryId: number): Observable<any> {
    return this.http.get(`${this.baseApi}/category/${categoryId}`);
  }

  addProduct(product: any): Observable<any> {
    return this.http.post<any>(this.baseApi, product);
  }

}
