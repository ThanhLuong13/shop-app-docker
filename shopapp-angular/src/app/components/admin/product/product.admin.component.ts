import { Component, OnInit, numberAttribute } from "@angular/core";
import { Router } from "@angular/router";
import { Category } from "src/app/models/category";
import { Product } from "src/app/models/product";
import { CategoryService } from "src/app/service/category.service";
import { ProductService } from "src/app/service/product.service";
import { environment } from "src/environments/environment";

@Component({
  selector: 'app-product-admin',
  templateUrl: './product.admin.component.html',
  styleUrls: ['./product.admin.component.scss']
})

export class ProductAdminComponent implements OnInit {
  products: Product[] = [];
  categories: Category[] = [];
  currentPage: number = 1;
  itemsPerPage: number = 9;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  keyword: string = "";
  selectedCategoryId: number = 0

  constructor(private productService: ProductService, private categoryService: CategoryService, private router: Router) { }

  ngOnInit() {
    debugger
    this.getCategories(this.currentPage, this.itemsPerPage)
    this.getProducts(this.keyword, this.selectedCategoryId, this.currentPage, this.itemsPerPage)
  }

  getCategories(page: number, limit: number) {
    this.categoryService.getCategories(page, limit).subscribe({
      next: (categories: Category[]) => {
        debugger
        this.categories = categories
      },
      complete: () => {
        debugger
      },
      error: (error: any) => {
        alert(`Cannot get categories, error: ${error.error}`)
      }
    })
  }

  getProducts(keyword: string, selectedCategoryId: number, page: number, limit: number) {
    this.productService.getProducts(keyword, selectedCategoryId, page, limit).subscribe({
      next: (response: any) => {
        debugger
        response.products.forEach((product: Product) => {
          product.url = `${environment.apiBaseUrl}/products/images/${product.thumbnail}`
        });
        this.products = response.products
        this.totalPages = response.totalPages
        this.visiblePages = this.generateVisiblePagesArray(this.currentPage, this.totalPages)
      },
      complete: () => {
        debugger
      },
      error: (error: any) => {
        alert(`Cannot get Products, error: ${error.error}`)
      }
    })
  }

  onPageChange(page: number) {
    debugger
    this.currentPage = page
    this.getProducts(this.keyword, this.selectedCategoryId, this.currentPage, this.itemsPerPage)
  }

  searchProducts() {
    this.currentPage = 1
    this.itemsPerPage = 12
    debugger
    this.getProducts(this.keyword, this.selectedCategoryId, this.currentPage, this.itemsPerPage)
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
}
