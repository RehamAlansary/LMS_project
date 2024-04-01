/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    
   //properties
    private String CourseCode;
    private String Day;
    private int StartTime;
    private int Hours;
    private int CourseCapacity;
// constracter empty
    public Course() {
    }

    public Course(String CourseCode, String Day, int StartTime, int Hours, int CourseCapacity) {
        this.CourseCode = CourseCode;
        this.Day = Day;
        this.StartTime = StartTime;
        this.Hours = Hours;
        this.CourseCapacity = CourseCapacity;
    }
// getter and setter method
    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String Day) {
        this.Day = Day;
    }

    public int getStartTime() {
        return StartTime;
    }

    public void setStartTime(int StartTime) {
        this.StartTime = StartTime;
    }

    public int getHours() {
        return Hours;
    }

    public void setHours(int Hours) {
        this.Hours = Hours;
    }

    public int getCourseCapacity() {
        return CourseCapacity;
    }

    public void setCourseCapacity(int CourseCapacity) {
        this.CourseCapacity = CourseCapacity;
    }
    // return all string
    @Override
    public String toString()
    {// ترجع تفاصيل الكلاس لي نص
        return "CourseCode: "+ CourseCode+
                "\nDay: "+Day+
                "\nStart Time: "+StartTime+
                "\nHours: "+Hours+
                "\nCourse Capacity: "+CourseCapacity;
    }
    // read cours from file and try file
    // نقراء من الملف و نخزن في الاري من نوع كورس
    public static ArrayList<Course> ReadFromFile(){
        ArrayList<Course> list = new ArrayList<>();
        try {
            // نفراء من لملف سطر سطر 
            File file1= new File ("Courses.txt");
            Scanner read = new Scanner(file1);

            while(read.hasNext()){
                String line =read.nextLine();
                String[] data = line.split(" ");
                Course obj = new Course( //  نخزن البيانات في متغير من نوع كورس
                        data[0],
                        data[1],
                        Integer.parseInt(data[2]),
                        Integer.parseInt(data[3]),
                        Integer.parseInt(data[4]
                        )
                
                );// نضيفها في لست تستخدم في الكلاس الرئيسي 
                list.add(obj);

                //System.out.println(line);
            }
             // اغلاق عملية القرائة 
            read.close();
        }
        catch (Exception e) 
        {
         e.printStackTrace();
        }
        
        return list;
    }
    // تطبع كل عناصر المصفوفةالي جاية من الكلاس الرئيسي  
    static void printAllCourses()
    {   
        for(Course course : LMS.courseList)
        {
            System.out.println("\n====== course "+course.getCourseCode()+"   data ========\n");
            System.out.println(course.toString());/// نحولها لي نص 
        }
    }  // نعطية الكود ويطابقة
    
    static Course getCourseByCode(String code)
    {
        for(Course course : LMS.courseList)
        {
            if (course.getCourseCode().equals(code)) 
            {
                return course;
            }
        }
        return null;
    }
}

    
    
    
    
    
    
    
    
    

