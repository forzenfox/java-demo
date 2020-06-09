package VO;

public class Student extends Person {
    public int score;
    private int grade;

    public int getScore(String type) {
        return 99;
    }

    private int getGrade(int year) {
        return 1;
    }

    @Override
    public void hello() {
        System.out.println("Student:hello");
    }
}
