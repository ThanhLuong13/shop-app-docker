import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UpdateUserDto } from 'src/app/DTO/users/updateUserDto';
import { UserResponse } from 'src/app/responses/user/userResponse';
import { TokenService } from 'src/app/service/token.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  userProfileForm: FormGroup
  token: string = ''
  userResponse?: UserResponse

  constructor(private userService: UserService,
    private tokenService: TokenService,
    private fb: FormBuilder,
    private router: Router) {
    // Tao form froup
    this.userProfileForm = this.fb.group({
      fullname: ['', [Validators.required]],
      //email: ['', [Validators.required, Validators.email]],
      //phone_number: ['', [Validators.required, Validators.minLength(6)]],
      password: ['', [Validators.minLength(3)]],
      retype_password: ['', [Validators.minLength(3)]],
      address: ['', [Validators.required]],
      date_of_birth: [Date.now()]
    }, {
      validators: this.passwordMatchValidator
    });
  }

  ngOnInit(): void {
    this.token = this.tokenService.getToken() ?? ''
    this.userService.getUserDetails(this.token).subscribe({
      next: (response: UserResponse) => {
        debugger
        this.userResponse = {
          ...response,
          date_of_birth: new Date(response.date_of_birth)
        }
        this.userProfileForm.patchValue({
          fullname: this.userResponse?.fullname ?? '',
          address: this.userResponse?.address ?? '',
          phone_number: this.userResponse?.phone_number ?? '',
          date_of_birth: this.userResponse?.date_of_birth.toISOString().substring(0, 10)
        })
        this.userService.saveUserToLocalStorage(this.userResponse)
      }, complete: () => {
        debugger
      },
      error: (error: any) => {
        alert(`Cannot login, error: ${error.error}`)
      }
    })
  }

  passwordMatchValidator(): ValidatorFn {
    debugger
    return (formGroup: AbstractControl): ValidationErrors | null => {
      const password = formGroup.get('password')?.value
      const retypePassword = formGroup.get('retype_password')?.value
      if (password !== retypePassword) {
        return { passWordMissmatch: true }
      }
      return null
    }
  }

  save() {
    debugger
    if (this.userProfileForm.valid) {
      const updateUser: UpdateUserDto = {
        fullname: this.userProfileForm.get('fullname')?.value,
        address: this.userProfileForm.get('address')?.value,
        password: this.userProfileForm.get('password')?.value,
        retype_password: this.userProfileForm.get('retype_password')?.value,
        date_of_birth: this.userProfileForm.get('date_of_birth')?.value,
      }
      this.userService.updateUserDetails(this.token, updateUser).subscribe({
        next: (response) => {
          this.userService.removeUserFromLocalStorage()
          this.tokenService.removeToken()
          alert("Cập nhật thông tin thành công")
          this.router.navigate(['/login'])
        },
        error: (error: any) => {
          alert(error.error)
        }
      })
    } else {
      if (this.userProfileForm.hasError('passwordMissmatch')) {
        alert("Nhập lại mật khẩu chưa chính xác")
      }
    }
  }
}
