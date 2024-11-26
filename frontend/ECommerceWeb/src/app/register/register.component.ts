import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { User } from '../models/user.model';
import { NgIf } from '@angular/common';
import { TextBoxModule } from '@progress/kendo-angular-inputs';
import { ButtonsModule } from '@progress/kendo-angular-buttons';
import { RouterLink } from '@angular/router';
import { emailExistsValidator } from '../email-exists.validator';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, TextBoxModule, ButtonsModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})

export class RegisterComponent {
  // [x: string]: any;
  registerForm: FormGroup;
  isSubmitted = false;
  errorMessage: string | null = null;
  emailExistError: string | null = null;

  constructor(
    private formBuilder: FormBuilder, 
    private router: Router,
    private userService: UserService) 
    {
    this.registerForm = this.formBuilder.group({
      userName: ['', [Validators.required, Validators.minLength(3)]],
      userEmail: ['', [Validators.required, Validators.email], [emailExistsValidator(this.userService)]],
      userPassword: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(8)]],
      userPhoneNumber: ['', [Validators.required, Validators.pattern("^\\+?[0-9]{10,13}$")]],
      userAddress: ['', [Validators.required]],
    }, { validator: this.passwordMatchValidator });
  }

  get formControls() {
    return this.registerForm.controls;
  }
    
  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('userPassword')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { mismatch: true };
  }

  onRegister(): void {
    this.isSubmitted = true;

    if (this.registerForm.invalid) {
      return;
    }

    const newUser: User = {
      token: this.registerForm.value.token,
      roles: this.registerForm.value.roles,
      userId: this.registerForm.value.user_id,
      username: this.registerForm.value.userName, 
      email: this.registerForm.value.userEmail, 
      password: this.registerForm.value.userPassword, 
      userPhoneNumber: this.registerForm.value.userPhoneNumber, 
      userAddress: this.registerForm.value.userAddress,
    };
    
    if(this.registerForm.valid) {
      this.userService.register(newUser).subscribe({
        next: (response) => {
          console.log('User registered successfully:', response);
          this.errorMessage = '';
          this.router.navigate(['/login']);        
        },
        error: (error) => {
          this.errorMessage = error.error?.message;
          console.error('Registration error:', error);
          this.errorMessage = 'Registration failed. Please try again.';
        },
      });
    }
  
  }

}
