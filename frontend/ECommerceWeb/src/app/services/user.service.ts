import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest, User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:8080/api/app_users'; 

  constructor(private http: HttpClient) {}

  register(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, user);
  }

  login(data: LoginRequest): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/login`, data);
  }

  checkEmailExists(email: string) {
    return this.http.post(`${this.baseUrl}/check-email`, { email });
  }

  // resetPassword(email: string): Observable<any> {
  //   return this.http.post(`${this.baseUrl}/reset-password`, { email });
  // }
  
}
