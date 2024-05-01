package enrollment.server.model.course;

import java.util.List;

public class Courses {
    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public Course getCourse(int courseId) {
        for (Course course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }

        throw new IllegalArgumentException("존재하지 않는 과목입니다.");
    }

    public List<Course> getCourses() {
        return courses;
    }
}
