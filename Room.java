/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Room {
    

    private int RoomNumber;
    private int Capacity;

    public Room() {
    }

    public Room(int RoomNumber, int Capacity) {
        this.RoomNumber = RoomNumber;
        this.Capacity = Capacity;
    }

    public int getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(int RoomNumber) {
        this.RoomNumber = RoomNumber;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    @Override
    public String toString()
    {
        return "Room Number: "+ RoomNumber+
                "\nCapacity: "+Capacity;
    }
    
    public static ArrayList<Room> ReadFromFile(){
        ArrayList<Room> list = new ArrayList<>();
        try {
            File file1= new File ("Rooms.txt");
            Scanner read = new Scanner(file1);

            while(read.hasNext()){
                String line =read.nextLine();
                String[] data = line.split(" ");
                Room obj = new Room(
                        Integer.parseInt(data[0]),
                        Integer.parseInt(data[1])
                );
                list.add(obj);
            }

            read.close();
        }
        catch (Exception e) 
        {
         e.printStackTrace();
        }
        
        return list;
    }
    
    static Room getRoomByCode(int roomNumber)
    {
        for(Room room : LMS.roomList)
        {
            if (room.RoomNumber == roomNumber) 
            {
                return room;
            }
        }
        return null;
    }
    // تشوف الكورسات المضافة و المدربين في  الاري لست
    ArrayList<RoomCourse> RoomEnrollments()
    {
        ArrayList<RoomCourse> roomCourses =  new ArrayList<>();
        for(RoomCourse roomCourse :LMS.roomCoursesList)
        {
            if(roomCourse.room != null)
                if(roomCourse.room.RoomNumber == RoomNumber)
                    roomCourses.add(roomCourse);
        }// يرجع كل الكورسا المرتبطة  بالروم بكل التفاصيل  
        return roomCourses;
    }
    
//    public boolean isAvilible(Course course)
//    {
//        if(Capacity == course.getCourseCapacity())
//        return true;
//    } نعطيها كورس و نتاكد نمر على الروم كورس بالكامل نتاكد اذ الغرفة مشغولة  
    public boolean isFree(Course course) // ارسال كورس
    {
        for(RoomCourse roomCourse: LMS.roomCoursesList) //  يمر على الروم كورس بالكامل
        {  // نطابق رقم الغرفة
            if(roomCourse.room.getRoomNumber() == RoomNumber) // يطابق رقم غرفى الكورس بالرقم الغرفة المرسل  
            {  // 
                if(roomCourse.course.getDay().equals(course.getDay()))//يطابق يوم الكورس باليوم تبع الكورس روم 
                {
                    if(roomCourse.course.getStartTime() == course.getStartTime()) // و لوقت 
                    {
                        return false; // 
                    }
                }
            }
        }
        return true; // يرجع صح اذ كانت متاح احط في الغرفة كورس  
    }
}