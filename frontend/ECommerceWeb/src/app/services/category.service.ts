import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class CategoryService {
    private apiUrl = 'http://localhost:8080/api/categories';

    constructor(private http: HttpClient) {}

    getCategories(): Observable<any> {
        return this.http.get(this.apiUrl);
    }

    addCategory(categoryData: FormData) {
      const token = localStorage.getItem('token'); 

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
      return this.http.post(this.apiUrl, categoryData, {headers});
    }
}
