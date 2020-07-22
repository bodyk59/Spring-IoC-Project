package com.softserve.edu.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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
        //TODO
        return null;
    }

    public List<StudentScore> allStudentsResult() {
        // TODO
        return null;
    }

    public AverageScore studentAverage(String studentName) {
        // TODO
        return null;
    }

    public List<AverageScore> allStudentsAverage() {
        // TODO
        return null;
    }

    public MentorStudent mentorStudents(String mentorName) {
        // TODO
        return null;
    }
}
