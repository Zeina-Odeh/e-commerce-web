import { Component } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NotificationService, NotificationModule } from '@progress/kendo-angular-notification';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-category',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, CommonModule, NotificationModule],
  templateUrl: './add-category.component.html',
  styleUrl: './add-category.component.css'
})
export class AddCategoryComponent {
    addCategoryForm: FormGroup;
    categoryName: string = '';
    successMessage: string | null = null;
    errorMessage: string | null = null;
    imagePreview: string | ArrayBuffer | null = null;
    selectedFile!: File;
    imageName: any;
    retrievedImage: any;
    base64Data: any;
    message: string | undefined;

    constructor(
      private formBuilder: FormBuilder, 
      private notificationService: NotificationService,
      private categoryService: CategoryService,
      private httpClient: HttpClient)
      {
      this.addCategoryForm = this.formBuilder.group({
        name: ['', Validators.required]
      });
    }

 
    public onFileSelected(event: any) {
      this.selectedFile = event.target.files[0];

    }
    
    addCategory(): void {
      if (this.addCategoryForm.valid) {
        const formData = new FormData();
        formData.append('category', JSON.stringify({
          name: this.addCategoryForm.get('name')?.value
        }));
    
        // Append the image file to FormData
        if (this.selectedFile) {
          formData.append('image', this.selectedFile, this.selectedFile.name);
        }
    
        // Send the form data to the backend
        this.categoryService.addCategory(formData).subscribe({
          next: (response) => {
            console.log('Category added:', response);
            this.addCategoryForm.reset();
            this.imagePreview = null;
            this.successMessage = 'Category added successfully!';
          },
          error: (error) => {
            console.error('Error adding category:', error);
            this.errorMessage = 'Failed to add category.';
          }
        });
      }
    }
  
  
    // onUpload() {
    //   console.log(this.selectedFile);
      
    //   //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
    //   const uploadImageData = new FormData();
    //   uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
    
    //   //Make a call to the Spring Boot Application to save the image
    //   this.httpClient.post('http://localhost:8080/image/upload', uploadImageData, { observe: 'response' })
    //     .subscribe((response: { status: number; }) => {
    //       if (response.status === 200) {
    //         this.message = 'Image uploaded successfully';
    //       } else {
    //         this.message = 'Image not uploaded successfully';
    //       }
    //     }
    //     );
    // }

    // getImage() {
    //   //Make a call to Sprinf Boot to get the Image Bytes.
    //   this.httpClient.get('http://localhost:8080/image/get/' + this.imageName)
    //     .subscribe(
    //       res => {
    //         this.retrieveResonse = res;
    //         this.base64Data = this.retrieveResonse.picByte;
    //         this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
    //       }
    //     );
    // }


  showNotification(message: string, style: 'success' | 'error') {
    this.notificationService.show({
      content: message,
      hideAfter: 3000,
      position: { horizontal: 'center', vertical: 'top' },
      type: { style: style as 'success' | 'error', icon: true }, 
    });
  }

  clearMessages(): void {
    this.successMessage = null;
    this.errorMessage = null;
  }
}
