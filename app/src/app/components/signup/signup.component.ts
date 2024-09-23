import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-signup',
  standalone: true,
  templateUrl: './signup.component.html',
  imports: [FormsModule, CommonModule, HttpClientModule], // Add HttpClientModule here
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  name: string = '';
  email: string = '';
  password: string = '';
  confirmPassword: string = '';
  error: string = '';
  loading: boolean = false;

  constructor(private http: HttpClient, private router: Router) { }

  async handleSubmit(event: Event): Promise<void> {
    // event.preventDefault(); // Uncomment if you're preventing default form submission

    // Log form data
    console.log('Form Data:');
    console.log('Name:', this.name);
    console.log('Email:', this.email);
    console.log('Password:', this.password);
    console.log('Confirm Password:', this.confirmPassword);

    // Validate form data
    if (this.password !== this.confirmPassword) {
      this.error = 'Passwords do not match';
      console.error('Passwords do not match');
      return;
    }

    this.loading = true;

    // Send the POST request
    this.http.post("http://localhost:8081/users/register", {
      name: this.name,
      email: this.email,
      password: this.password,
      confirmPassword: this.confirmPassword
    }).subscribe(response => {
      console.log('Success!', response);
      this.loading = false; // Stop loading on success
      this.router.navigate(['/login']); // Navigate to a different route if needed
    },
    error => {
      console.error('Error occurred:', error);
      this.error = 'Registration failed. Please try again.'; // Show error message
      this.loading = false; // Stop loading on error
    });
  }
}
