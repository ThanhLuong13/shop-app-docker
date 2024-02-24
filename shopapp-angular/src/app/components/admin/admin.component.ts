import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  adminComponent: string = 'orders'

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  showAdmincomponent(component: string): void {
    this.adminComponent = component;
    this.router.navigate([`/admin/${component}`]);
  }
}
