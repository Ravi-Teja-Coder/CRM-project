import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  imports: [FormsModule, CommonModule, HttpClientModule], // Add HttpClientModule here
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  loading: boolean = false;
  error: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  async handleSubmit(event: Event): Promise<void> {
    // Prevent default form submission if needed
    // event.preventDefault();

    // Log form data
    console.log('Form Data:');
    console.log('Email:', this.email);
    console.log('Password:', this.password);

    this.loading = true;

    // Send the POST request to the login API
    this.http.post("http://localhost:8081/users/login", {
      email: this.email,
      password: this.password
    }).subscribe(response => {
      console.log('Login successful:', response);
      this.loading = false; // Stop loading on success
      
      
      // Navigate to a different route on successful login
      this.router.navigate(['/']); // Change to your desired route
    },
    error => {
      console.error('Login error occurred:', error);
      this.error = 'Login failed. Please check your credentials.'; // Show error message
      this.loading = false; // Stop loading on error
    });
  }
}
