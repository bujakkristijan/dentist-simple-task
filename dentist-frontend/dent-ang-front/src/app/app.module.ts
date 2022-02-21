import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PatientMyAppointmentListComponent } from './patient-my-appointment-list/patient-my-appointment-list.component';
import { FormsModule } from '@angular/forms';
import { CreateAppointmentComponent } from './create-appointment/create-appointment.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import {MatNativeDateModule} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';


import { LoginComponent } from './login/login.component';
import { DentistAppointmentListComponent } from './dentist-appointment-list/dentist-appointment-list.component';
import { CancelHoursComponent } from './cancel-hours/cancel-hours.component';


@NgModule({
  declarations: [
    AppComponent,
    PatientMyAppointmentListComponent,
    CreateAppointmentComponent,
    LoginComponent,
    DentistAppointmentListComponent,
    CancelHoursComponent


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatNativeDateModule,
    MatDatepickerModule,
    NgxMaterialTimepickerModule


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
