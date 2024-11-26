import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ButtonsModule } from '@progress/kendo-angular-buttons';
import { TextBoxModule } from '@progress/kendo-angular-inputs';
import { emailExistsValidator } from '../email-exists.validator';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-update-user',
  standalone: true,
  imports: [ReactiveFormsModule, NgIf, TextBoxModule, ButtonsModule, RouterLink],
  templateUrl: './update-user.component.html',
  styleUrl: './update-user.component.css'
})
export class UpdateUserComponent { 

  updateForm: FormGroup;
  isSubmitted = false;
  errorMessage: string | null = null;

  constructor(private formBuilder: FormBuilder, private userService: UserService) {
    this.updateForm = this.formBuilder.group({
      userName: ['', [Validators.required, Validators.minLength(3)]],
      userEmail: ['', [Validators.required, Validators.email], [emailExistsValidator(this.userService)]],
      userPassword: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(8)]],
      userPhoneNumber: ['', [Validators.required, Validators.pattern("^\\+?[0-9]{10,13}$")]],
      userAddress: ['', [Validators.required]],
    }, { validator: this.passwordMatchValidator });
  }

  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('userPassword')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;
    return password === confirmPassword ? null : { mismatch: true };
  }

  onUpdateProfile() {

  }
}
