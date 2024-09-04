
'use client'
import React, { useContext, useEffect, useState, useMemo } from 'react'
import Spinner from './Spinner'
import TireChangeBookingRow from './TireChangeBookingRow';
import { TireChangeTime } from '@/entity/TireChangeTimet';
import { AppContext } from '@/context/StateComponent';
import { ApiResponse } from '@/entity/ApiResponse';
import FilteringComponent from './FilteringComponent';


const TireChangeColumn = () => {

  const [loading, setLoading] = useState(false);
  const { tireChangeTimesApiResponses, setTireChangeTimesApiResponses } = useContext(AppContext);
  const { tireChangeTimesApiResponsesLoading, setTireChangeTimesApiResponsesLoading } = useContext(AppContext);
  const { selectedWorkshops, setSelectedWorkshops } = useContext(AppContext);
  const { selectedDates, setSelectedDates } = useContext(AppContext);
  const { selectedVehicleType, setSelectedVehicleType } = useContext(AppContext);

  const handleError = (workshopsResponse: ApiResponse<TireChangeTime[]>) => {
  }


  const shownApiResponses = useMemo(() => {
    return tireChangeTimesApiResponses.filter(response => selectedWorkshops.length === 0 ? true : selectedWorkshops.includes(response.tireWorkshop.id))
    .filter(response => selectedVehicleType === "" ? true : response.tireWorkshop.supportedVehicleTypes.includes(selectedVehicleType))
  }, [tireChangeTimesApiResponses, selectedWorkshops, selectedVehicleType]);

  return (
    <>
        {tireChangeTimesApiResponsesLoading ? (
            <Spinner loading={tireChangeTimesApiResponsesLoading} />
        ) : (
            <div className="flex flex-row space-x-4 w-full h-full flex-1">
                
                <div className='flex flex-col w-full'>
                    <div className='bg-primary w-full flex justify-center items-center flex-none'>
                        <h2 className='p-4 text-white font-medium'>Tire Change time booking</h2>
                    </div>
                    <FilteringComponent></FilteringComponent>
                    <div className='h-full w-full flex-1 bg-white'>

                    {shownApiResponses.map((workshopsResponse: ApiResponse<TireChangeTime[]>, index) => {
                            if (!workshopsResponse.success) {
                                handleError(workshopsResponse);
                                return <p key={index}>error</p>;
                            } else {
                                return (
                                workshopsResponse.result!.filter(tireChangeTime => (new Date(tireChangeTime.dateTime) >= selectedDates[0] && new Date(tireChangeTime.dateTime) <= selectedDates[1])).
                                map((tireChangeTime : TireChangeTime, index) => {
                                    return (
                                        <TireChangeBookingRow 
                                            key={index} 
                                            tireChangeTime = {tireChangeTime}
                                            tireWorkshop = {workshopsResponse.tireWorkshop}
                                        />
                                    );

                                }
                                )
                            )
                            }
                        })}

                    </div>
                </div>
            </div>
        )}
    </>
  )
}

export default TireChangeColumn