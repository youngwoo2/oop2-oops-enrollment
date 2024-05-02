package enrollment.server.repository;

import enrollment.server.constants.Major;
import enrollment.server.constants.Status;
import enrollment.server.model.course.Course;
import enrollment.server.model.course.Courses;
import enrollment.server.model.course.EnrolledCourses;
import enrollment.server.model.course.Prerequisite;
import enrollment.server.model.student.Student;
import enrollment.server.model.student.Students;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Repository implements Serializable{
    public Repository() {
        write("Students",createStudents());
        write("Courses",createCourses());
    }

    public Students createStudents() {
        List<Student> students = new ArrayList<>(Arrays.asList(
                // 영우 - 수강신청 완료
                new Student(23001, 6, "이영우", new EnrolledCourses(new HashMap<>() {
                    {
                        put("2023-2", new Courses(new ArrayList<>(Arrays.asList(
                                new Course(1101, 30, 3, "자바 프로그래밍 기초", "배수지", new Prerequisite(List.of()), Major.COMPUTER, 25),
                                new Course(1103, 30, 3, "C언어 프로그래밍 기초", "김상진", new Prerequisite(List.of()), Major.COMPUTER, 25)
                        ))));
                    }{
                        put("2024-1", new Courses(new ArrayList<>(Arrays.asList(
                                new Course(1102, 30, 3, "자바 프로그래밍 심화", "배수지", new Prerequisite(List.of(1101)), Major.COMPUTER, 25),
                                new Course(1104, 30, 3, "C언어 프로그래밍 응용", "김상진", new Prerequisite(List.of(1103)), Major.COMPUTER, 25)
                        ))));
                    }
                }), Major.COMPUTER, Status.ENROLLED),
                // 성연 - 선수과목 있는거 신청 못함 , 수강신청인원 다참
                new Student(24001, 9, "정성연", new EnrolledCourses(new HashMap<>() {{
                    put("2024-1",
                            new Courses(new ArrayList<>(Arrays.asList(
                                    new Course(1101, 30, 3, "자바 프로그래밍 기초", "배수지", new Prerequisite(List.of()), Major.COMPUTER, 25),
                                    new Course(1103, 30, 3, "C언어 프로그래밍 기초", "김상진", new Prerequisite(List.of()), Major.COMPUTER, 25),
                                    new Course(1105, 30, 3, "데이터 통신", "김동혁", new Prerequisite(List.of()), Major.COMPUTER, 30)
                            ))));
                }}), Major.COMPUTER, Status.ENROLLED),
                // 모든 선수과목 수강완료(뭐든지 다 신청 할 수 있음)
                new Student(23002, 0, "김나경", new EnrolledCourses(new HashMap<>() {
                    {
                        put("2023-1", new Courses(new ArrayList<>(Arrays.asList(
                                new Course(1101, 30, 3, "자바 프로그래밍 기초", "배수지", new Prerequisite(List.of()), Major.COMPUTER, 25),
                                new Course(1103, 30, 3, "C언어 프로그래밍 기초", "김상진", new Prerequisite(List.of()), Major.COMPUTER, 25)
                        ))));
                    }{
                        put("2023-2", new Courses(new ArrayList<>(Arrays.asList(
                                new Course(1102, 30, 3, "자바 프로그래밍 심화", "배수지", new Prerequisite(List.of(1101)), Major.COMPUTER, 25),
                                new Course(1104, 30, 3, "C언어 프로그래밍 응용", "김상진", new Prerequisite(List.of(1103)), Major.COMPUTER, 25)
                        ))));
                    }
                }), Major.COMPUTER, Status.ENROLLED),
                // 예진 - 휴학중인 상태(신청불가)
                new Student(23003, 0, "정예진", new EnrolledCourses(new HashMap<>() {
                    {
                        put("2023-1", new Courses(new ArrayList<>(Arrays.asList(
                                new Course(1101, 30, 3, "자바 프로그래밍 기초", "배수지", new Prerequisite(List.of()), Major.COMPUTER, 25),
                                new Course(1103, 30, 3, "C언어 프로그래밍 기초", "김상진", new Prerequisite(List.of()), Major.COMPUTER, 25)
                        ))));
                    }{
                        put("2023-2", new Courses(new ArrayList<>(Arrays.asList(
                                new Course(1102, 30, 3, "자바 프로그래밍 심화", "배수지", new Prerequisite(List.of(1101)), Major.COMPUTER, 25),
                                new Course(1104, 30, 3, "C언어 프로그래밍 응용", "김상진", new Prerequisite(List.of(1103)), Major.COMPUTER, 25)
                        ))));
                    }
                }), Major.COMPUTER, Status.LEAVE_OF_ABSENCE),
                // 홍현 - 미술학과과목만 신청가능(선수과목없음)
                new Student(23003, 3, "김홍현", new EnrolledCourses(new HashMap<>() {
                    {
                        put("2024-1", new Courses(new ArrayList<>(Arrays.asList(
                                new Course(7101, 30, 3, "동양화", "김유정", new Prerequisite(List.of()), Major.FINE_ARTS, 25)
                        ))));
                    }
                }), Major.FINE_ARTS, Status.ENROLLED),
                // 성일 - 18학점 초과하여 수강신청하려는 경우
                new Student(23004, 3, "변성일", new EnrolledCourses(new HashMap<>() {
                    {
                        put("2024-1", new Courses(new ArrayList<>(Arrays.asList(
                                new Course(7101, 30, 3, "한국사학", "김재영", new Prerequisite(List.of()), Major.HISTORY, 25),
                                new Course(7102, 30, 3, "동양사학", "김재영", new Prerequisite(List.of()), Major.HISTORY, 25),
                                new Course(7103, 30, 3, "서양사학", "김재영", new Prerequisite(List.of()), Major.HISTORY, 25),
                                new Course(7104, 30, 3, "규장각과 한국문화", "김원훈", new Prerequisite(List.of()), Major.HISTORY, 25),
                                new Course(7105, 30, 3, "한국현대사의 이해", "김원훈", new Prerequisite(List.of()), Major.HISTORY, 25),
                                new Course(7106, 30, 3, "테마 중국사", "김원훈", new Prerequisite(List.of()), Major.HISTORY, 25)
                        ))));
                    }
                }), Major.HISTORY, Status.ENROLLED)
        ));

        return new Students(students);
    }

    public Courses createCourses() {
        List<Course> courses = new ArrayList<>(Arrays.asList(
                new Course(1101, 30, 3, "자바 프로그래밍 기초", "배수지", new Prerequisite(List.of()), Major.COMPUTER, 25),
                new Course(1102, 30, 3, "자바 프로그래밍 심화", "배수지", new Prerequisite(List.of(1101)), Major.COMPUTER, 25),
                new Course(1103, 30, 3, "C언어 프로그래밍 기초", "김상진", new Prerequisite(List.of()), Major.COMPUTER, 25),
                new Course(1104, 30, 3, "C언어 프로그래밍 응용", "김상진", new Prerequisite(List.of(1103)), Major.COMPUTER, 25),
                new Course(1105, 30, 3, "데이터 통신", "김동혁", new Prerequisite(List.of()), Major.COMPUTER, 30),
                new Course(1106, 30, 3, "알고리즘", "김동혁", new Prerequisite(List.of(1101, 1102, 1103, 1104)), Major.COMPUTER, 25),
                new Course(2101, 30, 3, "기계시스템설계1", "이승욱", new Prerequisite(List.of()), Major.MECHANICAL, 25),
                new Course(2102, 30, 3, "기계시스템설계2", "이승욱", new Prerequisite(List.of(2101)), Major.MECHANICAL, 25),
                new Course(3101, 30, 3, "영작문", "김아현", new Prerequisite(List.of()), Major.ENGLISH, 25),
                new Course(4101, 30, 3, "한국사학", "김재영", new Prerequisite(List.of()), Major.HISTORY, 25),
                new Course(4102, 30, 3, "동양사학", "김재영", new Prerequisite(List.of(4101)), Major.HISTORY, 25),
                new Course(4103, 30, 3, "서양사학", "김재영", new Prerequisite(List.of(4101)), Major.HISTORY, 25),
                new Course(4104, 30, 3, "규장각과 한국문화", "김원훈", new Prerequisite(List.of(4101)), Major.HISTORY, 25),
                new Course(4105, 30, 3, "한국현대사의 이해", "김원훈", new Prerequisite(List.of(4101)), Major.HISTORY, 25),
                new Course(4106, 30, 3, "테마 중국사", "김원훈", new Prerequisite(List.of(4101)), Major.HISTORY, 25),
                new Course(4107, 30, 3, "동아시아의 왕권", "김원훈", new Prerequisite(List.of(4101)), Major.HISTORY, 25),
                new Course(5101, 30, 3, "유체역학", "염창홍", new Prerequisite(List.of()), Major.PHYSICS, 25),
                new Course(6101, 30, 3, "분자생화학", "임채영", new Prerequisite(List.of()), Major.CHEMISTRY, 25),
                new Course(7101, 30, 3, "동양화", "김유정", new Prerequisite(List.of()), Major.FINE_ARTS, 25),
                new Course(7102, 30, 3, "서양화", "김유정", new Prerequisite(List.of()), Major.FINE_ARTS, 25),
                new Course(8101, 30, 3, "테니스", "김동근", new Prerequisite(List.of()), Major.PHYSICAL_EDUCATION, 25)
        ));

        return new Courses(courses);
    }



    public <T> void write(String name, T students) { // 매개변수로 students객체배열을 받는다. Enrollment에서도 이 메소드를 호출할 수 있다.
        File file = new File("src/main/java/enrollment/server/repository/", name+".ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T read(String name) {
        File file = new File("src/main/java/enrollment/server/repository/", name+".ser");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            return (T) obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public void coursesToFile(Courses courses) {
//        File file = new File("src/main/java/enrollment/server/repository/", "courseslist.ser");
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
//            oos.writeObject(courses);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public Courses fileToCourses() {
//        File file = new File("src/main/java/enrollment/server/repository/", "courseslist.ser");
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
//            Object obj = ois.readObject();
//            return (Courses) obj;
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    // enrollment에서 가져올 학생 정보
//    public Student findStudent(int id) {
//        for (int i = 0; i < students.size(); i++) {
//            if (students.get(i).getId() == id) {
//                return students.get(i);
//            }
//        }
//        return null;
//    }
    // enrollment에서 가져올 수업 정보
//    public Course findCourse(int id) {
//        for (int i = 0; i < courses.size(); i++) {
//            if (courses.get(i).getId() == id) {
//                return courses.get(i);
//            }
//        }
//        return null;
//    }
}
