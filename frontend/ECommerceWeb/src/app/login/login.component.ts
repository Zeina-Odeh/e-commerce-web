import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../services/user.service';
import { LoginRequest, User } from '../models/user.model';
import { RouterLink, Router } from '@angular/router';
import { TextBoxModule } from '@progress/kendo-angular-inputs';
import { ButtonsModule } from '@progress/kendo-angular-buttons';
import { AdminService } from '../services/admin.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, TextBoxModule, ButtonsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})

export class LoginComponent {
  loginForm: FormGroup;
  isSubmitted = false;
  errorMessage: string | null = null;

  constructor(
    private formBuilder: FormBuilder, 
    private authService: UserService,
    private router: Router,
    private adminService: AdminService
  ){
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  get formControls() {
    return this.loginForm.controls;
  }

  onLogin(): void {
    this.isSubmitted = true;
    if (this.loginForm.invalid) {
      return;
    }

    const loginData: LoginRequest = {
      email: this.loginForm.value.email,
      password: this.loginForm.value.password
    };

    this.authService.login(loginData).subscribe({
      next: (response: User) => { 
        this.errorMessage = '';
        console.log('Login successful:', response);
        const expirationTime = new Date().getTime() + (60 * 60 * 1000);

      localStorage.setItem('roles', response.roles);
      localStorage.setItem('user_id', response.userId.toString());
      localStorage.setItem('token', response.token);
      localStorage.setItem('tokenExpiration', expirationTime.toString());
      localStorage.setItem('username', response.username);
      localStorage.setItem('email', response.email);
      localStorage.setItem('userPhoneNumber', response.userPhoneNumber);
      localStorage.setItem('userAddress', response.userAddress);
        
        this.adminService.setUsername(response.username);
        this.router.navigate(['/home']);
      },
      error: (error) => {
        this.errorMessage = error.error?.message || 'Email or Password are incorrect. Please try again.';
        console.error('Login error:', error);
      },
    });
  }
}
