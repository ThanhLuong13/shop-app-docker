import { Category } from "./category"
import { ProductImage } from "./productImage"


export interface Product {
  id: number
  name: string
  price: number
  description: string
  category: Category
  url: string
  thumbnail: string
  product_images: ProductImage[]
}
