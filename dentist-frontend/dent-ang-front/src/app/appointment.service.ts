import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Appointment } from './appointment';
import { CancelHours } from './cancel-hours';
import { Login } from './login';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private httpClient: HttpClient) { }

  searchAppointmentByPhoneNumber(appointment: Appointment):Observable<any>{ //mora any, buni se u ts fajlu
    return this.httpClient.post("http://localhost:8080/api/getAppointmentByPhoneNumber", appointment);
  }

  createAppointment(appointment: Appointment):Observable<any>{
    return this.httpClient.post("http://localhost:8080/api/createAppointment", appointment);
  }

  deleteAppointment(phoneNumberId: number):Observable<any>{
    return this.httpClient.delete("http://localhost:8080/api/cancelAppointment/" + phoneNumberId);
  }

  login(login: Login):Observable<any>{
    return this.httpClient.post("http://localhost:8080/api/login", login);
  }

  getAllAppointmentList():Observable<Appointment[]>{
    return this.httpClient.get<Appointment[]>("http://localhost:8080/api/getAllAppointments");
  }

  getMyAppointmentList():Observable<any>{
    //let tokenStr = 'Bearer ' + token;
    //const headers = new HttpHeaders().set("Authorization", tokenStr);
    return this.httpClient.get<any>("http://localhost:8080/api/getMyAppointments");
  }

  getCancelHours():Observable<CancelHours>{
    return this.httpClient.get<CancelHours>("http://localhost:8080/api/getCancelHours");
  }

  changeCancelHours(cancelHours: CancelHours):Observable<any>{
    return this.httpClient.post("http://localhost:8080/api/changeCancelHours", cancelHours);
  }
}
