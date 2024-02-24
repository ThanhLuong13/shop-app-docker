import { Component, OnInit, numberAttribute } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { OrderDto } from "src/app/DTO/orders/orderDto";
import { UpdateOrderStatusDto } from "src/app/DTO/orders/updateOrderStatusDto";
import { OrderDetail } from "src/app/models/orderDetail";
import { OrderResponse } from "src/app/responses/order/orderResponse";
import { OrderService } from "src/app/service/order.service";
import { environment } from "src/environments/environment";

@Component({
  selector: 'app-order-detail-admin',
  templateUrl: './order-detail.admin.component.html',
  styleUrls: ['./order-detail.admin.component.scss']
})

export class OrderDetailAdminComponent implements OnInit {
  orderResponse: OrderResponse = {
    id: 0,
    user_id: 0,
    fullname: "",
    email: "",
    phone_number: "",
    address: "",
    note: "",
    order_date: new Date().toString(),
    total_price: 0,
    status: "",
    shipping_method: "",
    shipping_address: "",
    shipping_date: new Date().toString(),
    payment_method: "",
    order_details: []
  };
  orderId: number = 0

  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute,
    private router: Router) { }


  ngOnInit(): void {
    debugger
    this.getOrderDetail()
  }

  getOrderDetail(): void {
    this.orderId = Number(this.route.snapshot.paramMap.get('id'))
    this.orderService.getOrderById(this.orderId).subscribe({
      next: (response: OrderResponse) => {
        debugger
        // Hoàn thành các trường trong orderResponse từ response
        this.orderResponse.id = response.id;
        this.orderResponse.user_id = response.user_id;
        this.orderResponse.fullname = response.fullname;
        this.orderResponse.email = response.email;
        this.orderResponse.phone_number = response.phone_number;
        this.orderResponse.address = response.address;
        this.orderResponse.note = response.note;
        this.orderResponse.order_date = response.order_date;
        this.orderResponse.total_price = response.total_price;
        this.orderResponse.status = response.status;
        this.orderResponse.shipping_method = response.shipping_method;
        this.orderResponse.shipping_address = response.shipping_address;
        this.orderResponse.shipping_date = response.shipping_date;
        this.orderResponse.payment_method = response.payment_method;
        this.orderResponse.order_details = response.order_details
      },
      complete: () => {
        debugger
      },
      error: (error: any) => {
        alert(`Cannot get Order, error: ${error.error}`)
      }
    })
  }

  saveOrder() {
    debugger
    this.orderService.updateOrderStatus(this.orderId, new UpdateOrderStatusDto(this.orderResponse.status)).subscribe({
      next: (response: OrderResponse) => {
        debugger
        alert(`Order updated successfully`)
        // this.router.navigate(['../'], { relativeTo: this.route })
        window.location.reload()
      },
      complete: () => {
        debugger
      },
      error: (error: any) => {
        alert(`Cannot update, error: ${error.error}`)
      }
    })
  }

}
