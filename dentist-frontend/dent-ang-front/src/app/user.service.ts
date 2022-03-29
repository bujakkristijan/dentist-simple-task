import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }



  getDentistList():Observable<User[]>{
    return this.httpClient.get<User[]>("http://localhost:8080/user/getAllDentists");
  }

  createUser(user: User):Observable<any>{
    return this.httpClient.post("http://localhost:8080/user/createUser", user);
  }
}
