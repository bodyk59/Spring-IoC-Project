package com.softserve.edu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.entity.Communication;
import com.softserve.edu.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import com.softserve.edu.dto.StudentScore;
import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;

@Service
public class MarathonServiceImpl implements MarathonService {

    private DataService dataService;

    @Autowired
    public MarathonServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    private List<String> entityListToStringList(List<Entity> list) {
        return list.stream()
                .map(Entity::toString)
                .collect(Collectors.toList());
    }

    public List<String> getStudents() {
        return entityListToStringList(dataService.getStudents());
    }

    public List<String> getMentors() {
        return entityListToStringList(dataService.getMentors());
    }

    public StudentScore studentResult(String studentName) {
        return dataService.getStudentResult(studentName);
    }

    public List<StudentScore> allStudentsResult() {
        return dataService.getStudents().stream()
                .map(student -> studentResult(student.getName()))
                .collect(Collectors.toList());
    }

    public AverageScore studentAverage(String studentName) {
        double averageScore = studentResult(studentName).getSprintScore().stream()
                .mapToDouble(SprintScore::getScore)
                .average()
                .orElse(Double.NaN);
        return new AverageScore(studentName, averageScore);
    }

    public List<AverageScore> allStudentsAverage() {
        return dataService.getStudents().stream()
                .map(student -> studentAverage(student.getName()))
                .collect(Collectors.toList());
    }

    public MentorStudent mentorStudents(String mentorName) {
        Entity mentor = dataService.getMentorByName(mentorName)
                .orElseThrow(IllegalArgumentException::new);

        List<Communication> mentorCommunications = dataService
                .getCommunications()
                .stream()
                .filter(communication -> communication.getIdMentor() == mentor.getId())
                .collect(Collectors.toList());

        List<String> mentorStudentsList = mentorCommunications.stream()
                .map(communication -> dataService.getStudent(communication.getIdStudent()).get().getName())
                .collect(Collectors.toList());

        return new MentorStudent(mentorName, mentorStudentsList);
    }

}
