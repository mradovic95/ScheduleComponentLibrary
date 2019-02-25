package test;


import exceptions.InvalidScheduleException;
import importexport.ScheduleImportExportJson;
import importexport.impl.SheduleImportExportJsonImplementation;
import model.Event;
import model.EventFactory;
import model.Schedule;
import service.EventService;
import service.ScheduleService;
import service.impl.EventServiceImpl;
import service.impl.ScheduleServiceImpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class TestExport {

    public static void main(String[] args) {

        Schedule schedule = new Schedule();
        schedule.setTitle("Simple schedule");
        EventFactory eventFactory = new EventFactory();
        Event workEvent1 = eventFactory.getEvent("FunEvent");
        workEvent1.setStartActivity(LocalDateTime.of(2018, 1, 12, 12, 25));
        workEvent1.setEndActivity(LocalDateTime.of(2018, 1, 20, 12, 25));
        workEvent1.setTitle("fun event");
        schedule.add(workEvent1);

        Event workEvent2 = eventFactory.getEvent("WorkEvent");
        workEvent2.setStartActivity(LocalDateTime.of(2018, 1, 13, 12, 25));
        workEvent2.setEndActivity(LocalDateTime.of(2018, 1, 25, 12, 25));
        workEvent2.setTitle("work event");
        schedule.add(workEvent2);

        ScheduleImportExportJson scheduleImportExportJsonImplementation = new SheduleImportExportJsonImplementation();
        EventService eventService = new EventServiceImpl();
        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleImportExportJsonImplementation, eventService);

        try {
            scheduleService.checkSchedule(schedule);
            scheduleService.saveSchedule(new File("Proba.json"), schedule);
        } catch (IOException | InvalidScheduleException e) {
            e.printStackTrace();
        }

    }

}
