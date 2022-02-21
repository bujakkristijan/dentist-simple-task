import { AppComponent } from './../app.component';
import { LoginComponent } from './../login/login.component';
import { Router } from '@angular/router';
import { AppointmentService } from './../appointment.service';
import { Component, OnInit } from '@angular/core';
import { Appointment } from '../appointment';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';

@Component({
  selector: 'app-create-appointment',
  templateUrl: './create-appointment.component.html',
  styleUrls: ['./create-appointment.component.css']
})
export class CreateAppointmentComponent implements OnInit {

  appointment: Appointment = new Appointment();
  constructor(private appComponent: AppComponent, private appointmentService: AppointmentService, private router: Router) { }

  ngOnInit(): void {
    this.appointment.duration = "30";

  }

  //createAppointment(){
  //  this.appointmentService.createAppointment(this.appointment).subscribe(data =>{
  //    this.goToPatientMyAppointmentList();
  //    console.log(data);
   // });

  //}
  onSubmit(){
    this.appointmentService.createAppointment(this.appointment).subscribe(data =>{
      this.appointment = data;
      //console.log(this.appointment);
      //console.log("USOOOO");
      if(this.appointment.messageInvalidInput === "yes"){
        alert("Invalid input! Try again, make sure everything is filled");
      }
      if(this.appointment.messageAvailable === "no"){
        alert("Sorry, but your appointment is not available at that time, try another one!");
      }
      if(this.appointment.messageAlreadyExist === "yes"){
        alert("You already have booked appointment! Search it by your phone number!")
      }
      if(this.appointment.messageDateInPast === "yes"){
        alert("Invalid date input! Date must be in future!")
      }
      else if(this.appointment.messageSuccessfullyAdded === "yes"){
        alert("You have successfully booked appointment!")
        //ako je ulogovan dentist vraca ga na dentistallappointments
        if(this.appComponent.loggedUser.title === "dentist"){
          this.goToDentistAllAppointmentList();
        }
        //ako je dodat od strane pacijenta ide na patientmyappointmentlist
        else {
          this.goToPatientMyAppointmentList();
        }
      }

      console.log(data);
    });
    //this.createAppointment();
  }

  goToPatientMyAppointmentList(){
    this.router.navigate(['patient-my-appointments']);
  }

  goToDentistAllAppointmentList(){
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
