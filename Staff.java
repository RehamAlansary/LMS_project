/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Staff {
    
    private String FirstName;
    private String LastName;
    private int ID;
    private int Office;

    public Staff() {
    }

    public Staff(String FirstName, String LastName, int ID, int Office) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.ID = ID;
        this.Office = Office;
    }

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

    public int getOffice() {
        return Office;
    }

    public void setOffice(int Office) {
        this.Office = Office;
    }
    
    @Override
    public String toString()
    {
        return "FirstName: "+ FirstName+
                " LastName: "+LastName+
                " ID: "+ID+
                " Office: "+Office;
    }
    
    public static ArrayList<Staff> ReadFromFile(){
        ArrayList<Staff> list = new ArrayList<>();
        try {
            //System.out.print("Staff FirstName\t LastName\t ID\t GPA\t \n");
            File file1= new File ("Staff.txt");
            Scanner read = new Scanner(file1);

            while(read.hasNext()){
                String line =read.nextLine();
                String[] data = line.split(" ");
                Staff obj = new Staff(
                        data[0],
                        data[1],
                        Integer.parseInt(data[2]),
                        Integer.parseInt(data[3])
                );
                list.add(obj);

                //System.out.println(line);
            }

            read.close();
        }
        catch (Exception e) 
        {
         e.printStackTrace();
        }
        
        return list;
    }
    
    static Staff getStaffByID(int id)
    {
        for(Staff staff : LMS.staffList)
        {
            if (staff.getID() == id) 
            {
                return staff;
            }
        }
        return null;
    }
    
    static void printAllStaff()
    {
        for(Staff staff : LMS.staffList)
        {
            System.out.println("\n====== instractor "+staff.getID()+"   data ========\n");
            System.out.println(staff.toString());
        }
    }
    
    ArrayList<RoomCourse> StaffCourses()
    {
        ArrayList<RoomCourse> staffCourses =  new ArrayList<>();
        for(RoomCourse roomCourse :LMS.roomCoursesList)
        {
            if(roomCourse.staff != null)
                if(roomCourse.staff.ID == ID)
                    staffCourses.add(roomCourse);
        }
        return staffCourses;
    }

}

