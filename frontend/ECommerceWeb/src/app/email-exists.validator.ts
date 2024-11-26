import { AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { UserService } from './services/user.service'; 
import { map, catchError } from 'rxjs/operators';
import { of } from 'rxjs';

export function emailExistsValidator(userService: UserService): AsyncValidatorFn {
  return (control: AbstractControl) => {
    return userService.checkEmailExists(control.value).pipe(
      map((res: any) => {
        return res.emailExists ? { emailExists: true } : null; 
      }),
      catchError((err) => {
        console.error('Error in validator:', err);
        return of(null);
      })
    );
  };
}

