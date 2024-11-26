// import { Component } from '@angular/core';
// import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
// import { UserService } from '../user.service';
// import { Router, RouterLink } from '@angular/router';
// import { NgIf } from '@angular/common';

// @Component({
//   selector: 'app-forgot-password',
//   standalone: true,
//   imports: [ ReactiveFormsModule, NgIf, RouterLink],
//   templateUrl: './forgot-password.component.html',
//   styleUrl: './forgot-password.component.css'
// })
// export class ForgotPasswordComponent {

//   forgotPasswordForm: FormGroup;
//   emailSent = false;
//   errorMessage = '';

//   constructor(
//     private formBuilder: FormBuilder, 
//     private userService: UserService, 
//     private router: Router) 
//     {
//       this.forgotPasswordForm = this.formBuilder.group({
//         userEmail: ['', [Validators.required, Validators.email]],
//       });
//     }

//     onSubmit() {
//       const email = this.forgotPasswordForm.value.userEmail;

//       this.userService.resetPassword(email).subscribe(
//         () => {
//           this.emailSent = true;
//         },
//         (error) => {
//           this.errorMessage = 'Failed to send reset email. Try again later.';
//         }
//       );
//     }
// }
