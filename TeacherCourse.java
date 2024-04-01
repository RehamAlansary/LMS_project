/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.util.ArrayList;

public class TeacherCourse {
   
    private int ID;
    Staff staff;
    Course course;
// constractor empty
    public TeacherCourse() {
    }

    public TeacherCourse(int ID, Staff staff, Course course) {
        this.ID = ID;
        this.staff = staff;
        this.course = course;
    }
// getter and seter method
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    public static ArrayList<TeacherCourse> GetEnrollmentByTeacher(Staff staff)
    {
        ArrayList<TeacherCourse> list = new ArrayList<>();
        for(TeacherCourse teacherCourse : LMS.teacherCoursesList)
        {
            if(teacherCourse.staff.getID() == staff.getID())
                list.add(teacherCourse);
        }
        
        return list;
    }
    
    public static ArrayList<TeacherCourse> GetEnrollmentByCourse(Course course)
    {
        ArrayList<TeacherCourse> list = new ArrayList<>();
        for(TeacherCourse teacherCourse : LMS.teacherCoursesList)
        {
            if(teacherCourse.course.getCourseCode().equals(course.getCourseCode()))
                list.add(teacherCourse);
        }
        
        return list;
    }
}

