package enrollment.server.model.course;

import enrollment.server.constants.Major;

import java.io.Serializable;

public class Course implements Serializable {
    private final int id;
    private int capacity; // 증원가능성이 있으니 final로 하지 않았음
    private int credit; // 수업의 학점
    private String name;
    private String professor;
    private Prerequisite prerequisite; // 선행과목 - Course의 id 값들이 저장됨
    private Major major;
    private int currentCapacity;

    public Course(int id, int capacity, int credit, String name, String professor, Prerequisite prerequisite,
                  Major major,
                  int currentCapacity) {
        this.id = id;
        this.capacity = capacity;
        this.credit = credit;
        this.name = name;
        this.professor = professor;
        this.prerequisite = prerequisite;
        this.major = major;
        this.currentCapacity = currentCapacity;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCredit() {
        return credit;
    }

    public String getName() {
        return name;
    }

    public String getProfessor() {
        return professor;
    }

    public Prerequisite getPrerequisite() {
        return prerequisite;
    }

    public Major getMajor() {
        return major;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public boolean checkCapacity() {
        return capacity-currentCapacity>0;
    }

    public void increaseCurrentCapacity() {
        this.currentCapacity++;
    }
}