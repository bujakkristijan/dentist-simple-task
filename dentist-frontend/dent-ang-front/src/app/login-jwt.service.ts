import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from './login';

@Injectable({
  providedIn: 'root'
})
export class LoginJWTService {

  constructor(private httpClient: HttpClient) { }

  loginWithJWT(login: Login):Observable<any>{
    return this.httpClient.post("http://localhost:8080/api/loginWithJWT", login);
  }

  welcome(token){
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set("Authorization", tokenStr);
    return this.httpClient.get("http://localhost:8080/api/welcomeTest",  {responseType: 'text' as 'json'});
  }

  logout():Observable<any>{
    return this.httpClient.get("http://localhost:8080/api/logout");
  }

}
