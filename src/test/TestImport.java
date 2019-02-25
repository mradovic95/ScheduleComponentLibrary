package test;

import exceptions.InvalidScheduleException;
import importexport.ScheduleImportExportJson;
import importexport.impl.SheduleImportExportJsonImplementation;
import model.Schedule;
import service.EventService;
import service.ScheduleService;
import service.impl.EventServiceImpl;
import service.impl.ScheduleServiceImpl;

import java.io.File;
import java.io.IOException;

public class TestImport {

    public static void main(String[] args) {

        ScheduleImportExportJson scheduleImportExportJsonImplementation = new SheduleImportExportJsonImplementation();
        EventService eventService = new EventServiceImpl();
        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleImportExportJsonImplementation, eventService);

        try {
            Schedule schedule = scheduleService.loadSchedule(new File("Proba.json"));
            scheduleService.checkSchedule(schedule);
            System.out.println(schedule.getTitle());
            schedule.getEvents().forEach(event -> System.out.println(event.getTitle()));
        } catch (IOException | InvalidScheduleException e) {
            e.printStackTrace();
        }

    }

}
