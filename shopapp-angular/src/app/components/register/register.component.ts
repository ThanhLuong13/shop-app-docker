import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserService } from '../../service/user.service';
import { RegisterDTO } from '../../DTO/users/registerDto';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  @ViewChild('registerForm') registerForm!: NgForm

  phoneNumber: string;
  password: string;
  retypePassword: string;
  fullName: string;
  address: string;
  isAccept: boolean;
  dateOfBirth: Date;

  constructor(private router: Router, private userService: UserService) {
    this.phoneNumber = '';
    this.password = '';
    this.retypePassword = '';
    this.fullName = '';
    this.address = '';
    this.isAccept = false;
    this.dateOfBirth = new Date();
  }

  onPhoneNumberChange() { }

  register() {
    const registerData: RegisterDTO = {
      "fullname": this.fullName,
      "phone_number": this.phoneNumber,
      "password": this.password,
      "retype_password": this.retypePassword,
      "address": this.address,
      "date_of_birth": this.dateOfBirth,
      "facebook_account_id": 0,
      "google_account_id": 0,
      "role_id": 2
    }
    this.userService.register(registerData).subscribe({
      next: (response: any) => {
        debugger
        if (response && (response.status === 200 || response.status === 201)) {
          this.router.navigate(["/login"])
        } else {

        }
      },
      complete: () => {
        debugger
      },
      error: (error: any) => {
        alert(`Cannot register, error: ${error.error}`)
      }
    })

  }

  checkPasswordMatch() {
    if (this.password !== this.retypePassword) {
      this.registerForm.controls['retypePassword'].setErrors({ 'passwordNotMatch': true })
    } else {
      this.registerForm.controls['retypePassword'].setErrors({ 'passwordNotMatch': false })
    }

  }
}
