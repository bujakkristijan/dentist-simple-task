import { AppComponent } from './../app.component';
import { Router } from '@angular/router';
import { AppointmentService } from './../appointment.service';
import { Appointment } from './../appointment';
import { Component, OnInit } from '@angular/core';
import { Login } from '../login';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: Login = new Login();
  user: User = new User();

  constructor(private appComponent: AppComponent, private appointmentService: AppointmentService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
    this.appointmentService.login(this.login).subscribe(data =>{
      this.user = data;

      if(this.user.loginMessage === "NotExist"){
        alert('User with that id doesnt exist');
      }
      else if(this.user.loginMessage === "Exist"){
        //kada se uloguje setujem loggedUsera u app Component kako bih nakon kreiranja appointmenta znao gde da ga vrati
        this.appComponent.loggedUser = data;
        this.goToDentistAppointmentList();
      }
      else if(this.user.loginMessage === "BadRequest"){
        alert('Bad request! You must insert a number!')
      }
    });
  }

  goToDentistAppointmentList(){
    this.router.navigate(['dentist-appointment-list']);
  }

  onKeyPress(event: any) {
    const regexpNumber = /[0-9\+\-\ ]/;
    let inputCharacter = String.fromCharCode(event.charCode);
    if (event.keyCode != 8 && !regexpNumber.test(inputCharacter)) {
      event.preventDefault();
    }
  }

}
