import { NgModule } from "@angular/core";
import { OrderAdminComponent } from "./order/order.admin.component";
import { ProductAdminComponent } from "./product/product.admin.component";
import { CommonModule } from "@angular/common";
import { AdminRoutingModule } from "./admin-routing.module";
import { AdminComponent } from "./admin.component";
import { FormsModule } from "@angular/forms";
import { OrderDetailAdminComponent } from "./order-detail/order-detail.admin.component";
import { RouterModule } from "@angular/router";


@NgModule({
  declarations: [
    AdminComponent,
    OrderAdminComponent,
    ProductAdminComponent,
    OrderDetailAdminComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    AdminRoutingModule
  ]

})
export class AdminModule { }
