import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { DropDownListModule } from '@progress/kendo-angular-dropdowns';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ProductListComponent } from "../product-list/product-list.component";
import { CategoryComponent } from "../category/category.component";
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-home',
    standalone: true,
    imports: [DropDownListModule, 
              FormsModule, RouterLink, MatIconModule, MatInputModule,
              ProductListComponent, CategoryComponent, CommonModule],
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  @ViewChild('categorySection') categorySection!: ElementRef;

  categories: any[] = [];
  selectedCategory: any = null;
  dropdownOpen = false;
  username: string | null = null;
  isAdmin: boolean = false;
  accountDropdownOpen: boolean = false;
  showProducts: boolean = false; 

  constructor(
    private categoryService: CategoryService
  ) {}

  ngOnInit(): void {
    this.loadCategories();

    if (this.isLoggedIn) {
      this.username = localStorage.getItem('username');
      this.isAdmin = localStorage.getItem('roles')?.includes('admin') || false;
    }
  }

  get isLoggedIn(): boolean {
    const tokenExpiration = localStorage.getItem('tokenExpiration');
    if (!tokenExpiration) return false;

    const isExpired = new Date().getTime() > parseInt(tokenExpiration);
    if (isExpired) {
        console.log('Not logged in: token expired.');
        this.logout(); 
        return false;
    }
    return true; 
   }

  
  toggleAccountDropdown(): void {
    this.accountDropdownOpen = !this.accountDropdownOpen;
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

  toggleDropdown(): void {
    this.dropdownOpen = !this.dropdownOpen;
  }

  openDropdown(type: string): void {
    if (type === 'category') {
      this.dropdownOpen = true;
    } else if (type === 'account') {
      this.accountDropdownOpen = true;
    }
  }

  closeDropdown(type: string): void {
    if (type === 'category') {
      this.dropdownOpen = false;
    } else if (type === 'account') {
      this.accountDropdownOpen = false;
    }
  }
  
  selectCategory(category: any): void {
    this.selectedCategory = category;
  }

  getCategoryImage(category: any): void {
    return category.imageUrl;
  }

  logout(): void {
    localStorage.clear();
    window.location.reload();
  }

  toggleProducts() {
    this.showProducts = !this.showProducts;
  }  

  scrollToCategories(): void {
    this.categorySection.nativeElement.scrollIntoView({ behavior: 'smooth' });
  }


}