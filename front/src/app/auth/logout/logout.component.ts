import {Component, OnInit, Input} from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../../core/user';
import {AuthService} from '../service/auth.service'
@Component({
    moduleId: module.id,
    selector: 'app-logout',
    templateUrl: './logout.component.html',
})
export class LogoutComponent implements OnInit {

    model: User;
  public username = '';

    constructor(private router: Router, private authService: AuthService) {
    }

    ngOnInit(): void {
       this.username = this.authService.user.username;
    }

    onSubmit(): void {
     this.authService.logout();
     this.router.navigate(['/auth']); 
        }
}
