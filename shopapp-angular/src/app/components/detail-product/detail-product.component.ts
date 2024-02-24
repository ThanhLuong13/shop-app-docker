import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductImage } from 'src/app/models/productImage';
import { CartService } from 'src/app/service/cart.service';
import { ProductService } from 'src/app/service/product.service';
import { environment } from 'src/environments/environment';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenService } from 'src/app/service/token.service';


@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.scss']
})
export class DetailProductComponent implements OnInit {
  product?: Product;
  productId: number = 0;
  currentImageIndex: number = 0
  quantity: number = 1;

  constructor(private productService: ProductService,
    private cartService: CartService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private tokenService: TokenService) { }

  ngOnInit() {
    debugger
    const idParam = this.activatedRoute.snapshot.paramMap.get('id')
    if (idParam !== null) {
      this.productId = +idParam
    }
    if (!Number.isNaN(this.productId)) {
      this.productService.getProductDetail(this.productId).subscribe({
        next: (response: any) => {
          debugger
          if (response.product_images && response.product_images.length > 0) {
            response.product_images.forEach((productImage: ProductImage) => {
              productImage.imageUrl = `${environment.apiBaseUrl}/products/images/${productImage.imageUrl}`
            })
          }
          this.product = response
          this.showImage(0)
        },
        complete: () => {
          debugger
        },
        error: (error: any) => {
          alert(`Cannot get product detail, error: ${error.error}`)
        }
      })
    }
  }
  showImage(index: number): void {
    if (this.product && this.product.product_images && this.product.product_images.length > 0) {
      if (index < 0) {
        index = 0
      } else if (index >= this.product.product_images.length) {
        index = this.product.product_images.length - 1
      }

      this.currentImageIndex = index
    }
  }

  thumbnailClick(index: number): void {
    this.currentImageIndex = index
  }

  nextImage() {
    this.showImage(this.currentImageIndex + 1)
  }

  previousImage() {
    this.showImage(this.currentImageIndex - 1)
  }

  addToCart() {
    debugger
    const userId = this.tokenService.getUserId()
    if (this.product) {
      this.cartService.addToCart(this.product.id, this.quantity)
      alert(`Thêm sản phẩm ${this.product.name} với số lượng ${this.quantity} thành công`)
    } else {
      console.log("Can not add to cart");
    }
  }

  inscreaseQuantity(): void {
    this.quantity++
  }

  desreaseQuantity(): void {
    if (this.quantity > 1) {
      this.quantity--
    }
  }

  buyNow(): void {
    this.router.navigate(['/orders'])
  }
}
