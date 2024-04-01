/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.util.ArrayList;

public class RoomCourse {
    
    // for auto incremnt
    static int ROOM_COURSE_NUMBER = 0;
    private int ID ;
    Room room;
    Course course;
    Staff staff;

    public RoomCourse() 
    {
        this.ID = ++ROOM_COURSE_NUMBER;
    }

    public RoomCourse(Room room, Course course, Staff staff) {
        this.ID = ++ROOM_COURSE_NUMBER;
        this.room = room;
        this.course = course;
        this.staff = staff;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
    
    public static ArrayList<RoomCourse> GetEnrollmentByRoom(Room room)
    {
        ArrayList<RoomCourse> list = new ArrayList<>();
        for(RoomCourse roomCourse : LMS.roomCoursesList)
        {
            if(roomCourse.room.getRoomNumber()== room.getRoomNumber())
                list.add(roomCourse);
        }
        return list;
    }
    
    public static ArrayList<RoomCourse> GetEnrollmentByCourses(Course course)
    {
        ArrayList<RoomCourse> list = new ArrayList<>();
        for(RoomCourse roomCourse : LMS.roomCoursesList)
        {
            if(roomCourse.course.getCourseCode().equals(course.getCourseCode()))
                list.add(roomCourse);
        }
        return list;
    }
    
    static RoomCourse getRoomCourseByID(int id)
    {
        for(RoomCourse roomCourse : LMS.roomCoursesList)
        {
            if (roomCourse.getID() == id) 
            {
                return roomCourse;
            }
        }
        return null;
    }   
}

