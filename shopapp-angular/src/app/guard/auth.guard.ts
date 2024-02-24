import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from "@angular/router";
import { TokenService } from "../service/token.service";
import { Injectable, inject } from "@angular/core";

@Injectable({
  providedIn: "root",
})

export class AuthGuard {

  constructor(private tokenService: TokenService, private router: Router) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    debugger
    const isTokenExpired = this.tokenService.isTokenExpired()
    const isUserValid = this.tokenService.getUserId() > 0
    debugger
    if (!isTokenExpired && isUserValid) {
      return true
    } else {
      this.router.navigate(['/login'])
    }
    return false
  }
}

export const AuthGuardFn: CanActivateFn = (next: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  return inject(AuthGuard).canActivate(next, state)
}
