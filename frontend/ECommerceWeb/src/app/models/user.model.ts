export interface User {
    token: string;
    roles: string;
    userId: number;
    username: string;
    email: string;
    password: string;
    userPhoneNumber: string;
    userAddress: string;
  }
  
  export interface LoginRequest {
    email: string;
    password: string;
  }
  