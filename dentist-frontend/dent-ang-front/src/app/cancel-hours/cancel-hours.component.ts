import { AppComponent } from './../app.component';
import { Router } from '@angular/router';
import { CancelHours } from './../cancel-hours';
import { Message } from './../message';
import { AppointmentService } from './../appointment.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Variable } from '@angular/compiler/src/render3/r3_ast';


@Component({
  selector: 'app-cancel-hours',
  templateUrl: './cancel-hours.component.html',
  styleUrls: ['./cancel-hours.component.css']
})
export class CancelHoursComponent implements OnInit {


  modal: any;
  cancelHours: CancelHours = new CancelHours(); //vrednost iz modala
  cancelHoursGet: CancelHours = new CancelHours(); //vrednost koju cita kada se otvori stranica
  message: Message;


  constructor(private appComponent: AppComponent, private appointmentService: AppointmentService, private router: Router) { }

  ngOnInit(): void {

    if(this.appComponent.loggedUser.role === "DENTIST"){
      this.getCancelHours();
    this.modal = document.getElementById("myModal");
    //this.cancelHours = new CancelHours();
    //this.cancelHoursGet = new CancelHours();
    this.message = new Message();
    }
    else{
      this.router.navigate(['patient-my-appointments']);
      console.log("Unaothorized!");
    }

  }

  getCancelHours(){
    this.appointmentService.getCancelHours().subscribe(data =>{
      this.cancelHoursGet = data;

     //this.cancelHours = data;
    });
  }

  openModal(){
    this.modal.style.display = "block";

    this.cancelHours.hours = this.cancelHoursGet.hours;
  }

  closeModal(){
    this.modal.style.display = "none";
  }

  changeCancelHours(){

    //this.cancelHours.hours = Number((<HTMLInputElement>document.getElementById('cancelHours')).value); //more htmlinputelement ispred jer nece da ucita value, a i number da castuje posto je string
    //this.cancelHours.hours = this.cancelHoursGet.hours;
    this.appointmentService.changeCancelHours(this.cancelHours).subscribe(data =>{
      this.message = data;
      if(this.message.cancelHoursMessage === "success"){

        this.closeModal();
        this.getCancelHours();

        alert("You have successfully changed cancel hours!");
      }

      else if(this.message.cancelHoursMessage === "failed"){
        alert("Bad input");
      }
    });
  }

}
