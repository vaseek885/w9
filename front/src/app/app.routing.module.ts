import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthComponent } from './auth/auth.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';


// import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', redirectTo: '/auth', pathMatch: 'full' },
  { path: 'auth',  component: AuthComponent },
  { path: 'home',  component: HomeComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes,
      { enableTracing: true } ) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
