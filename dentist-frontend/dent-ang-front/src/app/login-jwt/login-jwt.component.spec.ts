import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginJWTComponent } from './login-jwt.component';

describe('LoginJWTComponent', () => {
  let component: LoginJWTComponent;
  let fixture: ComponentFixture<LoginJWTComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginJWTComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginJWTComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
