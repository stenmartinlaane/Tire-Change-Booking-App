export class TireWorkshop {
    id: string;
    name: string;
    address: string;
    supportedVehicleTypes: string[];
  
    constructor(
      id: string = '',
      name: string = '',
      address: string = '',
      supportedVehicleTypes: string[] = []
    ) {
      this.id = id;
      this.name = name;
      this.address = address;
      this.supportedVehicleTypes = supportedVehicleTypes;
    }
  }