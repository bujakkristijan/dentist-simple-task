import { LoginJWTService } from './login-jwt.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './user';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent implements OnInit {

  loggedUser: User;
  loginBtn: any;
  logoutBtn: any;
  cancelHoursBtn: any;
  dentistListBtn: any;

  ngOnInit(): void {
    this.loggedUser = new User();
    this.loggedUser.role = "NotDentist";
    localStorage.token = "";

    this.loginBtn = document.getElementById("loginBtn");
    this.logoutBtn = document.getElementById("logoutBtn");
    this.cancelHoursBtn = document.getElementById("cancelHoursBtn");
    this.dentistListBtn = document.getElementById("dentistListBtn");

    this.dentistListBtn.style.display = 'none';
    this.logoutBtn.style.display = 'none';
    this.cancelHoursBtn.style.display = 'none';


  }

  constructor(private loginJWTservice: LoginJWTService, private router: Router){}
  title = 'dent-ang-front';

  logOut(){
    this.loggedUser = new User();

    this.loginJWTservice.logout().subscribe(data =>{
      localStorage.token = "";
      console.log("Token local storage: ");
      //localStorage.removeItem("token");


      this.router.navigate(['patient-my-appointments']);
      //sakriva logout i pokazuje login ponovo
      this.cancelHoursBtn.style.display = 'none';
      this.dentistListBtn.style.display = 'none';
      this.logoutBtn.style.display = 'none';
      this.loginBtn.style.display = 'block';
    });


  }

  goToChangeCancelHours(){
    this.router.navigate(['cancel-hours']);
  }


}
