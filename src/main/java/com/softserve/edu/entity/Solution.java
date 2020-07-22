package com.softserve.edu.entity;

public class Solution {
    private int idStudent;
    private int idSprint;
    private int score;

    public Solution(int idStudent, int idSprint, int score) {
        this.idStudent = idStudent;
        this.idSprint = idSprint;
        this.score = score;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public int getIdSprint() {
        return idSprint;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;

        Solution solution = (Solution) o;

        if (getIdStudent() != solution.getIdStudent()) return false;
        if (getIdSprint() != solution.getIdSprint()) return false;
        return getScore() == solution.getScore();
    }

    @Override
    public int hashCode() {
        int result = getIdStudent();
        result = 31 * result + getIdSprint();
        result = 31 * result + getScore();
        return result;
    }

    @Override
    public String toString() {
        return String.format("Solution idStudent: %d, idSprint: %d, score: %d", idStudent, idSprint, score);
    }
}
