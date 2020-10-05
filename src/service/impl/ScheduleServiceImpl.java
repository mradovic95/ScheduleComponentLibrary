package service.impl;

import exceptions.InvalidScheduleException;
import importexport.ScheduleImportExport;
import model.Background;
import model.Event;
import model.Schedule;
import service.EventService;
import service.ScheduleService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleImportExport<File> scheduleImportExport;
    private final EventService eventService;

    public ScheduleServiceImpl(ScheduleImportExport<File> scheduleImportExport, EventService eventService) {
        this.scheduleImportExport = scheduleImportExport;
        this.eventService = eventService;
    }

    @Override
    public void checkSchedule(Schedule schedule) throws InvalidScheduleException {
        List<Event> events = schedule.getEvents();
        for (int i = 0; i < events.size(); i++) {
            for (int j = i + 1; j < events.size(); j++) {

                if (!events.get(i).getClass().isAnnotationPresent(Background.class))
                    break;

                if (!events.get(j).getClass().isAnnotationPresent(Background.class))
                    continue;

                if (eventService.checkOverlappingBetweenEvents(events.get(i), events.get(j)))
                    throw new InvalidScheduleException(events.get(i), events.get(j));
            }
        }
    }

    @Override
    public Schedule loadSchedule(File file) throws IOException {
        return scheduleImportExport.importSchedule(file);
    }

    @Override
    public void saveSchedule(File file, Schedule schedule) throws IOException {
        scheduleImportExport.exportSchedule(file, schedule);
    }

}
