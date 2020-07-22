package com.softserve.edu.service;

import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import com.softserve.edu.entity.Solution;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DataService {

    public void addStudent(String studentName);

    public void addMentor(String mentorName);

    public void addSprint(String sprintName);

    public void addCommunication(String studentName, String mentorName);

    public void addSolution(String studentName, String sprintName, int score);

    public List<Entity> getStudents();

    public List<Entity> getMentors();

    public List<Entity> getSprints();

    public List<Communication> getCommunications();

    public List<Solution> getSolutions();

    public Optional<Entity> getSprintByName(String sprintName);

    public Optional<Entity> getMentorByName(String mentorName);

    public Optional<Entity> getStudent(Integer id);

    public StudentScore getStudentResult(String studentName);
}
