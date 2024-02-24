import { Role } from "src/app/models/role";


export interface UserResponse {
  id: number;
  address: string;
  fullname: string;
  phone_number: string;
  date_of_birth: Date;
  facebook_account_id: number;
  google_account_id: number;
  role: Role;
  active: boolean;
}
