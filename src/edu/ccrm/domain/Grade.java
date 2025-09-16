package edu.ccrm.domain;

public enum Grade{
    S(10), A(9), B(8), C(7), F(0);

    private final int gradePoints;

    Grade(int points){
        this.gradePoints = points;
    }

    public int getGradePoints(){
        return gradePoints;
    }
}
