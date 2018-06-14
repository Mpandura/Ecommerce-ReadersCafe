import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Book} from '../models/book';

@Injectable()
export class EditBookService {

  constructor(private http:Http) { }
//function to update the book by attaching correct headers and making a post to the server 
  sendBook(book:Book) {
  	let url = "http://localhost:8181/book/update";
    
    let headers = new Headers ({
      'Content-Type': 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(url, JSON.stringify(book), {headers: headers});
  }
}
