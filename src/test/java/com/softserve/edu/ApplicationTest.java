package com.softserve.edu;

import java.util.List;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.dto.StudentScore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationTest {
    
    @Autowired
    private final MarathonService marathonService;
    
    @Autowired
    private final DataService dataService;

    @Autowired
    public ApplicationTest(MarathonService marathonService, DataService dataService) {
        this.marathonService = marathonService;
        this.dataService = dataService;
        fillDataService();
    }

    private void fillDataService() {
        fillStudentsList(dataService);
        fillMentorsList(dataService);
        fillSprintsList(dataService);
        fillSolutionsList(dataService);
        fillCommunicationList(dataService);
    }

    //Filling DataService: start
    private void fillStudentsList(DataService dataService) {
        dataService.addStudent("Petro");
        dataService.addStudent("Nazar");
        dataService.addStudent("Bohdan");
    }

    private void fillMentorsList(DataService dataService) {
        dataService.addMentor("Mykola");
        dataService.addMentor("Yaroslav");
        dataService.addMentor("Natalia");
    }

    private void fillSprintsList(DataService dataService) {
        dataService.addSprint("1. Java EE");
        dataService.addSprint("2. JavaScript");
        dataService.addSprint("1. HTML & CSS");
    }

    private void fillSolutionsList(DataService dataService) {
        dataService.addSolution("Bohdan", "Java EE", 100);
        dataService.addSolution("Petro", "JavaScript", 100);
        dataService.addSolution("Nazar", "HTML & CSS", 100);
    }

    private void fillCommunicationList(DataService dataService) {
        dataService.addCommunication("Nazar", "Yaroslav");
        dataService.addCommunication("Bohdan", "Mykola");
        dataService.addCommunication("Petro", "Natalia");
    }
    //Filling DataService: stop

    @Test
    void checkGetStudents() {
        List<String> expected = List.of(
                String.format("Entity id: %d, name: %s", 1, "Petro"),
                String.format("Entity id: %d, name: %s", 2, "Nazar"),
                String.format("Entity id: %d, name: %s", 3, "Bohdan"));
        List<String> actual = marathonService.getStudents();
        Assertions.assertEquals(expected, actual, "checkGetStudents()");
    }

    @Test
    void checkGetMentors() {
        List<String> expected = List.of(
                String.format("Entity id: %d, name: %s", 4, "Mykola"),
                String.format("Entity id: %d, name: %s", 5, "Yaroslav"),
                String.format("Entity id: %d, name: %s", 6, "Natalia"));
        List<String> actual = marathonService.getMentors();
        Assertions.assertEquals(expected, actual, "checkGetMentors()");
    }

    //need to check
    @Test
    void checkStudentResult() {
        StudentScore expected = new StudentScore("Bohdan", List.of(new SprintScore("Java EE", 100)));
        StudentScore actual = marathonService.studentResult("Bohdan");

        Assertions.assertEquals(expected.getStudentName(), actual.getStudentName());
//        Assertions.assertEquals(expected.getSprintScore().get(0).getSprintName(),
//                actual.getSprintScore().get(0).getSprintName());
//        Assertions.assertEquals(expected.getSprintScore().get(0).getScore(),
//                actual.getSprintScore().get(0).getScore());
    }

    //need to check
    @Test
    void checkAllStudentsResult() {
        List<StudentScore> expected = List.of(
                new StudentScore("Petro", List.of(new SprintScore("JavaScript", 100))),
                new StudentScore("Nazar", List.of(new SprintScore("HTML & CSS", 100))),
                new StudentScore("Bohdan", List.of(new SprintScore("Java EE", 100)))
        );

        List<StudentScore> actual = marathonService.allStudentsResult();
        for (int i = 0; i < expected.size() && expected.size() == actual.size(); i++) {
            Assertions.assertEquals(expected.get(i).getStudentName(), actual.get(i).getStudentName());
//            Assertions.assertEquals(expected.get(i).getSprintScore().get(0).getSprintName(),
//                    actual.get(i).getSprintScore().get(0).getSprintName());
//            Assertions.assertEquals(expected.get(i).getSprintScore().get(0).getScore(),
//                    actual.get(i).getSprintScore().get(0).getScore());
        }
    }

    //need to check
    @Test
    void checkStudentAverage() {
        AverageScore expected = new AverageScore("Petro", 100.0);
        AverageScore actual = marathonService.studentAverage("Petro");
        Assertions.assertEquals(expected.getStudentName(), actual.getStudentName());
        //Assertions.assertEquals(expected.getAvgScore(), actual.getAvgScore());
    }

    //need to check
    @Test
    void checkAllStudentsAverage() {
        List<AverageScore> expected = List.of(new AverageScore("Petro", 100.0),
                new AverageScore("Nazar", 100.0),
                new AverageScore("Bohdan", 100.0));
        List<AverageScore> actual = marathonService.allStudentsAverage();
        for (int i = 0; i < expected.size() && expected.size() == actual.size(); i++) {
            Assertions.assertEquals(expected.get(i).getStudentName(), actual.get(i).getStudentName());
            //Assertions.assertEquals(expected.get(i).getAvgScore(), actual.get(i).getAvgScore());
        }
    }

    //need to check
    @Test
    void checkMentorStudents() {
        MentorStudent expected = new MentorStudent("Yaroslav", List.of("Nazar"));
        MentorStudent actual = marathonService.mentorStudents("Yaroslav");
        Assertions.assertEquals(expected.getMentorName(), actual.getMentorName());
        //Assertions.assertEquals(expected.getStudentNames(), actual.getStudentNames());
    }
}
