"use client";

import { ApiResponse } from "@/entity/ApiResponse";
import { TireChangeTime } from "@/entity/TireChangeTimet";
import { useEffect, useState } from "react";
import { createContext } from "react";
import { useParams, useRouter } from "next/navigation";
import { TimeHelper } from "@/helpers/TimeHelper";
import { usePathname, useSearchParams } from "next/navigation";

export default function StateComponent({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const pathname = usePathname();
  const router = useRouter();
  const searchParams = useSearchParams();

  const [ tireChangeTimesApiResponses, setTireChangeTimesApiResponses ] = useState<ApiResponse<TireChangeTime[]>[]>([]);
  const [tireChangeTimesApiResponsesLoading, setTireChangeTimesApiResponsesLoading] = useState(true);

  const [selectedWorkshops, setSelectedWorkshops] = useState<string[]>([]);
  const [selectedVehicleType, setSelectedVehicleType] = useState("Car");

  const to = new Date();
  to.setDate(to.getDate() + 6);
  const from = new Date();
  from.setDate(from.getDate() - 1);
  const [selectedDates, setSelectedDates] = useState<[Date, Date]>([from, to]);

  useEffect(() => {
    const currentParams = new URLSearchParams(window.location.search);

    const fromDate: Date = selectedDates[0]
    fromDate.setDate(fromDate.getDate() + 1);
    const fromUrl = fromDate.toISOString().split('T')[0];

    const toDate: Date = selectedDates[1]
    toDate.setDate(toDate.getDate() + 1);
    const toUrl = toDate.toISOString().split('T')[0];


    currentParams.set('from', fromUrl);
    currentParams.set('to', toUrl);
    router.push(`?${currentParams.toString()}`);

    const getTireChangeTimesByDate = async(from: Date, to: Date) => {
      setTireChangeTimesApiResponsesLoading(true);
      let data: ApiResponse<TireChangeTime[]>[];
      try {
          const res = await fetch(
            `${process.env.NEXT_PUBLIC_BACKEND_SERVER}/tire-change-times/bookings?from=${fromUrl}&to=${toUrl}`,{
              method: "GET",
            }
          );
          if (res.status === 200) {
            data = await res.json();
            setTireChangeTimesApiResponses(data)
          } else {
              return []
          }

        } catch (error) {
          console.log(error)
        }
        return [];
  };
    getTireChangeTimesByDate(from, to);
    setTireChangeTimesApiResponsesLoading(false);
  }, [selectedDates]);

  return (
    <AppContext.Provider
      value={{ tireChangeTimesApiResponses, setTireChangeTimesApiResponses, tireChangeTimesApiResponsesLoading, setTireChangeTimesApiResponsesLoading,
        selectedDates, setSelectedDates, selectedWorkshops, setSelectedWorkshops, selectedVehicleType, setSelectedVehicleType}}
    >
        {children}
    </AppContext.Provider>
  );
}

export interface IUserInfo {
  "jwt": string,
  "refreshToken": string,
  "email": string
}

interface IAppContext {
  tireChangeTimesApiResponses: ApiResponse<TireChangeTime[]>[];
  setTireChangeTimesApiResponses: (val: ApiResponse<TireChangeTime[]>[]) => void;
  tireChangeTimesApiResponsesLoading: boolean;
  setTireChangeTimesApiResponsesLoading: (val: boolean) => void;
  selectedDates: [Date, Date];
  setSelectedDates: (val: [Date, Date]) => void; 
  selectedWorkshops: string[];
  setSelectedWorkshops: (val: string[]) => void;
  selectedVehicleType: string;
  setSelectedVehicleType: (val: string) => void;
}

export const AppContext = createContext<IAppContext>({
  tireChangeTimesApiResponses: [],
  setTireChangeTimesApiResponses: () => {},
  tireChangeTimesApiResponsesLoading: false,
  setTireChangeTimesApiResponsesLoading: () => {},
  selectedDates: [new Date(), new Date()],
  setSelectedDates: () => {}, 
  selectedWorkshops: [], 
  setSelectedWorkshops: () => {}, 
  selectedVehicleType: "", 
  setSelectedVehicleType: () => {}
});
