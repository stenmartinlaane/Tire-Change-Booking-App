import { ApiResponse } from '@/entity/ApiResponse'
import { TireChangeTime } from '@/entity/TireChangeTimet'
import { TireWorkshop } from '@/entity/TireWorkshop'
import Link from 'next/link'
import React from 'react'

const TireChangeBookingRow = ({ tireChangeTime, tireWorkshop }: { tireChangeTime: TireChangeTime, tireWorkshop: TireWorkshop}) => {
    const date = tireChangeTime.dateTime.split('T')[0];
    const time = tireChangeTime.dateTime.split('T')[1].slice(0, -1);
  return (
    <div className="mx-4 my-2 flex">
        <Link
        className="flex h-full flex-1 px-2 border-2 border-secondary rounded box-border hover:bg-slate-200"
        href={`booking/${tireWorkshop.id}/${tireChangeTime.id}`}
        >
        <div className="w-8/12">
        <p>
            <strong>Name:</strong> {tireWorkshop.name}
        </p>
        <p>
            <strong>Address:</strong> {tireWorkshop.address}
        </p>
        <p>
            <strong>Supported Vehicle Types:</strong> {tireWorkshop.supportedVehicleTypes.join(', ')}
        </p>
        </div>
        <div className="w-2/12 flex content-center h-full">
            <p className="inline-block"> {date + " " + time} </p>
        </div>
        <div className=" flex content-center h-full ml-auto">
            <p className="inline-block ml-auto">Broneeri</p>
        </div>
        </Link>
    </div>
  )
}

export default TireChangeBookingRow