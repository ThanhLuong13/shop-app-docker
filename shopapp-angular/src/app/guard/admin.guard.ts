import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";
import { TokenService } from "../service/token.service";
import { Injectable, inject } from "@angular/core";
import { UserResponse } from "../responses/user/userResponse";
import { UserService } from "../service/user.service";

@Injectable({
  providedIn: "root",
})

export class AdminGuard {

  user?: UserResponse | null

  constructor(private tokenService: TokenService,
    private userService: UserService,
    private router: Router) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    debugger
    const isTokenExpired = this.tokenService.isTokenExpired()
    const isUserValid = this.tokenService.getUserId() > 0
    this.user = this.userService.getUserFromLocalStorage()
    const isAdmin = this.user?.role.id == 1
    debugger
    if (!isTokenExpired && isUserValid && isAdmin) {
      return true
    } else {
      this.router.navigate(['/login'])
    }
    return false
  }
}

export const AdminGuardFn: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  return inject(AdminGuard).canActivate(next, state)
}
