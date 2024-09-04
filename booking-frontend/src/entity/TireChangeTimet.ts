import { TireWorkshop } from './TireWorkshop'; // Assuming you have a TireWorkshop class defined

export class TireChangeTime {
  id: string;
  dateTime: string;

  // Constructor with default values (similar to @AllArgsConstructor and @NoArgsConstructor)
  constructor(id: string = '', dateTime: string = '') {
    this.id = id;
    this.dateTime = dateTime;
  }
}