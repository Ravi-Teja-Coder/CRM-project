import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  //imports: [Router],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  constructor(private router: Router) {};
  navigateToLogin() {
    console.log('Link clicked:');
    this.router.navigate(['/login'])
    
  }

   
    
  }
  //constructor(private router: Router) {};
    

