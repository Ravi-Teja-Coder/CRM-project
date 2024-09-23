import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  imports: [FormsModule, CommonModule], // Add HttpClientModule here
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';
  loading: boolean = false;
  error: string = '';

  role : string = '';
  name: string = '';
  user:any;
  token: string='';

  constructor(private http: HttpClient, private router: Router) {}
  ngOnInit(): void {
    if(sessionStorage.getItem('role') != 'empty' ){
      this.router.navigate(['/customer']); 
    }
  }

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
    }).subscribe((response) => {
      console.log('Login successful:', response);
        this.user = response;
        this.role = this.user.userList[0].userRole;
        this.name = this.user.userList[0].name;
        this.token = this.user.token;
        // Storing role, name, and token in sessionStorage
        sessionStorage.setItem('role', this.role);
        sessionStorage.setItem('id', this.user.userList[0].userId);
        sessionStorage.setItem('name', this.name);
        sessionStorage.setItem('token', this.token);
      this.loading = false; // Stop loading on success     
      window.location.reload(); 
    },
    error => {
      console.error('Login error occurred:', error);
      this.error = 'Login failed. Please check your credentials.'; // Show error message
      this.loading = false; // Stop loading on error
    });
  }
}
