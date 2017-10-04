import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { User } from '../../core/user';
import { HttpHeaders } from '@angular/common/http';
import { API_URL } from '../../core/api';
import { Point } from '../../core/point'

// import { Observer } from 'rxjs';
  
 // import { API_URL } from '../../core/api';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class AuthService {
  private apiURL = API_URL;
  constructor(private http: Http) { }
  
  public isAuth = false; 
  private accessToken: String;
  public username = '';
  
  login(user: User): Promise<boolean> {
      const header = new Headers();
      header.append('Username', user.username as string );
      header.append('Password', user.password as string );
      const opt = new RequestOptions();
      opt.headers = header;
      return this.http.post(this.apiURL + 'auth/login', user.toJson(), opt)
              .toPromise()
              .then(response => {this.accessToken =  response.headers.get('Expires');
                this.isAuth = true;
                this.username = user.username as string;
                return this.isAuth;
                })
        .catch(err => {this.isAuth = false});
   }
  getUser(user: User): Promise<any> {
      const header = new Headers();
      console.log(this.accessToken);
      header.append('Token', this.accessToken as string );
      const opt = new RequestOptions();
    opt.headers = header;
     
      // this.header.append('Username', user.name as string);
      // this.header.append('Password', user.password as string);
      return this.http.get(this.apiURL + 'user', opt)
              .toPromise()
              .then(response => { const headers = response.headers; 
                console.log(headers.get('Expires')); 
              this.accessToken = headers.get('Expires'); });
   }
  
  
  register(user: User): Promise<boolean> {
      return this.http.post(this.apiURL + 'auth/register', user)
            .toPromise()
        .then(() => true);
  }
  
  logout(): void {
    this.accessToken = '';
    this.isAuth = false;
  }
  getPoint(): Promise<any> {
    const header = new Headers();
    header.append('Token', this.accessToken as string );
    const opt = new RequestOptions();
    opt.headers = header;
    return this.http.get(this.apiURL + 'point', opt)
              .toPromise()
              .then(response => { const headers = response.headers; 
              console.log(response); 
              this.accessToken = headers.get('Expires'); 
              return Promise.resolve(response); })
      .catch(err => {
        const headers = err.headers; 
              console.log(err); 
              this.accessToken = headers.get('Expires');
      } );
  }
  addPoint(point: Point): Promise<any> {
    const header = new Headers();
    header.append('Token', this.accessToken as string );
    const opt = new RequestOptions();
    opt.headers = header;
    return this.http.post(this.apiURL + 'point', point, opt)
              .toPromise()
              .then(response => { const headers = response.headers; 
              console.log(response); 
              this.accessToken = headers.get('Expires'); 
              return Promise.resolve(response);
              })
      .catch(err => {
        const headers = err.headers; 
              console.log(err); 
              this.accessToken = headers.get('Expires');
      } );
  }
  deleteAll(): Promise<any> {
    const header = new Headers();
    header.append('Token', this.accessToken as string );
    const opt = new RequestOptions();
    opt.headers = header;
    return this.http.delete(this.apiURL + 'delete' , opt)
              .toPromise()
              .then(response => { const headers = response.headers;
              console.log(response);
              this.accessToken = headers.get('Expires'); 
              return Promise.resolve(response);
              })
      .catch(err => {
        const headers = err.headers;
              console.log(err);
              this.accessToken = headers.get('Expires');
      } );
  }
  deletePoint(point: Point): Promise<any> {
    const header = new Headers();
    header.append('Token', this.accessToken as string );
    const opt = new RequestOptions();
    opt.headers = header;
    return this.http.delete(this.apiURL + 'point' + Point.toString(point) , opt)
              .toPromise()
              .then(response => { const headers = response.headers;
              console.log(response);
              this.accessToken = headers.get('Expires'); 
              return Promise.resolve(response);
              })
      .catch(err => {
        const headers = err.headers;
              console.log(err);
              this.accessToken = headers.get('Expires');
      } );
  }
  updatePoint(r: number): Promise<any> {
    const header = new Headers();
    header.append('Token', this.accessToken as string );
    const opt = new RequestOptions();
    opt.headers = header;
    return this.http.head(this.apiURL + 'point' + '?r=' + r, opt)
              .toPromise()
              .then(response => { const headers = response.headers;
              console.log(response);
              this.accessToken = headers.get('Expires'); 
              return Promise.resolve(response);
              })
      .catch(err => {
        const headers = err.headers;
              console.log(err);
              this.accessToken = headers.get('Expires');
      } );
  }
  getR(): Promise<any> {
    const header = new Headers();
    header.append('Token', this.accessToken as string );
    const opt = new RequestOptions();
    opt.headers = header;
    return this.http.get(this.apiURL + 'r', opt)
              .toPromise()
              .then(response => { const headers = response.headers;
              console.log(response); 
              this.accessToken = headers.get('Expires'); 
              return Promise.resolve(response); })
      .catch(err => {
        const headers = err.headers; 
              console.log(err); 
              console.log(headers.get('Expires')); 
              this.accessToken = headers.get('Expires');
      } );
  }
}
