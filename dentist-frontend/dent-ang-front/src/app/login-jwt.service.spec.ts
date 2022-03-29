import { TestBed } from '@angular/core/testing';

import { LoginJWTService } from './login-jwt.service';

describe('LoginJWTService', () => {
  let service: LoginJWTService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginJWTService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
