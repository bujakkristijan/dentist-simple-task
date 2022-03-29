import { LoginJWTService } from './../login-jwt.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { Login } from '../login';
import { User } from '../user';
import { LoginJWT } from '../login-jwt';

@Component({
  selector: 'app-login-jwt',
  templateUrl: './login-jwt.component.html',
  styleUrls: ['./login-jwt.component.css']
})
export class LoginJWTComponent implements OnInit {

  login: Login = new Login();
  user: User = new User();
  loginJWT: LoginJWT = new LoginJWT();
  //tokenStr: string;

  constructor(private appComponent: AppComponent, private loginJwtService: LoginJWTService, private router: Router) { }

  ngOnInit(): void {
   // this.loginJWT = new LoginJWT();
  }

  onSubmit(){
    this.loginJwtService.loginWithJWT(this.login).subscribe(data =>{
      //let tokenStr = data;

      this.loginJWT = data;
      this.user = this.loginJWT.user;



      //this.user = this.loginJWT.user;
      //console.log("TOKEEEEN: " + this.tokenStr);

      //this.user = data;


     if(this.user.loginMessage === "Exist"){
        //kada se uloguje setujem loggedUsera u app Component kako bih nakon kreiranja appointmenta znao gde da ga vrati
        this.appComponent.loggedUser = this.user;
        //this.tokenStr = this.loginJWT.token;
        localStorage.token = this.loginJWT.token;
        console.log("USER ULOGOVAN :" + this.appComponent.loggedUser.firstName);
        this.goToDentistAppointmentList();
      }
      else if(this.user.loginMessage === "InvalidUsernameOrPassword"){
        // *** ne mora ipak *** mora ovo jer je bila greska da kada se bezuspesno loguje, sledece logovanje izbaci error na backu: JWT strings must contain exactly 2 period characters. Found: 0
        //this.tokenStr = "";
        //localStorage.token = "";
        alert('Invalid username or password! Try again!')
      }
    });
  }

  goToDentistAppointmentList(){
    console.log("UDJIIII ");
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
