import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { User } from '../../core/user';
import { PointPayload } from '../../core/pointpayload';
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
  public user;
  
  login(user: User): Promise<boolean> {
      return this.http.post(this.apiURL + 'auth/login', user)
              .toPromise()
              .then(response => {
                this.isAuth = true;
                this.user = user;
                return this.isAuth;
                })
        .catch(err => {this.isAuth = false});
   }
  
  
  
  register(user: User): Promise<boolean> {
      return this.http.post(this.apiURL + 'new/register', user)
            .toPromise()
        .then(() => true);
  }
  
  logout(): void {
    this.accessToken = '';
    this.isAuth = false;
  }
  getPoint(): Promise<any> {
    
    return this.http.post(this.apiURL + 'point/get', this.user )
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
    
    // var payload = {'username':this.user, 'point':point};
    var payload = new PointPayload(point.x,point.y,point.r,false,this.user.username,this.user.password);
    return this.http.post(this.apiURL + 'point/addpoint',payload)
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
    return this.http.post(this.apiURL + 'point/deleteall' , this.user)
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

    return this.http.post(this.apiURL + 'point/delete' ,point)
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
    var payload = new PointPayload(0,0,r,false,this.user.username,this.user.password);
    return this.http.post(this.apiURL + 'point/update', payload)
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
    
    return this.http.post(this.apiURL + 'point/r', this.user)
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
