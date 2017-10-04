import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../../core/user';
import {AuthService} from '../service/auth.service';

@Component({
    moduleId: module.id,
    selector: 'app-register',
    templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit {

    model: User;
    message = '';

    constructor(private router: Router, private authService: AuthService) {
    }

    ngOnInit(): void {
      this.model = new User('', '');
    }

    onSubmit(): void {
      if (this.model.username.length == 0 || this.model.password.length == 0) {
        this.message = 'Username and/or password fields should not be empty';
        return;
      }
      console.log(this.model);
        this.authService.register(this.model).then(response => {
            this.message = 'Registration is succsesful';
        })
          .catch(() => {this.message = 'Registration failed!'; });
    }
}
