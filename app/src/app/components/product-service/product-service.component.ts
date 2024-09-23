import { CommonModule, NgFor } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

interface Product {
  id: number;
  name: string;
  colour: string;
  url: string;
  description: string;
  price: number;
  quantity: number;
  reviewList: [] | feedbackList;
}
interface feedbackList{
  id: number;
  userId: number;
  rating: number;
  review: string;
  product: number;
}

@Component({
  selector: 'app-product-service',
  standalone: true,
  imports: [CommonModule, NgFor],
  templateUrl: './product-service.component.html',
  styleUrls: ['./product-service.component.css']
})
export class ProductServiceComponent implements OnInit {
  products: Product[] | any;
  id: any;
  itemQuantity =0;

  showTooltip = false;


  constructor(private http: HttpClient) {}
  ngOnInit(): void {
     this.id = sessionStorage.getItem('id');
    this.http.get<any>('http://localhost:5050/viewAll').subscribe(
      (response) => {
        console.log('API Response:', response);
        this.products = response; 
      },
        (error) => {
          console.error('Error fetching customer data', error);
        }
      );
      this.http.get<any>('http://localhost:5050/user-products?userId=${this.id}').subscribe(
        (response) => {
          console.log('API Response:', response);
          this.itemQuantity = response.userQuantity; 
        },
          (error) => {
            console.error('Error fetching customer data', error);
          }
        );
  
    }
    cartItem = {
      name: 'Product Name',
      quantity: this.itemQuantity
    };
  cart: Product[] = [];

  selectedProduct: any = null; // Variable to store the selected product
  reviews :any = [];
  reviewsVisible = false;
  reviewFormVisible = false;
  newReview = { comment: '' }; // Model for the new review

  // Method to set the selected product
  selectProduct(product: any) {
    this.selectedProduct = product;
    this.reviewsVisible = false;
    this.reviewFormVisible = false;
  }
  showReviews(selectProductP: any) {
    alert(selectProductP)
    // Extracting feedback from the selected product
    this.reviewsVisible = true;
    this.reviews = selectProductP.feedbackList.map((feedback: { rating: any; review: any; userId: any; }) => {
        return {
            rating: feedback.rating,
            review: feedback.review,
            userId: feedback.userId
        };
    });
}



  // Method to show the rating and review form
  giveReview() {
    this.reviewFormVisible = true;
    this.reviewsVisible = false;
  }

   // Method to submit the review
   submitReview() {
    // Logic to submit the review
    if (this.newReview.comment.trim() !== '') {
      this.reviews.push();
      this.newReview.comment = ''; // Clear the form
      this.reviewFormVisible = false; // Hide the form after submission
    }
  }


  addToCart(product: Product) {
    
    this.cart.push(product);
    console.log(`${product.name} added to cart.`);
    this.http.get<any>(`http://localhost:5050/manage-quantity?userId=${this.id}&productId=${product.id}&boolean1=true`).subscribe(
      (response) => {
        console.log('API Response:', response);
        this.products = response;
        window.location.reload();
      },
      (error) => {
        console.error('Error fetching customer data', error);
      }
    );

  }

  removeFromCart(product: Product) {
    this.http.get<any>(`http://localhost:5050/manage-quantity?userId=${this.id}&productId=${product.id}&boolean1=false`).subscribe(
      (response) => {
        console.log('API Response:', response);
        this.products = response;
        window.location.reload();
      },
      (error) => {
        console.error('Error fetching customer data', error);
      }
    );

  }
}
