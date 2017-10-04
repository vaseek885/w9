import {NgModule} from '@angular/core';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';


import {AuthComponent} from './auth.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import { BrowserModule } from '@angular/platform-browser';


// import {AuthService} from './services/auth.service';
// import {AuthGuard} from './services/auth-guard.service';

@NgModule({
    imports: [
      BrowserModule,
        FormsModule,
        HttpModule,
    ],
    // providers: [AuthService, AuthGuard],
    declarations: [AuthComponent, LoginComponent, RegisterComponent],
    // exports: [AuthComponent]
})
export class AuthModule {
}
