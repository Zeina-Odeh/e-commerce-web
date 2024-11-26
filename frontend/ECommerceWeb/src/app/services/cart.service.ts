import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CartItem } from '../models/cart-item.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private isLoggedIn: boolean;
  private apiUrl = `http://localhost:8080/api/cart`;

  constructor(private http: HttpClient) {
    this.isLoggedIn = !!localStorage.getItem('token');
  }
  
  addToCart(cartItem: CartItem): Observable<CartItem> {
    const token = localStorage.getItem('token'); 

    return this.http.post<CartItem>(this.apiUrl, cartItem);
  }
}
