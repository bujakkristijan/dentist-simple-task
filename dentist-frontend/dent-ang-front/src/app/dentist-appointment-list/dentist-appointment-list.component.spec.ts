import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DentistAppointmentListComponent } from './dentist-appointment-list.component';

describe('DentistAppointmentListComponent', () => {
  let component: DentistAppointmentListComponent;
  let fixture: ComponentFixture<DentistAppointmentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DentistAppointmentListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DentistAppointmentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
