import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms'; // Import ReactiveFormsModule
import { provideHttpClient, withFetch } from '@angular/common/http'; // Import HttpClient with fetch
import { routes } from './app/app.routes';

// Bootstrap application with necessary providers
bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes), // Set up routing
    provideHttpClient(withFetch()), // Enable fetch API for HttpClient
    ReactiveFormsModule // Import ReactiveFormsModule for form handling
  ]
}).catch(err => console.error(err));
