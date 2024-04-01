/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lms;        
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LMS {    

  //  مصفوفات تقراء البيانات من الملفات  واستخدامها في الكلاسات الانها ستاتك 
    static ArrayList<Student> studentList = Student.ReadFromFile();// تجي البيانات من الكلاس ستيودنت تتخزن هنا 
    static ArrayList<Course> courseList = Course.ReadFromFile();
    static ArrayList<Staff> staffList = Staff.ReadFromFile();
    static ArrayList<Room> roomList = Room.ReadFromFile();
    // مصفوفات فاضية
    static ArrayList<RoomCourse> roomCoursesList = new ArrayList<>();
    static ArrayList<Enrollment> enrollmentList = new ArrayList<>();
    static ArrayList<TeacherCourse> teacherCoursesList = new ArrayList<>();
    
    public static void main(String[] args) 
    {
        int choice = 0;
        Scanner s = new Scanner(System.in);
        System.out.println("==========================================================");
        System.out.println("=======  Welcome IN Learning Managemnt System  ===========");
        System.out.println("==========================================================");
        
        do
        {
            System.out.println("");
            System.out.println("1. Add.");
            System.out.println("2. Delete Student From Course.");
            System.out.println("3. Search.");
            System.out.println("4. Sort.");
            System.out.println("5. Print Schedule.");
            System.out.println("6. Exit.");
            System.out.print("Enter Number of Opreation: ");
            choice = s.nextInt();
            switch(choice)
            {
                case 1:
                    addOpreations();
                    break;
                case 2:
                    deleteOpreations();
                    break;
                case 3:
                    searchOpreations();
                    break;
                case 4:
                    sortOpreations();
                    break;
                case 5:
                    printScheduleOpreations();
                    break;
            }
            

            
        } while (choice != 6);
        System.out.println("The System Will Close \n Good by .. ");
        System.exit(0);
        
        
    }

    
    private static void addOpreations() 
    {
        char choice = 'z';
        System.out.println("");
        System.out.println("a. Allocate a course."); // يختار كورس ويضيفة الي روم
        System.out.println("b. Assign a course to an instructor."); // ربط الكورس مع المدرب 
        System.out.println("c. Add a course to a student."); // تسجيل طالب في كورس 
        System.out.println("z. Back to Main Menu:"); // اختار  لي الرجوع الي السابقz  
        System.out.print("Enter Charecter of Opreation: ");  
        Scanner s = new Scanner(System.in);
        choice = s.next().charAt(0);
        
        switch(choice)
        {
            case 'a':
            case 'A':
                // for allocate course to Room   يختار كورس ويضيفة الي روم
                String code;
                System.out.println("");
                // print all courses
                Course.printAllCourses(); // استدعاء المثد الي تطبع كل الكلاسات
                // get course code from user
                System.out.println("\n Enter Course Code:");
                code = s.nextLine(); // القرائة من اليوزر
                code = s.nextLine();
                // check if course is exsist.
                Course course = Course.getCourseByCode(code); // بالكود نبحث عن الكورس   
                // if  course not found exit.     getCourseByCode تجيبها من الاري 
                if(course == null)  //اذ الكود مو موجود نل 
                {
                    System.out.println("Course Code is Wrong");
                }
                // if  course found    اذ  في كورس نطبع الغورف
                else
                {
                    // راح يطبع الغرف 
                    for(Room room : roomList )
                    {
                        // print availble room for this course Based on Room Capacity.
                        if(room.getCapacity() == course.getCourseCapacity())  // اذ سعتهاالروم  مطابقة لي الكورس
                        { //  واذ كانت الغرفة فارغة 
                            // Check if room not busy with another course.
                            if(room.isFree(course))
                            {   System.out.println("============ Room Data ===========");
                                System.out.println("\n"+room.toString());  // يطبع الغرفة
                            }
                        }  // نطبع انو يدخل رقم الغرفة
                    }  
                    // get room number from user.
                    System.out.println("\nEnter Number of Room:");
                    int roomNumber = s.nextInt();
                    // check if room is exsist.  نرسل رقم الغرفة و نتاكد اذ مو جودة  
                    Room room = Room.getRoomByCode(roomNumber);
                    if(room == null) // اذة
                    {
                        System.out.println("Room Number is Wrong");
                    }
                    //if room not found.
                    else
                    {
                        // check if the room the same of course in capacity
                        if(room.getCapacity() == course.getCourseCapacity())
                        {
                            // Check if room not busy with another course.
                            if(room.isFree(course))  //  اذ فري يحجز كورس مع مدربينة و الغرفة
                            {   // انشاء روم كورسمع و نرسل لها الفرفة و الكورس و الكونستركتر فارغ
                                RoomCourse roomCourse = new RoomCourse(room, course, null);
                                roomCoursesList.add(roomCourse);
                                System.out.println("Course "+course.getCourseCode()+" Has Allocated in room "+ room.getRoomNumber());
                            }
                            else
                            {
                                System.out.println("room is busy with another Course");
                            }
                        }
                        else
                        {
                            System.out.println("Room Capacity does not Match Course Capacity");
                        }
                    }
                }
                
                
                break;
            case 'b':
            case 'B':  // B  اذ اختار المستخدم 
                // assign course to instactor
                int staffID;
                System.out.println("");
                
                // print all staff طباعة المدربين 
                Staff.printAllStaff();
                
                // enter number of staff   رقم المدرب 
                System.out.println("\n Enter instractor Number:");
                staffID = s.nextInt();
                // 
                Staff staff = Staff.getStaffByID(staffID);
                // staff not found.
                if(staff == null)
                {
                    System.out.println("staff Number is Wrong");
                }
                // if staff found
                else
                {
                    // الكورسات المرتبطة بالغرف نعرضها ومايكون مهاها مدرب 
                    // print courses that allocates with room and  didont have instractor.
                    for(RoomCourse roomCourse: roomCoursesList)
                    {
                        
                        if(roomCourse.staff == null)
                        {
                            System.out.println(""); // نطبعها
                            System.out.println("SessionID="+roomCourse.getID()+". courseCode= "+
                                    roomCourse.course.getCourseCode()+" Room= "+
                                    roomCourse.room.getRoomNumber());
                        }
                        else
                        {
                            if(roomCourse.staff.getID() != staffID)
                            {
                                System.out.println("");
                                System.out.println(roomCourse.getID()+". courseCode= "+
                                        roomCourse.course.getCourseCode()+" Room= "+
                                        roomCourse.room.getRoomNumber());
                            }
                        }
                        
                    }
                    //  ندخل السايشن  يعرض  رقم الكورس و الغرقة
                    // get Session number from user.
                    System.out.println("\nEnter Number of Session:");
                    int sessionID = s.nextInt();
                    // check if course aloocated to room and  is exsist.
                    RoomCourse roomCourse = RoomCourse.getRoomCourseByID(sessionID);// من خلال الايدي يعرض سيشن مرتبط بالروم و الكورس
                    if(roomCourse == null) // اذ الروم كورس مو موجود يعرض خطاء
                    {
                        System.out.println("Session Number is Wrong, please allocate course to room Before");
                    }
                    else
                    {
                        // check if the room the same of course in capacity   نتاكد ان الكورس مامعاه مدرب  
                        if(roomCourse.staff == null)
                        {
                            ArrayList<RoomCourse> staffCourses = staff.StaffCourses();
                            // Teachers canâ€™t take more than 3 courses. نتاكد نه مايدب اكثر من ثلاث كرسات 
                            if(staffCourses.size() < 3)
                            {
                                for(RoomCourse rc : staffCourses)
                                {
                                    // check instractor conflict time  with another course اذ تحققت الشروط نضيف المدرب الي لكورس قبلها نتاكد من تعارض الوقت  
                                    if(rc.course.getDay() == roomCourse.course.getDay() &&  // و قت الكورس مع الوقت الخاص بالمدرب 
                                            rc.course.getHours() == roomCourse.course.getHours())
                                    {   // نطبع انو في تعارض
                                        System.out.println("this course is conflict with another course in instractor schedule");
                                        return;
                                    }
                                }  // اذ مافي تعارض اضافة هذا المدرب الي هذا الكورس
                                // when no conflict found add course to instractor.
                                roomCourse.staff = staff;
                                roomCoursesList.add(roomCourse);
                                System.out.println("Course "+roomCourse.course.getCourseCode()+" Has Assign to Instractor" + roomCourse.staff.getID());
                            }   // اذ مافي تعارض
                            else
                            {
                                System.out.println("This Instractor Has 3 courses ");
                            }
                        }
                        else
                        {
                            System.out.println("this session is used by another Instractor ");
                        }
                    }
                }
                
                break;
            case 'c':
            case 'C':
                // نفس الخطوات في اضافة طابب 
                // Add a course to a student
                int studentID;
                System.out.println("");
                
                // نتاكد اذ في كورسات لها مدرب و غرف اذ مافي  كنسل العملية بالكامل
                // check if no course allocated to room .
                if(roomCoursesList.isEmpty())
                {
                    System.out.println("You should to allocate course to room Before.");
                    return;
                }
                    
                // طباعة الطلاب في النضام
                // print all students
                Student.printAllStudent();
                
                System.out.println("\n Enter student ID:");
                studentID = s.nextInt();
                // الحصول عل اي دي الطالب 
                Student student = Student.getStudentByID(studentID);
                // student not found.  نتاكد ان الطالب موجود 
                if(student == null)
                {
                    System.out.println("student ID is Wrong");
                }
                // student found
                else
                {
                    for(RoomCourse roomCourse: roomCoursesList)
                    {
                        // print the courses that havae instractor. يطبع الكورس الي معاها مدرب
                        if(roomCourse.staff != null)
                        {
                            System.out.println("");
                            System.out.println("SessionID="+roomCourse.getID()+". courseCode= "+
                                    roomCourse.course.getCourseCode()+" Room= "+
                                    roomCourse.room.getRoomNumber());
                        }
                    }
                    
                    
                    // get Session number from user.
                    System.out.println("\nEnter Number of Session:");
                    int sessionID = s.nextInt();
                    // check if room is exsist.
                    RoomCourse roomCourse = RoomCourse.getRoomCourseByID(sessionID);
                    if(roomCourse == null)  //  نتاكد انة موجود 
                    {
                        System.out.println("Session Number is Wrong, please allocate course to room Before");
                    }
                    else
                    {    //  هل السيشن عندة مدرب 
                        // check if course have instractor .
                        if(roomCourse.staff != null)
                        {
                            
                            ArrayList<Enrollment> studentEnrollments = student.StudentEnrollments();//  جلب كرسات كل الطالب 
                            // Student must have 4 courses as maximum . //  اذ كانت الكورسات اكبر من اربعة
                            if(studentEnrollments.size() >= 4)
                            {   //  نطبع  انة  مايقدر ياخذ كورس 
                                System.out.println("the student already has 4 courses, You Can't to choose corse for this student ");
                                return;
                            }
                            for(Enrollment enrollment : studentEnrollments)
                            {
                                // check Conflict with student time. // نتاكد من تعارض الوقت 
                                if(enrollment.roomCourse.course.getDay().equals(roomCourse.course.getDay()))
                                {
                                    if(enrollment.roomCourse.course.getHours() == roomCourse.course.getHours())
                                    {     //  في تعارض 
                                        System.out.println("This course is conflict time with another course");
                                        return;
                                    }
                                }
                            }  // اذ الكورسات اقل من اربعة 
                            // Add course to student by Create new Enrollment. 
                            Enrollment enrollment = new Enrollment(student,roomCourse); // انشاء انضمام جديد اسم الطالب م الالكلاس روم
                            enrollmentList.add(enrollment); // اضافة الانضمام  + 
                            System.out.println("Course "+roomCourse.course.getCourseCode()+" Has Assign to Student" + enrollment.student.getID());
                            
                        }
                        else        // غير كذا نقولة  السكشن غير متاح
                        {
                            System.out.println("This session did not Binding with instractor Please Bind it Before ");
                        }
                    }
                    
                    
                }
                break;
                
        }
        

    }      // اذ المستخدم اختار رقم المثد تتاكد قبل حذف الطالب من الكورس انو مازال عندة كورسين على الاقل   2  

    private static void deleteOpreations() 
    {
        System.out.println("");
        // cancel if there is no enrollment.
        if(enrollmentList.isEmpty()) // التاكد في طلاب مسجلين او لا  
        {
            System.out.println("There is not Any Student Join To Any course"); // مافي طلاب  اطبع لايوج اي  طاب منضم  
            return;  
        }
         // اذ في طلاب يطبع الطلاب المنضمين  
        // print all students they have  Courses.
        for(Enrollment enrollment: enrollmentList)
        {
            System.out.println("");
            System.out.println(
                    "EnrollmentID=  "+ enrollment.getID()+
                    " StudentID=  "+ enrollment.student.getID()+ //   عند حذف طالب نكتب ايدي الانضمام مع  الكورس
                    " CourseID=  "+ enrollment.roomCourse.course.getCourseCode()); // الانها مرتبط في بعض
        }
        Scanner s = new Scanner(System.in);
        // enter enrollment ID to delete it.
        int enrollmentID ;   // ادخال  ايدي الانضمام لي الحذف
        System.out.println("\nEnter Enrollment ID To Delete it");
        enrollmentID = s.nextInt();
        
        // check if Enrollment found.
        Enrollment enrollment = Enrollment.getEnrollmentByID(enrollmentID); // جلب ايدي  الانضمام
        if(enrollment == null) //  التاكد من ان ايدي الانضمام موجود
        {
            System.out.println("enrollment ID is Wrong"); // اطبع خطاء
            return; // خروج
        }
        //Student must have at  minimum 2 courses.
        if(enrollment.student.StudentEnrollments().size() <= 2) // الانرولمنت يروح على  الطلاب ويجلب كل  كورسات  الطالب المسجل فيها 
        {         //  اذ كان عدده  اقل من  او يساوي  اثنين لا تحذف الكورس
            System.out.println("you Cant delete student from course, the student must two Courses at least ");
            return;
        }
        // اكبر من  الاثنين مسموح الحذف 
        // remove course form student.
        enrollmentList.remove(enrollment); // حذف 
        System.out.println("The student Has Deleted From Course Successfuly"); // طباعة تم الحذف
    }
// البحث عن الايدي حق الطالب واطبع بياناتها 
    private static void searchOpreations() 
    {
        char choice = 'z';
        System.out.println("");
        System.out.println("a. Search By Student ID.");
        System.out.println("b. print all students that their GPA is greater than a specific number.");
        System.out.println("c. print all students that are enrolled in a specific course.");
        System.out.println("z. Back to Main Menu:");
        System.out.print("Enter Charecter of Opreation: ");
        Scanner s = new Scanner(System.in);
        choice = s.next().charAt(0);
        
        switch(choice)
        {
            case 'a':
            case 'A':
                //Search By Student ID.
                int studentID;
                System.out.println("\n Enter student ID:");// طلب الايدي 
                studentID = s.nextInt();
                
                Student student = Student.getStudentByID(studentID);//
                // student not found.
                if(student == null)// اذ فارغ اطبع خطاء 
                {
                    System.out.println("there is not student with this ID ");
                    return;   // خروج
                }
                
                // print student data // اذ لايدي موجود اطبع الابيانات 
                System.out.println("\n====== student "+student.getID()+"   data ========\n");
                System.out.println(student.toString());
                
                break;
            case 'b':
            case 'B':   //  طباعة كل الطلاب معدلهم اكبر من الرقم المحدد 
                // print all students that their GPA is greater than a specific number.
                double gpa;
                System.out.println("\n Enter GPA Value:"); //  ادخال معدل 
                gpa = s.nextDouble();
                
                
                for(Student student1: studentList) // فور تمر على كل االطلاب 
                {
                    // find all students that their GPA is greater than a entered GPA and print them data.
                    if(student1.getGPA()> gpa) //معدل اكبر من معدل المستخدم 
                    {
                        System.out.println("\n====== student "+student1.getID()+"   data ========\n"); // تتم الطباعة 
                        System.out.println(student1.toString());
                    }
                }
                
                break;
            case 'c':
            case 'C':
                
                // print all students that are enrolled in a specific course.
                // cancel if there is no enrollment.
                if(enrollmentList.isEmpty()) // هل في طلاب طلاب مسجلين في الكورسات 
                {
                    System.out.println("There is no student enroll to any Course");// اطبع مافي طلاب 
                    return;  /// خروج
                }
                 /// في حال في طلاب 
                String corseCode;
                System.out.println("\n Enter Course Code :");// ادخل رقم الكورس 
                corseCode = s.nextLine();
                boolean courseHaveStudents = false;
                for(Enrollment enrollment : enrollmentList) // انشيك على  انضمامات  الطلاب 
                {
                    if(enrollment.roomCourse.course.getCourseCode().equals(corseCode)) //  مطابقة كود المستخدم مع  كود كورس الانضمام  
                    {   // متغير 
                        courseHaveStudents = true; //  متغير  يحتوي على طلاب 
                        System.out.println("");//نطبع بيانات الكورس 
                        System.out.println("======== student  "+ enrollment.student.getID()+"  Data"); // نطبع الطلاب 
                        System.out.println("student Name: "+ enrollment.student.getFirstName()+" "+enrollment.student.getLastName());
                        System.out.println("Room: "+ enrollment.roomCourse.room.getRoomNumber()); //   رقم الغرفة
                        System.out.println("Isntractor Name: "+ enrollment.roomCourse.staff.getFirstName()+" "+ enrollment.roomCourse.staff.getLastName()); // المدرب 
                    }
                }
                
                if(courseHaveStudents == false) // مافي طلاب 
                {
                    System.out.println("There is not any students in this course, or corse code is wrong"); //  اطبع لايوجد 
                    return; // خروج
                }
                
                break;
                
        }
        
    } // ترتيب تصاعدي اعتمادا على الايدي او تنازلي اعتماد على المعدل

    private static void sortOpreations() 
    {
        char choice = 'z';
        System.out.println("");
        System.out.println("a. Sort the students in an ascending order according to the ID numbers and print them."); //
        System.out.println("b. Sort the students in descending order according to the GPA and print them.");
        System.out.println("z. Back to Main Menu:");
        System.out.print("Enter Charecter of Opreation: ");
        Scanner s = new Scanner(System.in);
        choice = s.next().charAt(0);
        
        switch(choice)
        {
                
            case 'a': //ادخال a
            case 'A':
                //Sort the students in an ascending order according to the ID numbers and print them.
                Student.sortAscendingByID(studentList); // استدعاء الدالة  موجودة داخل الكلاس ستيودنت  ارسال لها لست استيودنت  الموجودة في الاكلاي الاساسي 
                break;
            case 'b':
            case 'B':
                //Sort the students in descending order according to the GPA and print them.
                Student.sortDescendingByGPA(studentList); // استدعاء الدالة الترتيب التنازلي موجودة داخل الكلاس ستيودنت  ارسال لها لست استيودنت الموجودة في الاكلاي الاساسي
                break;

        }
    }
// 
    private static void printScheduleOpreations() 
    {
        char choice = 'z';
        System.out.println("");
        System.out.println("a. Print a student table similar to your transcript including the name, ID, GPA."); // طباعة  تفاصيل الطالب 
        System.out.println("b. Print a Room schedule to check free times.");
        System.out.println("c. Print an instructor schedule also to indicate free times.");
        System.out.println("z. Back to Main Menu:");
        System.out.print("Enter Charecter of Opreation: ");
        Scanner s = new Scanner(System.in);
        choice = s.next().charAt(0);
        
        switch(choice)
        {
            case 'a':
            case 'A': // يطبع
                //Print a student table similar to your transcript including the name, ID, GPA.
                int studentID;
                System.out.println("\n Enter student ID:"); // الايدي
                studentID = s.nextInt();
                
                Student student = Student.getStudentByID(studentID);
                // student not found.
                if(student == null) //  الطالب موجود او لا
                {
                    System.out.println("there is not student with this ID ");
                    return;
                }
                
                 // اذ كان لطالب موجود اطبع بياناتة 
                System.out.println("\n====== student "+student.getID()+"  busy Time ========\n"); // طباعة وقت انشغال الطالب  
                System.out.println("");
                System.out.println("Day\tFrom\tTo");
                
                for(Enrollment enrollment: student.StudentEnrollments()) // الكورسات الي الطالب منشغل فيها 
                {
                      // طباعة البيانات
                    
                    System.out.println(enrollment.roomCourse.course.getDay()+"\t"+
                            enrollment.roomCourse.course.getStartTime()+"\t"+
                            (enrollment.roomCourse.course.getStartTime() + enrollment.roomCourse.course.getHours()));
                }
                 // وقت فراغ الطالب
                System.out.println("\n====== student "+student.getID()+"  Free Time ========\n");
                System.out.println("");
                System.out.println("Day\tFrom\tTo");
                    // نجلب كل الكورسات من اللست
                for(Course course : courseList)
                {
                    boolean courseFound = false;
                    for(Enrollment enrollment: student.StudentEnrollments()) // نمر على كل انضمامات الطالب 
                    {
                        if(enrollment.roomCourse.course.getDay() == course.getDay() &&    // اذ طابق الكورس انضمام الطالب يعني منشغل
                                enrollment.roomCourse.course.getHours() == course.getHours())
                        {
                            
                            courseFound = true;
                            break;
                        }
                    }
                    if(courseFound == false)
                    {     // اذ تم اضافة الطالب في  كورس اطبع انه مشغول
                        System.out.println(course.getDay()+"\t"+
                                course.getStartTime()+"\t"+
                                (course.getStartTime() + course.getHours()));
                    }
                }

 
                break;
            case 'b':
            case 'B':
                int roomNumber;
                System.out.println("\n Enter room Number :");
                roomNumber = s.nextInt();
                
                Room room = Room.getRoomByCode(roomNumber);
                // room not found.
                if(room == null)
                {
                    System.out.println("there is not room with this ID ");
                    return;
                }
                
                
                System.out.println("\n====== room "+room.getRoomNumber()+"  busy Time ========\n");
                System.out.println("");
                System.out.println("Day\tFrom\tTo");
                
                for(RoomCourse roomCourse : room.RoomEnrollments())
                {
                    
                    
                    System.out.println(roomCourse.course.getDay()+"\t"+
                            roomCourse.course.getStartTime()+"\t"+
                            (roomCourse.course.getStartTime() + roomCourse.course.getHours()));
                }
                
                System.out.println("\n====== room "+room.getRoomNumber()+"  Free Time ========\n");
                System.out.println("");
                System.out.println("Day\tFrom\tTo");
                
                for(Course course : courseList)
                {
                    boolean courseFound = false;
                    for(RoomCourse roomCourse : room.RoomEnrollments())
                    {
                        if(roomCourse.course.getDay() == course.getDay() &&
                                roomCourse.course.getHours() == course.getHours())
                        {
                            courseFound = true;
                            break;
                        }
                    }
                    if(courseFound == false)
                    {   
                        System.out.println(course.getDay()+"\t"+
                                course.getStartTime()+"\t"+
                                (course.getStartTime() + course.getHours()));
                    }
                }
                break;
            case 'c':
            case 'C':    
                int staffID;
                System.out.println("\n Enter Instractor ID :");
                staffID = s.nextInt();
                
                Staff staff = Staff.getStaffByID(staffID);
                // staff not found.
                if(staff == null)
                {
                    System.out.println("there is not instarctor with this ID ");
                    return;
                }
                
                
                System.out.println("\n====== staff "+staff.getID()+"  busy Time ========\n");
                System.out.println("");
                System.out.println("Day\tFrom\tTo");
                
                for(RoomCourse roomCourse : staff.StaffCourses())
                {
                    
                    
                    System.out.println(roomCourse.course.getDay()+"\t"+
                            roomCourse.course.getStartTime()+"\t"+
                            (roomCourse.course.getStartTime() + roomCourse.course.getHours()));
                }
                
                System.out.println("\n====== staff "+staff.getID()+"  Free Time ========\n");
                System.out.println("");
                System.out.println("Day\tFrom\tTo");
                
                for(Course course : courseList)
                {
                    boolean courseFound = false;
                    for(RoomCourse roomCourse : staff.StaffCourses())
                    {
                        if(roomCourse.course.getDay() == course.getDay() &&
                                roomCourse.course.getHours() == course.getHours())
                        {
                            courseFound = true;
                            break;
                        }
                    }
                    if(courseFound == false)
                    {   
                        System.out.println(course.getDay()+"\t"+
                                course.getStartTime()+"\t"+
                                (course.getStartTime() + course.getHours()));
                    }
                }
                break;
                
        }
    }
}

