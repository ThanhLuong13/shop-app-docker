import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisterDTO } from '../DTO/users/registerDto';
import { LoginDto } from '../DTO/users/loginDto';
import { environment } from 'src/environments/environment';
import { UserResponse } from '../responses/user/userResponse';
import { UpdateUserDto } from '../DTO/users/updateUserDto';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiRegister = `${environment.apiBaseUrl}/users/register`
  private apiLogin = `${environment.apiBaseUrl}/users/login`
  private apiUserDetail = `${environment.apiBaseUrl}/users/detail`
  private apiConfig = {
    headers: this.createHeader()
  }
  constructor(private http: HttpClient) { }

  private createHeader(): HttpHeaders {
    return new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  register(registerData: RegisterDTO): Observable<any> {
    return this.http.post(this.apiRegister, registerData, this.apiConfig)
  }

  login(loginData: LoginDto): Observable<any> {
    return this.http.post(this.apiLogin, loginData, this.apiConfig)
  }

  getUserDetails(token: string): Observable<any> {
    return this.http.get(this.apiUserDetail)
  }

  saveUserToLocalStorage(userResponse?: UserResponse) {
    try {
      if (!userResponse || userResponse === null) {
        return;
      }
      const userResponseJSON = JSON.stringify(userResponse);
      localStorage.setItem('user', userResponseJSON)
      console.log("Saved user details to local storage successfully");
    } catch (error) {
      console.log("Failed to save user details to local storage");
    }
  }

  getUserFromLocalStorage(): UserResponse | null {
    try {
      debugger
      const userResponseJSON = localStorage.getItem('user')
      if (userResponseJSON === null || userResponseJSON === undefined) {
        return null
      }
      const user = JSON.parse(userResponseJSON)
      console.log("Get user from local storage successfully");
      return user
    } catch (error) {
      console.log("Failed to get user from local storage");
      return null
    }
  }

  removeUserFromLocalStorage(): void {
    try {
      localStorage.removeItem('user')
      console.log("Removing user from local storage successfully");
    } catch (error) {
      console.log("Removing user from local storage error: " + error);
    }
  }

  updateUserDetails(token: string, updateUser: UpdateUserDto): Observable<any> {
    debugger
    const user = this.getUserFromLocalStorage()
    return this.http.put(`${environment.apiBaseUrl}/${user?.id}`, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`
      })
    })
  }
}

// , {
//   headers: { 'Authorization': `Bearer  + ${token}` },
// }
