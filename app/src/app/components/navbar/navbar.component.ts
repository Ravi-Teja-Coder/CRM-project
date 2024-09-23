import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],  // Add necessary imports for standalone components
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']  // Changed to 'styleUrls'
})
export class NavbarComponent implements OnInit {
  role: any;
  name: any;
  currentPath: any;
  isLoggedIn: any;
  constructor(private router: Router) {}

  navigateToLogin() {
    sessionStorage.setItem('role','empty');
    sessionStorage.setItem('name','empty');
  }

  ngOnInit(): void {
    this.role=sessionStorage.getItem('role');
    this.name=sessionStorage.getItem('name');
    if(this.role != 'empty'){
      this.isLoggedIn = true;
    }else this.navigateToLogin();
  }
}
