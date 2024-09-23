import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { RouterOutlet } from '@angular/router';
import { CustomerServiceComponent } from './components/customer-service/customer-service.component';
import { NgModule } from '@angular/core';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProductServiceComponent } from './components/product-service/product-service.component';
export const routes: Routes = [



 { path: 'login', component: LoginComponent },
  { path: 'Signup', component:SignupComponent },
  { path: 'customer', component:CustomerServiceComponent },
  { path: 'product', component:ProductServiceComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}