import { TireWorkshop } from './TireWorkshop';

export class TireChangeTime {
  id: string;
  dateTime: string;

  constructor(id: string = '', dateTime: string = '') {
    this.id = id;
    this.dateTime = dateTime;
  }
}