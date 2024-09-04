import { TireWorkshop } from "./TireWorkshop";

export class ApiResponse<T> {
    result: T | null;
    statusCode: string;
    message: string;
    success: boolean;
    tireWorkshop: TireWorkshop;
  
    constructor(result: T | null = null, statusCode: string = '', message: string = '', success: boolean = false, tireWorkshop: TireWorkshop = new TireWorkshop()) {
      this.result = result;
      this.statusCode = statusCode;
      this.message = message;
      this.success = success;
      this.tireWorkshop = tireWorkshop;
    }
}
  