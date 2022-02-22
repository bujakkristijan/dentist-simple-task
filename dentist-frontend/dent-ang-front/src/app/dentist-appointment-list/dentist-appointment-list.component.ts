import { Message } from './../message';
import { AppComponent } from './../app.component';
import { AppointmentService } from './../appointment.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Appointment } from '../appointment';
import { User } from '../user';
import { CancelHours } from '../cancel-hours';

@Component({
  selector: 'app-dentist-appointment-list',
  templateUrl: './dentist-appointment-list.component.html',
  styleUrls: ['./dentist-appointment-list.component.css']
})
export class DentistAppointmentListComponent implements OnInit {

  allAppointments: Appointment[];
  loggedUser: User;
  messageAvailableToCancel: Message = new Message();
  cancelHours: CancelHours = new CancelHours();

  constructor(private appComponent: AppComponent, private appointmentService: AppointmentService, private router: Router) { }

  ngOnInit(): void {

    if(this.appComponent.loggedUser.title === "dentist"){
      this.loggedUser = this.appComponent.loggedUser;
      this.appComponent.loginBtn.style.display = 'none';  // ako je ulogovan sakrijem button za logovanje
      this.appComponent.logoutBtn.style.display = 'block';
      this.appComponent.cancelHoursBtn.style.display = 'block';
      this.appComponent.dentistListBtn.style.display = 'block';

      this.getAllAppointments();
      this.getCancelHours();
    }
    else{
      this.router.navigate(['patient-my-appointments']);
      console.log("Unauthorized!");
    }


  }

  goToCreateAppointment(){
    this.router.navigate(['create-appointment']);
  }

  getAllAppointments(){
    this.appointmentService.getAllAppointmentList().subscribe(data =>{
      this.allAppointments = data;
      //this.sortAppointmentsByDateAndTime();
      this.sortAppointments();
    });
  }

  getCancelHours(){
    this.appointmentService.getCancelHours().subscribe(data =>{
      this.cancelHours = data;
    });
  }

  cancelAppointment(phoneNumberId: number){
    this.appointmentService.deleteAppointment(phoneNumberId).subscribe(data =>{
      this.messageAvailableToCancel = data;

      if(this.messageAvailableToCancel.availableToCancel === "yes"){
        alert("Your appointment is canceled successfully!");

        this.getAllAppointments();
      }
      else if(this.messageAvailableToCancel.availableToCancel === "no"){
        alert("You can't cancel your appointment within " + this.cancelHours.hours + " hours before start!")

        this.getAllAppointments();
      }
      //this.getAllAppointments();
    });
  }

  //sortAppointmentsByDateAndTime(){
  //  this.allAppointments.sort(function(a: Appointment , b: Appointment): any{

  //   return b.date.getTime() - a.date.getTime();
  //  });
  // }

  sortAppointments(){
    this.allAppointments.sort((x, y) => +new Date(x.date) - +new Date(y.date));
  }



}
