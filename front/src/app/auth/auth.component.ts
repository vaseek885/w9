import {Component, OnInit} from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'app-auth',
    templateUrl: './auth.component.html'
})
export class AuthComponent implements OnInit {
  loginForm = true;
  ngOnInit() { }
  onChange(): void {
    this.loginForm = !this.loginForm;
  }
}
