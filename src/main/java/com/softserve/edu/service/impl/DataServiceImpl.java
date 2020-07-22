package com.softserve.edu.service.impl;

import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import com.softserve.edu.service.DataService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Component
public class DataServiceImpl implements DataService {
    private final List<Entity> students = new ArrayList<>();
    private final List<Entity> mentors = new ArrayList<>();
    private final List<Entity> sprints = new ArrayList<>();
    private final List<Communication> communication = new ArrayList<>();
    private final List<Solution> solution = new ArrayList<>();

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

    public List<Communication> getCommunications() {
        return communication;
    }

    public List<Solution> getSolutions() {
        return solution;
    }

    public List<Solution> getSolutionsByStudent(Entity student) {
        return getSolutions().stream()
                .filter(solution -> solution.getIdStudent() == student.getId())
                .collect(Collectors.toList());
    }

    public Optional<Entity> getSprintByName(String name) {
        return sprints.stream()
                .filter(sprint -> sprint.getName().equals(name))
                .findAny();
    }

    public StudentScore getStudentResult(String studentName) {
        Optional<Entity> student = students.stream()
                .filter(entity -> entity.getName().equals(studentName))
                .findFirst();

        if (student.isPresent()) {
            List<SprintScore> sprintScore = getSolutionsByStudent(student.get())
                    .stream()
                    .collect(groupingBy(Solution::getIdSprint, summingInt(Solution::getScore)))
                    .entrySet()
                    .stream()
                    .map(entrySet -> new SprintScore(
                            getSprintById(entrySet.getKey()).get().getName(),
                            entrySet.getValue())
                    )
                    .collect(Collectors.toList());
            return new StudentScore(studentName, sprintScore);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Optional<Entity> getSprintById(Integer sprintId) {
        return sprints.stream().filter(sprint -> sprint.getId() == sprintId).findAny();
    }

    public Optional<Entity> getStudent(Integer studentId) {
        return students.stream().filter(student -> student.getId() == studentId).findAny();
    }

    public Optional<Entity> getMentorByName(String mentorName) {
        return mentors.stream().filter(entity -> entity.getName().equals(mentorName)).findAny();
    }
}
