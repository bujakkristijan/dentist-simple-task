import { Message } from "./message";

export class User {

  id: number;
  firstName: string;
  lastName: string;
  username: string;
  password: string;
  role: string;
  email: string;
  loginMessage: string;
  errorMessage: Message;

}
