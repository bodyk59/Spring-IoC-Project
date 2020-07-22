package com.softserve.edu.service.impl;

import java.util.List;

import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import com.softserve.edu.service.DataService;

public class DataServiceImpl implements DataService {
    private List<Entity> students;
    private List<Entity> mentors;
    private List<Entity> sprints;
    private List<Communication> communication;
    private List<Solution> solution;

    public void addStudent(String studentName) {
        if (studentName != null) {
            students.add(new Entity(studentName));
        }
    }

    public void addMentor(String mentorName) {
        if (mentorName != null) {
            mentors.add(new Entity(mentorName));
        }
    }

    public void addSprint(String sprintName) {
        if (sprintName != null) {
            sprints.add(new Entity(sprintName));
        }
    }

    public void addCommunication(String studentName, String mentorName) {
        int studentId = getIdOnName(students, studentName);
        int mentorId = getIdOnName(mentors, mentorName);
        if (studentId == 0 || mentorId == 0) {
            //such student or mentor does not exist
            //may be useful int the future
            return;
        }
        communication.add(new Communication(studentId, mentorId));
    }

    public void addSolution(String studentName, String sprintName, int score) {
        int studentId = getIdOnName(students, studentName);
        int sprintId = getIdOnName(sprints, sprintName);
        if (studentId == 0 || sprintId == 0) {
            //such student or sprint does not exist
            //may be useful int the future
            return;
        }
        if (score <= 0 || score > 12) {
            //base check
            //may be useful int the future
            return;
        }
        solution.add(new Solution(studentId, sprintId, score));
    }

    private int getIdOnName(List<Entity> list, String name) {
        return list.stream()
                .filter(entity -> entity.getName().equals(name))
                .map(Entity::getId)
                .findFirst()
                .orElse(0);
    }

    public List<Entity> getStudents() {
        return students;
    }

    public List<Entity> getMentors() {
        return mentors;
    }

    public List<Entity> getSprints() {
        return sprints;
    }

    public List<Communication> getCommunication() {
        return communication;
    }

    public List<Solution> getSolution() {
        return solution;
    }

    //Maybe something else?
}
