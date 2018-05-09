package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private final CounterService counter;
    private final GaugeService gauge;
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(
                TimeEntryRepository timeEntryRepository,
                CounterService counter,
                GaugeService gauge) {

        this.timeEntryRepository = timeEntryRepository;
        this.counter = counter;
        this.gauge = gauge;
    }
    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);

        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());

        return new ResponseEntity(timeEntry, HttpStatus.CREATED);

    }
    @GetMapping("/{id}")
    public ResponseEntity read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if (null == timeEntry){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            counter.increment("TimeEntry.read");
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(id, expected);
        if (null == timeEntry){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            counter.increment("TimeEntry.updated");
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity list() {
        List aList = timeEntryRepository.list();
        counter.increment("TimeEntry.listed");

        if (null == aList){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(aList, HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        timeEntryRepository.delete(id);

        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
