import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserResponse } from 'src/app/responses/user/userResponse';
import { TokenService } from 'src/app/service/token.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  user?: UserResponse | null
  activeNavItem: number = 0;
  constructor(private tokenService: TokenService, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    debugger
    this.user = this.userService.getUserFromLocalStorage();
    const name = this.user?.fullname
  }

  logoutClick() {
    this.userService.removeUserFromLocalStorage()
    this.tokenService.removeToken()
  }

  setActiveNavItem(index: number): void {
    this.activeNavItem = index
  }

  profileClick() {
    this.router.navigate(['/profile'])
  }

  isExpiredToken(): boolean {
    return this.tokenService.isTokenExpired()
  }
}
