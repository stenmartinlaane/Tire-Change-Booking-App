'use client'
import React, { useContext, useEffect, useState } from 'react'
import { DatePickerInput } from '@mantine/dates';
import { useRouter } from 'next/navigation';
import { MultiSelect, NativeSelect } from '@mantine/core';
import { AppContext } from '@/context/StateComponent';
import { TireWorkshop } from '@/entity/TireWorkshop';
import { useSearchParams } from "next/navigation";
import { TimeHelper } from '@/helpers/TimeHelper';

const FilteringComponent = () => {

   const router = useRouter();
   const searchParams = useSearchParams();
    const [value, setValue] = useState<[Date | null, Date | null]>([null, null]);

    const urlFrom = searchParams.get('from');
    const urlTo = searchParams.get('to');

    const { tireChangeTimesApiResponses, setTireChangeTimesApiResponses } = useContext(AppContext);
    const { tireChangeTimesApiResponsesLoading, setTireChangeTimesApiResponsesLoading } = useContext(AppContext);
    const { selectedWorkshops, setSelectedWorkshops } = useContext(AppContext);
    const { selectedVehicleType, setSelectedVehicleType } = useContext(AppContext);
    const { selectedDates, setSelectedDates } = useContext(AppContext);
    

    const getUniqueVehicleTypes = (): string[] => {
        const res = new Set<string>();
        tireChangeTimesApiResponses.forEach((response) => {
          response.tireWorkshop.supportedVehicleTypes.forEach((item) => {
              res.add(item);
          });
        });
        return Array.from(res);
      };

    const getWorkshops = () => {
         return tireChangeTimesApiResponses.map(x => x.tireWorkshop)
    }
    
    const workshops: TireWorkshop[] = getWorkshops();

    const updateUrlParams = (from: string, to: string) => {
        const currentParams = new URLSearchParams(window.location.search);
        currentParams.set('from', from);
        currentParams.set('to', to);
        router.push(`?${currentParams.toString()}`);
      };
    
    useEffect(() => {
      if (value[0] && value[1]) {
          setSelectedDates([value[0], value[1]])
      }
    }, [value]);

    useEffect(() => {
      if (!(value[0] || value[1])) {
        let to: Date = new Date();
        let from: Date = new Date();
        if (urlFrom) {
          from = new Date(urlFrom);
          
        } else {
          from.setDate(from.getDate() - 2)
        }
        if (urlTo) {
          to = new Date(urlTo);
          to.setDate(to.getDate())
        } else {
          to.setDate(to.getDate() + 4);
        }
        setSelectedDates([from, to])
        setValue([from, to]);
      }
    }, [setSelectedDates, setValue]);

  return (
    <div className='w-full flex justify-center items-center flex-none py-2'>
                <MultiSelect
            label="Select Workshops"
            placeholder="Choose workshops"
            data={workshops.map((workshop) => ({
                value: workshop.id,
                label: workshop.name,
            }))}
            value={selectedWorkshops}
            onChange={setSelectedWorkshops}
            searchable
            clearable
            />
        <div className='px-4'>
            <DatePickerInput
                type="range"
                label="Pick dates range"
                placeholder="Pick dates range"
                value={value}
                onChange={setValue}
            />
        </div>
        <NativeSelect label="Choos vheicle type" value={selectedVehicleType} onChange={(event) => setSelectedVehicleType(event.currentTarget.value)}  data={tireChangeTimesApiResponsesLoading ? [] : getUniqueVehicleTypes()} />
    </div>
  )
}

export default FilteringComponent