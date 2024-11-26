import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
    private usernameSubject = new BehaviorSubject<string | null>(null);
    currentUsername$ = this.usernameSubject.asObservable();
  
    setUsername(username: string | null): void {
      this.usernameSubject.next(username);
    }
  
    clearUsername(): void {
      this.usernameSubject.next(null);
    }
}
