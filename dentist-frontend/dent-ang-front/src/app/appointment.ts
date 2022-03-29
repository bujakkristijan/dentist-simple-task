import { User } from "./user";

export class Appointment {

  phoneNumberId: number;
  firstName: string;
  lastName: string;
  email: string;
  date: Date;
  //dateEnding: Date;
  endDateWithDurationAdded: Date;
  time: string;
  duration: string;
  dentist: User;
  messageAvailable: string;
  messageAlreadyExist: string;
  messageDateInPast: string;
  messageInvalidInput: string;
  messageSuccessfullyAdded: string;
}
