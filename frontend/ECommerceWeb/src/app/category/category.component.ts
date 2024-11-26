import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { ProductService } from '../services/product.service';
import { NgFor, NgIf } from '@angular/common';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { Product } from '../models/product.model';
import { CartService } from '../services/cart.service';
import { CartItem } from '../models/cart-item.model';

@Component({
  selector: 'app-category',
  standalone: true,
  imports: [NgFor, NgIf, MatProgressSpinnerModule],
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent implements OnInit {

  @ViewChild('productsSection') productsSection!: ElementRef;

  categories: any[] = [];
  products: Product[] = [];
  selectedCategory: string = '';
  isLoading: boolean = false;
  isLoggedIn: boolean = false;

  categoryImages: { [key: string]: string } = {
    'Electronics': 'assets/images/electronics.jpg',
    'Groceries': 'assets/images/grocery.jpg',
    'Clothing': 'assets/images/clothing.jpg',
    'Beauty': 'assets/images/beauty.jpeg',
    'Home & Kitchen': 'assets/images/home.jpeg',

  };

  constructor(
    private categoryService: CategoryService,
    private productService: ProductService,
    private cartService: CartService,
  ) {}

  ngOnInit(): void {
    this.loadCategories();
    this.checkLoginStatus();
  }

  ngAfterViewInit(): void {
  }

  loadCategories(): void {
    this.categoryService.getCategories().subscribe({
      next: (data) => {
        this.categories = data;
      },
      error: (error) => {
        console.error('Error fetching categories:', error);
      }
    });
  }

  onCategoryClick(categoryId: number, categoryName: string): void {
    this.isLoading = true;
    this.selectedCategory = categoryName;
    setTimeout(() => {
      this.productService.getProductsByCategory(categoryId).subscribe({
        next: (data) => {
          this.products = data;
          this.isLoading = false;
          setTimeout(() => this.scrollToProducts(), 0); 
        },
        error: (error) => {
          console.error('Error fetching products by category:', error);
          this.isLoading = false;
        }
      });
    }, 500); 
  }

  getCategoryImage(categoryName: string): string {
    return this.categoryImages[categoryName]; 
  }

  scrollToProducts(): void {
    if (this.productsSection) {
      this.productsSection.nativeElement.scrollIntoView({ behavior: 'smooth' });
    } else {
      console.error('productsSection is not defined');
    }
  }

  checkLoginStatus(): void {
    this.isLoggedIn = localStorage.getItem('token') ? true : false;
  }

  addToCart(productId: number): void {
    const userId = this.getUserId();
    if (userId) {
      const cartItem: CartItem = {
        userId: userId,
        productId: productId,
        quantity: 1,
      };
  
      this.cartService.addToCart(cartItem).subscribe({
        next: (data) => {
          console.log('Product added to cart:', data);
          alert('Product added to cart!');
        },
        error: (error) => {
          console.error('Error adding to cart:', error);
          alert('Failed to add product to cart');
        },
      });
    } else {
      alert('User not logged in');
    }
  }
  
  
  getUserId(): number {
    return parseInt(localStorage.getItem('user_id') || '0');
  }
  
}
