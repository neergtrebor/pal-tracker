package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Long generatedId = new Long(0);

    private HashMap timeEntries = new HashMap();

    public TimeEntry create(TimeEntry timeEntry) {

        generatedId++;

        TimeEntry ent = new TimeEntry(generatedId, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntries.put(ent.getId(), ent);
        return ent;
    }

    public List list() {
        if (null == timeEntries
                || timeEntries.isEmpty()){
            return new ArrayList();
        }
        return new ArrayList(timeEntries.values());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {

        TimeEntry ent = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntries.put(id, ent);
        return ent;
    }

    public TimeEntry find(Long id) {
        return (TimeEntry) timeEntries.get(id);
    }

    public void delete(Long id) {
        timeEntries.remove(id);
    }
}
