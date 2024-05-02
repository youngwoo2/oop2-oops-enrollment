package enrollment.server.model;

import enrollment.server.constants.Major;
import enrollment.server.constants.Status;
import enrollment.server.model.course.Course;
import enrollment.server.model.course.EnrolledCourses;
import enrollment.server.model.course.Prerequisite;
import enrollment.server.constants.Period;
import enrollment.server.model.course.Courses;
import enrollment.server.model.student.Student;
import enrollment.server.model.student.Students;

import java.time.LocalDateTime;
import java.util.*;

public class Enrollment {
    private Students students; // 컬렉션 변경 가능
    private Courses courses;

    public Enrollment(Students students, Courses courses) {
        this.students = students;
        this.courses = courses;
    }

    public void register(int studentId, int courseId) {
        Student student = students.getStudent(studentId);
        Course course = courses.getCourse(courseId);

        if (!Period.check()) {
            throw new IllegalArgumentException("수강신청기간이 아닙니다.");
        }
        if (!student.checkCurrentCredits(course)) {
            throw new IllegalArgumentException("수강 가능한 학점을 초과했습니다.");
        }
        if (!student.checkMajor(course)) {
            throw new IllegalArgumentException("해당 전공이 아닙니다.");
        }
        if (!student.checkPrerequisite(course)) {
            throw new IllegalArgumentException("선수과목이 존재합니다.");
        }
        if (!course.checkCapacity()) {
            throw new IllegalArgumentException("정원을 초과했습니다.");
        }

        course.increaseCurrentCapacity();
        student.increaseCurrentCredits(course.getCredit());

        student.getEnrolledCourses().getEnrolledCourses().putIfAbsent(getCurrentSemester(), new Courses(new ArrayList<>()));
        student.getEnrolledCourses().getEnrolledCourses().get(getCurrentSemester()).getCourses().add(course);
    }

    public String getCurrentSemester() {
        StringBuilder sb = new StringBuilder();
        LocalDateTime current = LocalDateTime.now();
        sb.append(current.getYear()).append("-").append(current.getMonthValue() / 8 + 1);

        return sb.toString();
    }


    // ---------------------------------------------------------------------------

    // Arrays.asList()는 수정이 안되서 ArrayList 로 바꿨습니다.
    private static List<Student> studentsTest = new ArrayList<>(Arrays.asList(
            new Student(202200001, 6, "홍길동", new EnrolledCourses(new HashMap<>() {{
                put("2024-1", new Courses(new ArrayList<>(Arrays.asList(
                        new Course(101, 30, 3, "프로그래밍 기초", "배수지", new Prerequisite(List.of()), Major.COMPUTER, 25),
                        new Course(102, 30, 3, "자료구조", "김민정", new Prerequisite(List.of()), Major.COMPUTER, 20)
                ))));
            }}), Major.COMPUTER, Status.ENROLLED),
            new Student(202200002, 6, "김동현", new EnrolledCourses(new HashMap<>() {{
                put("2024-1",
                        new Courses(new ArrayList<>(Arrays.asList(
                                new Course(101, 30, 3, "프로그래밍 기초", "배수지", new Prerequisite(List.of()), Major.COMPUTER, 25),
                                new Course(102, 30, 3, "자료구조", "김민정", new Prerequisite(List.of()), Major.COMPUTER, 20)
                        ))));
            }}), Major.COMPUTER, Status.ENROLLED)
    ));

    // 학번 검증 로직 (입력된 id(학번)과 저장된 정보의 id와 일치하는지 찾는 메소드)

    public static Student checkStudentId(int studentId) {
        for (Student student : studentsTest) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }
//    수강신청기간체크
//    수강신청자격체크

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student validStudent = null; // 검증된 학생 변수를 미리 선언

        while (true) {
            // 로그인 출력
            System.out.println("-----로그인-----");
            // 학번 -> 계속진행 또는 나가기(q) -> 종료문구 출력하고 종료 (break)
            System.out.println("로그인하려면 학번을 입력해주세요 (나가기: q): ");
            String inputId = scanner.nextLine();

            if (inputId.equals("q")) {
                System.out.println("수강신청을 종료합니다.");
                scanner.close();
                return;
            }

            try {
                int studentId = Integer.parseInt(inputId); // 입력받은 학번을 정수형으로 변환 (현재 학번 String으로 받음)
                validStudent = checkStudentId(studentId); // 학번 존재하는지 검사

                if (validStudent == null) {
                    System.out.println("유효하지 않은 학번입니다.");
                    // 유효하지 않은 학번일 경우 로그인 처음으로 이동
                } else {
                    System.out.println(validStudent.getName() + "님 수강신청을 시작하세요.");
                    break; // 유효한 학번이면 로그인 프로세스 종료
                }
            } catch (NumberFormatException e) {
                System.out.println("학번은 숫자여야 합니다.");

            }
        }

        // 이건 일단 사용안함
        boolean isEligibleStudent = true; //  수강신청자격체크 예시를 위해 항상 참
        boolean isEnrollmentPeriod = true; //  수강신청기간체크 예시를 위해 항상 참

        if (!isEligibleStudent || !isEnrollmentPeriod) {
            System.out.println("수강 신청 기간이 아니거나 자격이 유효하지 않습니다.");
            return;
        }

        // 수강신청메뉴 출력 ( 1. 수강신청 과정 2. 수강철회과정 3. 수강신청과목조회 과정 4. 종료과정)
        while (true) {
            System.out.println("수강 신청 메뉴:");
            System.out.println("1. 수강 신청");
            System.out.println("2. 수강 철회");
            System.out.println("3. 수강 신청 과목 조회");
            System.out.println("4. 종료");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // 수강 신청 로직 (생략)
                    System.out.println("수강 신청");
                    break;
                case "2":
                    // 수강 철회 로직
                    if (validStudent != null) {
                        withdrawal(scanner, validStudent);
                    } else {
                        System.out.println("유효하지 않은 학번입니다.");
                    }
                    break;
                case "3":
                    // 수강 신청 과목 조회 로직 (생략)
                    System.out.println("수강 신청 과목 조회");
                    if (validStudent != null) {
                        enrollmentConfirm(scanner, validStudent);
                    } else {
                        System.out.println("유효하지 않은 학번입니다.");
                    }
                    break;
                case "4":
                    System.out.println("수강 신청 시스템을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
                    break;
            }
        }
    }


    // 수강철회
    public static void withdrawal(Scanner scanner, Student student) {

        // 특정 학기의 과목 목록에 접근 ( 현재 2024-1 수강신청이라서 2024-1목록만 조회 )
        //    수강신청과목출력
        Courses coursesForTerm = student.getEnrolledCourses().getEnrolledCourses().get("2024-1");
        if (coursesForTerm == null || coursesForTerm.getCourses().isEmpty()) {
            System.out.println("2024-1 학기에 등록된 과목이 없습니다.");
            return;
        }

        System.out.println(student.getName() + "님의 2024-1 학기 수강 중인 과목 목록:");
        for (Course course : coursesForTerm.getCourses()) {
            System.out.printf("과목 코드: %d, 과목명: %s\n", course.getId(), course.getName());
        }

        //    수강신청기간 체크 (생략)

        //    철회할 수업코드 or 나가기(q입력)( q입력하면 메뉴화면으로 가기)
        System.out.println("철회하려는 과목 코드를 입력해주세요 (종료하려면 q):");
        String input = scanner.nextLine();
        if (input.equals("q")) {
            return;
        }
        int courseId;
        try {
            courseId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        boolean found = false;
        Iterator<Course> iterator = coursesForTerm.getCourses().iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getId() == courseId) {
                iterator.remove();
                found = true;
                //    철회확인문구출력 -> 수강철회 종료
                System.out.println("과목이 성공적으로 철회되었습니다.");
                break;
            }
        }

        if (!found) {
            System.out.println("해당 과목 코드가 수강 목록에 없습니다.");
            System.out.println("과목 코드를 다시 입력해주세요");
            withdrawal(scanner, student); // 다시 철회 시도 // while문으로 감싸려니 student오류남
        }
        //    메모리에 저장해야되는데 일단은 철회 되었는지 확인
        System.out.println(student.getName() + "님의 2024-1 학기 수강 신청한 과목 목록:");
        for (Course course : coursesForTerm.getCourses()) {
            System.out.printf("과목 코드: %d, 과목명: %s\n", course.getId(), course.getName());
        }
    }

    //    수강신청과목조회과정
    public static void enrollmentConfirm(Scanner scanner, Student student) {

        //    수강신청기간 체크 (생략)
        //    수강신청과목출력
        Courses coursesForTerm = student.getEnrolledCourses().getEnrolledCourses().get("2024-1");
        if (coursesForTerm == null || coursesForTerm.getCourses().isEmpty()) {
            System.out.println("2024-1 학기에 등록된 과목이 없습니다.");
            return;
        }

        System.out.println(student.getName() + "님의 2024-1 학기 수강 신청한 과목 목록:");
        for (Course course : coursesForTerm.getCourses()) {
            System.out.printf("과목 코드: %d, 과목명: %s\n", course.getId(), course.getName());
        }
        //    조회되었습니다 종료하려면 q를 입력해주세요 출력
        System.out.println("조회가 끝났습니다. (메뉴로 돌아가려면 q를 눌러주세요):");
        String input = scanner.nextLine();
        if (input.equals("q")) {
            return;
        }
    }

    public Students getStudents() {
        return students;
    }

    public Courses getCourses() {
        return courses;
    }
}

