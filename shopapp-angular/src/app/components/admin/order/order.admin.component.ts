import { Component, OnInit, numberAttribute } from "@angular/core";
import { Router } from "@angular/router";
import { OrderResponse } from "src/app/responses/order/orderResponse";
import { OrderService } from "src/app/service/order.service";

@Component({
  selector: 'app-order-admin',
  templateUrl: './order.admin.component.html',
  styleUrls: ['./order.admin.component.scss']
})

export class OrderAdminComponent implements OnInit {
  orders: OrderResponse[] = [];
  currentPage: number = 1;
  itemsPerPage: number = 9;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  keyword: string = "";

  constructor(private orderService: OrderService, private router: Router) { }


  ngOnInit(): void {
    debugger
    this.getAllOrders(this.keyword, this.currentPage, this.itemsPerPage)
  }

  getAllOrders(keyword: string, page: number, limit: number): void {
    this.orderService.getOrders(keyword, page, limit).subscribe({
      next: (response: any) => {
        debugger
        this.orders = response.orders
        this.totalPages = response.totalPages
        this.visiblePages = this.generateVisiblePagesArray(this.currentPage, this.totalPages)
      },
      complete: () => {
        debugger
      },
      error: (error: any) => {
        alert(`Cannot get Order, error: ${error.error}`)
      }
    })
  }


  onPageChange(page: number) {
    debugger
    this.currentPage = page
    this.getAllOrders(this.keyword, this.currentPage, this.itemsPerPage)
  }

  generateVisiblePagesArray(currentPage: number, totalPages: number): number[] {
    debugger
    const maxVisiablePages = 5
    const halfVisiablePages = Math.floor(maxVisiablePages / 2)

    let startPage = Math.max(currentPage - halfVisiablePages, 1)
    let endPage = Math.min(startPage + maxVisiablePages - 1, totalPages)

    if (endPage - startPage + 1 < maxVisiablePages) {
      startPage = Math.max(endPage - maxVisiablePages + 1, 1)
    }
    return new Array(endPage - startPage + 1).fill(0).map((_, index) => startPage + index);
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleString(); // Sử dụng phương thức toLocaleString để định dạng ngày và giờ
  }

  searchOrder() {
    this.currentPage = 1
    this.itemsPerPage = 10
    this.getAllOrders(this.keyword, this.currentPage, this.itemsPerPage)
  }

  getOrderDetails(orderId: number) {
    this.router.navigate([`admin/orders/${orderId}`])
  }

  deleteOrder(orderId: number) {
    const confirmation = window.confirm("Xoá order này")
    debugger
    if (confirmation) {
      this.orderService.deleteOrder(orderId).subscribe({
        next: (response: any) => {
          alert("Delete order success")
          debugger
          window.location.reload()
        },
        complete: () => {
          debugger
        },
        error: (error: any) => {
          alert(`Cannot get delete, error: ${error.error}`)
        }
      })
    }
  }

}
