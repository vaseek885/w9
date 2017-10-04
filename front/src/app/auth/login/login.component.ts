import {Component, OnInit, Input} from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../../core/user';
import {AuthService} from '../service/auth.service';

@Component({
    moduleId: module.id,
    selector: 'app-login',
    templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

    model: User;
    message = '';

    constructor(private router: Router, private authService: AuthService) {
    }

    ngOnInit(): void {
      this.model = new User('', '');
    }

    onSubmit(): void {
      console.log(this.model.username.length);
      if (this.model.username.length < 3 || this.model.password.length < 3) {
        this.message = 'Username or password cant be empty and less then 3 simbols';
        return;
      }
        this.authService.login(this.model).then( () => { 
            if (this.authService.isAuth) {
            this.message = '';
            this.router.navigate(['/home']);
          }
        });
          console.log('catch');
          this.message = 'Username or password is incorrect';
        // this.model = new User('Ti Najal knopky', '');
        
    }
}
