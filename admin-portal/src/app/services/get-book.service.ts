import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';

@Injectable()
export class GetBookService {

  constructor(private http:Http) { }
//function to execute a GET request to get books based on ID
  getBook(id:number) {
  	let url = "http://localhost:8181/book/"+id;
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get(url, {headers: headers});
  }

}
