import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private readonly TOKEN_KEY = 'access_token';

  private jwtHelper = new JwtHelperService();
  constructor() { }
  //getter/setter
  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }
  setToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }
  removeToken(): void {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  getUserId(): number {
    debugger
    const token = this.getToken();
    if (token === null) {
      return 0
    } else {
      const userObject = this.jwtHelper.decodeToken(token)
      return 'user_id' in userObject ? parseInt(userObject['user_id']) : 0
    }
  }

  isTokenExpired(): boolean {
    if (this.getToken() === null) {
      return false;
    }
    return this.jwtHelper.isTokenExpired(this.getToken());
  }
}
