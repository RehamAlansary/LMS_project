/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.util.ArrayList;

public class Enrollment {
    
    //
    static int Enrollment_NUMBER = 0;
    private int ID;
    Student student;
    RoomCourse roomCourse;

    public Enrollment() 
    {
        this.ID = ++Enrollment_NUMBER;
    }

    public Enrollment( Student student, RoomCourse roomCourse) {
        this.ID = ++Enrollment_NUMBER;
        this.student = student;
        this.roomCourse = roomCourse;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public RoomCourse getRoomCourse() {
        return roomCourse;
    }

    public void setRoomCourse(RoomCourse roomCourse) {
        this.roomCourse = roomCourse;
    }

    
    
    ArrayList<Enrollment> GetEnrollmentByStudent(Student student)
    {
        ArrayList<Enrollment> list = new ArrayList<>();
        for(Enrollment enrollment : LMS.enrollmentList)
        {
            if(enrollment.student.getID() == student.getID())
                list.add(enrollment);
        }
        
        return list;
    }
    ArrayList<Enrollment> GetEnrollmentByCourse(Course course)
    {
        ArrayList<Enrollment> list = new ArrayList<>();
        for(Enrollment enrollment : LMS.enrollmentList)
        {
            if(enrollment.roomCourse.course.getCourseCode().equals(course.getCourseCode()))
                list.add(enrollment);
        }
        return list;
    }
    
    static Enrollment getEnrollmentByID(int id)
    {
        for(Enrollment enrollment : LMS.enrollmentList)
        {
            if (enrollment.getID() == id) 
            {
                return enrollment;
            }
        }
        return null;
    }
}

    
    
    
    
    
    
    
