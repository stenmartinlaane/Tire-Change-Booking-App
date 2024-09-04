"use client"
import Link from 'next/link'
import { useParams, useRouter } from 'next/navigation';
import { toast } from "react-toastify";
import React, { useState } from 'react'

export default function Page() {
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const router = useRouter();

  const { "tire-workshop-id": tireWorkshopId, "booking-id": bookingid } = useParams<{ "tire-workshop-id"?: string; "booking-id"?: string }>();

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    let data = ""; 

    try {
      const res = await fetch(
        `${process.env.NEXT_PUBLIC_BACKEND_SERVER}/tire-change-times/${tireWorkshopId}/booking/${bookingid}?contactInfo=email: ${email} name: ${name}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: ``,
          credentials: 'include',
        }
      );
      console.log(res.status)
      if (res.status === 200) {
        toast.success("Booking successfuly made.")
        router.push("/")
        
      } else if (res.status === 400 || res.status === 401 || res.status === 405) {
        const dataObj = await res.json();
    
      }
    } catch (error) {
      console.log(error)
    } finally {
    }
  }


  return (
    <div className="w-full h-full bg-white-background ">
        <div className='w-full bg-primary flex items-center pb-4'>
            <h1 className='p-2 pb-6 pl-4 text-white text-4xl font-thin text-nowrap'>Booking</h1>
        </div>
      <div className="flex-1 flex w-full">
          <div className="w-1/5"></div>
        

          <form className="w-1/2 py-2" onSubmit={handleSubmit}>
        <div className="w-100 flex my-3">
          <div className="w-1/4">
            <label htmlFor="email">Email:</label>
          </div>
          <div className="w-3/4">
            <input
              className="w-full border border-black rounded px-2"
              type="text"
              id="email"
              name="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            ></input>
          </div>
        </div>

        <div className="w-100 flex my-3">
          <div className="w-1/4">
            <label htmlFor="email">Full name:</label>
          </div>
          <div className="w-3/4">
            <input
              className="w-full border border-black rounded px-2"
              type="text"
              id="name"
              name="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
            ></input>
          </div>
        </div>

        <div className="flex space-x-4">
          <Link href={"/"}>
            <div className="bg-secondary p-2 rounded">
              <button>Back</button>
            </div>
          </Link>
          <div className="bg-primary p-2 rounded">
            <button type="submit" className='text-white'>Book</button>
          </div>
        </div>
      </form>


        
      </div>
    </div>  
  )
}
