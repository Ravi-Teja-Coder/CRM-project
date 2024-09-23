import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { HttpClient } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators,ReactiveFormsModule  } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-service',
  standalone: true,
  imports: [MatTableModule, MatPaginatorModule, MatIconModule, MatButtonModule,ReactiveFormsModule,CommonModule],
  templateUrl: './customer-service.component.html',
  styleUrls: ['./customer-service.component.css']
})
export class CustomerServiceComponent implements AfterViewInit , OnInit{
delete(arg0: any) {
throw new Error('Method not implemented.');
}
  displayedColumns: string[] = ['id', 'name', 'email', 'phone', 'actions'];
  dataSource = new MatTableDataSource<CustomerData>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;  // Definite assignment operator

  constructor(private http: HttpClient,  private fb: FormBuilder,private router: Router) {}

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator; // This will work after the view is initialized
   // this.loadCustomerData();
  }
  dummy : any;
  userForm: FormGroup | any;
  formDisplay: false | any;

  ngOnInit(): void {
    if(sessionStorage.getItem('role') == 'CUSTOMER' ){
      this.router.navigate(['/product']); 
    }
    this.formDisplay = false;
    this.dataSource.paginator = this.paginator; // This will work after the view is initialized
    this.http.get<any>('http://localhost:8081/users?role=CUSTOMER').subscribe(
      (response) => {
        console.log('API Response:', response);
        this.dataSource.data = response.responseObj; // Bind the responseObj data to the table
      },
        (error) => {
          console.error('Error fetching customer data', error);
        }
      );

      ///-------------------Update user---------------------------------///

      this.userForm = this.fb.group({
        userId: [],  // Assuming you will populate this
        name: [''],
        email: [''],
        phoneNumber: [''],
        dob: []
      });

      
  }

   userData = {
    userId: '',
    name: '',
    email: '',
    phoneNumber: '',
    dob: ''
  };

  onSubmit(): void {
    if (this.userForm.valid) {
      const updatedUser = this.userForm.value;
  
      this.http.put(`http://localhost:8081/users`, updatedUser).subscribe(
        (response) => {
          console.log(`Successfully updated user with ID: ${updatedUser.userId}`, response);
          alert("updated successfully");
          this.ngOnInit();

          // Optionally, you can refresh the user data here if needed
          // this.loadCustomerData(); // Refresh data after update
        },
        error => {
          alert("User not found");
          console.error('Error updating user', error);
        }
      );
    } else {
      console.log('Form is invalid. Please fix the errors and try again.');
    }
  }
  
  deleteCustomer(id: number) {
    this.http.delete(`http://localhost:8081/users/id/${id}`).subscribe(
      () => {
        console.log(`Deleted user with ID: ${id}`);
        alert("deleted successfully")
        this.ngOnInit();
      },
      error => {
        console.error('Error deleting customer', error);
      }
    );
  }

  editCustomer(user:any) {
    console.log(`Editing customer with ID:`+user.id);
    this.userData.userId = user.id;
    this.userData.name = user.name;
    this.userData.email = user.email;
    this.userData.phoneNumber = user.phoneNumber;
    this.userData.dob = user.dob;
    this.formDisplay  =true;

  }
}

export interface CustomerData {
  id: number;
  name: string;
  email: string;
  role: number;
  phone: string;
}
