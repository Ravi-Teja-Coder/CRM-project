import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { ReactiveFormsModule } from '@angular/forms'; // Import ReactiveFormsModule
import { LoginComponent } from './app/components/login/login.component';
import {routes} from './app/app.routes'
import { provideRouter } from '@angular/router';
import { NavbarComponent } from './app/components/navbar/navbar.component';
import { SignupComponent } from './app/components/signup/signup.component';



bootstrapApplication(AppComponent, {
  providers: [
   
   provideRouter(routes)
  ]
}).catch(err => console.error(err));
