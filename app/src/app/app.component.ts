import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component'; // Adjust path as necessary
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { CustomerServiceComponent } from './components/customer-service/customer-service.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent,LoginComponent,SignupComponent,RouterLink,RouterLinkActive,CustomerServiceComponent], // Import NavbarComponent here
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  ngOnInit(): void {
  }
  title = 'app';
} 
