package enrollment.server.model.student;

import java.util.List;

public class Students {
    private final List<Student> students;

    public Students(List<Student> students) {
        this.students = students;
    }

    public Student getStudent(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }

        throw new IllegalArgumentException("존재하지 않는 학생입니다.");
    }
}
