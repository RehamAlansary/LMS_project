/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Student {
  // هنا الخصائص لي الملف 
    private String FirstName;
    private String LastName;
    private int ID;
    private double GPA;
// كونسترامكتير  فارغ 
    public Student() 
    {
        
    }
// كونستراكتر في البيانات 
    public Student(String FirstName, String LastName, int ID, double GPA) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.ID = ID;
        this.GPA = GPA;
    }
// اسيترل والقيتر 
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }
    // مثد ترجع استرنق 
    @Override
    public String toString()
    {
        return "FirstName: "+ FirstName+
                "\nLastName: "+LastName+
                "\nID: "+ID+
                "\nGPA: "+GPA;
    }
    //  عرفنامصفوفات من الطلاب وتكون فارغة 
    public static ArrayList<Student> ReadFromFile(){
        ArrayList<Student> list = new ArrayList<>();
        // استخدمنا التراي عشان مايسيرerror اذ الملف مو موجود  
        try {
            //  متغير من فايبل عشان يفتح من الملف
            //System.out.print("Student FirstName\t LastName\t ID\t GPA\t \n");
            File file1= new File ("Students.txt");
            Scanner read = new Scanner(file1);// نقراء من لامتغير سكاتر 
           // ندخل اللوبو نقراء سطر سطر 
            while(read.hasNext()){
                String line =read.nextLine();   
                String[] data = line.split(" ");
                Student obj = new Student(
                        data[0],
                        data[1],
                        Integer.parseInt(data[2]),
                        Double.parseDouble(data[3])
                );
                // نضيفها في لست 
                list.add(obj);

                //System.out.println(line);
            }
            //  اقفال السكانر
            read.close();
        }
        catch (Exception e) 
        {
         e.printStackTrace();
        }
      // يتخزن في الكلاس الرئيسي 
        return list;
    }

    // 
    static void printAllStudent()
    {
        for(Student student : LMS.studentList)
        {
            System.out.println("\n====== student "+student.getID()+"   data ========\n");
            System.out.println(student.toString());
        }
    }
    // تبحث بالايدي عن اي طالب   
    static Student getStudentByID(int id)
    {
        for(Student student : LMS.studentList)
        {
            if (student.getID() == id) 
            {
                return student;
            }
        }
        return null;
    }
    // تشوف اي كلاسات الطالب مو مسجل فيهاهيا تشوف كل الكلاسات المسجللافيها   
    ArrayList<Enrollment> StudentEnrollments()// عند انشاء كائن من كلاس ,و حتجنا نشوف هذا الطالب في اي كلاس مو مسجل  student 
    {      
        ArrayList<Enrollment> studentEnrollments =  new ArrayList<>(); //  حتجنا نشوف هذا الطالب في اي كلاس مو مسجل
        for(Enrollment enrollment :LMS.enrollmentList)
        {
            if(enrollment.student != null)
                if(enrollment.student.ID == ID)
                    studentEnrollments.add(enrollment);
        }
        return studentEnrollments;
    }
    // ترتب اعتمادا على التصاعدي id 
    public static void sortAscendingByID(ArrayList<Student> arrayList) // arraylist من نوع student
    {
        Collections.sort(arrayList,Comparator.comparingInt(Student::getID));        
        System.out.println("");
        for(Student student : arrayList)
        {
            System.out.println("\n======== Student  "+ student.ID+"  Data =========");
            System.out.println(student.toString());
        }
    }
    
    public static void sortDescendingByGPA(ArrayList<Student> arrayList)
    {
        Collections.sort(arrayList,Comparator.comparingDouble(Student::getGPA).reversed()); // الترتيب بالعكس       
        System.out.println(""); // طباعت اللست بالكامل 
        for(Student student : arrayList)
        {
            System.out.println("\n======== Student  "+ student.ID+"  Data =========");
            System.out.println(student.toString());
        }
    }  
}

