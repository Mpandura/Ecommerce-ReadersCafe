import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class LoginService {

  constructor(private http: Http) { }
//fucntion to check the user credentials by making a GET request 
  sendCredential(username: string, password: string) {
  	let url = "http://localhost:8181/token";
  	let encodedCredentials = btoa(username+":"+password);
  	let basicHeader = "Basic "+encodedCredentials;
  	let headers = new Headers ({
  		'Content-Type' : 'application/x-www-form-urlencoded',
  		'Authorization' : basicHeader
  	});

  	return this.http.get(url, {headers: headers});

  }
//check the session 
  checkSession() {
    let url = "http://localhost:8181/checkSession";
    
    let headers = new Headers ({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get(url, {headers: headers});
  }
//logout by executing a POST request 
logout() {
    let url = "http://localhost:8181/user/logout";
    
    let headers = new Headers ({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(url, '', {headers: headers});
  }

}
