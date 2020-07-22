package com.softserve.edu;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;

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
}
