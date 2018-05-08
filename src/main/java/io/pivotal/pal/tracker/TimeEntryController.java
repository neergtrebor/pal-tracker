package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }

    public ResponseEntity create(TimeEntry timeEntryToCreate) {

        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);

        return new ResponseEntity(timeEntry, HttpStatus.CREATED);

    }

    public ResponseEntity read(long l) {
        TimeEntry timeEntry = timeEntryRepository.find(l);
        if (null == timeEntry){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }

    }

    public ResponseEntity update(long l, TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(l, expected);
        if (null == timeEntry){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }
    }

    public ResponseEntity list() {
        List aList = timeEntryRepository.list();
        if (null == aList){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(aList, HttpStatus.OK);
        }
    }

    public ResponseEntity delete(long l) {
        timeEntryRepository.delete(l);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
