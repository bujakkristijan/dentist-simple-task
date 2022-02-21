import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelHoursComponent } from './cancel-hours.component';

describe('CancelHoursComponent', () => {
  let component: CancelHoursComponent;
  let fixture: ComponentFixture<CancelHoursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CancelHoursComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CancelHoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
