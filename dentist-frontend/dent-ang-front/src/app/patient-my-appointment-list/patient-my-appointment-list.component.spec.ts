import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientMyAppointmentListComponent } from './patient-my-appointment-list.component';

describe('PatientMyAppointmentListComponent', () => {
  let component: PatientMyAppointmentListComponent;
  let fixture: ComponentFixture<PatientMyAppointmentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientMyAppointmentListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientMyAppointmentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
