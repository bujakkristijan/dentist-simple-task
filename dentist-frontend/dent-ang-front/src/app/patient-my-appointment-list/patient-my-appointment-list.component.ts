import { AppointmentService } from './../appointment.service';
import { Component, OnInit } from '@angular/core';
import { Appointment } from '../appointment';
import { Router } from '@angular/router';
import { Message } from '../message';
import { CancelHours } from '../cancel-hours';


@Component({
  selector: 'app-patient-my-appointment-list',
  templateUrl: './patient-my-appointment-list.component.html',
  styleUrls: ['./patient-my-appointment-list.component.css']
})
export class PatientMyAppointmentListComponent implements OnInit {


  appointments: Appointment[];
  mySearchedAppointmentsByPhoneNumber: Appointment[] = [];
  appointment: Appointment = new Appointment();

  messageAvailableToCancel: Message = new Message();
  cancelHours: CancelHours = new CancelHours();

  constructor(private appointmentService: AppointmentService, private router: Router) { }

  ngOnInit(): void {
    //this.getMyAppointments();
    this.getCancelHours();

  }

  private getMyAppointments(){
    this.appointmentService.getMyAppointmentList().subscribe(data =>{
      this.appointments = data;
    });
  }

  getCancelHours(){
    this.appointmentService.getCancelHours().subscribe(data =>{
      this.cancelHours = data;
    });
  }

  searchAppointmentByPhoneNumberId(){
    this.appointmentService.searchAppointmentByPhoneNumber(this.appointment).subscribe(data =>{
      console.log(data);
      this.mySearchedAppointmentsByPhoneNumber = data;
      if(this.mySearchedAppointmentsByPhoneNumber.length == 0){
        alert('You dont have any booked appointments yet!');
      }
    });
  }
  onSubmit(){
    this.searchAppointmentByPhoneNumberId();
  }
  goToCreateAppointment(){
    this.router.navigate(['create-appointment']);
  }

  cancelAppointment(phoneNumberId: number){
    this.appointmentService.deleteAppointment(phoneNumberId).subscribe(data =>{
      this.messageAvailableToCancel = data;

      if(this.messageAvailableToCancel.availableToCancel === "yes"){
        alert("Your appointment is canceled successfully!");

        this.searchAppointmentByPhoneNumberId();
      }
      else if(this.messageAvailableToCancel.availableToCancel === "no"){
        alert("You can't cancel your appointment within " + this.cancelHours.hours + " hours before start!")

        this.searchAppointmentByPhoneNumberId();
      }
      //this.searchAppointmentByPhoneNumberId(); // dodato da kada se canceluje appointment da se opet searchuje i update tabela
    });
  }

  goToMyAppointmentList(){
    this.router.navigate(['patient-my-appointments']);
  }

  onKeyPress(event: any) {
    const regexpNumber = /[0-9\+\-\ ]/;
    let inputCharacter = String.fromCharCode(event.charCode);
    if (event.keyCode != 8 && !regexpNumber.test(inputCharacter)) {
      event.preventDefault();
    }
  }


}
