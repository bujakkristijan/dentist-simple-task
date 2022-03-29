import { LoginJWTComponent } from './login-jwt/login-jwt.component';
import { CancelHoursComponent } from './cancel-hours/cancel-hours.component';
import { LoginComponent } from './login/login.component';
import { DentistAppointmentListComponent } from './dentist-appointment-list/dentist-appointment-list.component';

import { CreateAppointmentComponent } from './create-appointment/create-appointment.component';
import { PatientMyAppointmentListComponent } from './patient-my-appointment-list/patient-my-appointment-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: 'patient-my-appointments', component: PatientMyAppointmentListComponent},
  {path: '', redirectTo: 'patient-my-appointments', pathMatch: 'full'},
  {path: 'create-appointment', component: CreateAppointmentComponent},
  {path: 'dentist-appointment-list', component: DentistAppointmentListComponent},
  {path: 'cancel-hours', component: CancelHoursComponent},
  {path: 'login', component: LoginComponent},
  {path: 'login-jwt', component: LoginJWTComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
