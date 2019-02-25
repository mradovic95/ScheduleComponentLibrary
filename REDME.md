
# Schedule Component

This component can handle schedules in different formats.

## Features

List if features:

* Can import schedule in selected format and convert it to object model
    * JSON
    * XML (not implemented)
    * CSV (not implemented)
* Can export schedule to selected format
    * JSON
    * XML (not implemented)
    * CSV (not implemented)
* Can edit schedule after import and before export

## Example

Import schedule in JSON format example:

```
        SheduleImportExportJsonImplementation sheduleImportExportJsonImplementation = new SheduleImportExportJsonImplementation();

		try {
			Schedule schedule=sheduleImportExportJsonImplementation.importShedule(new File("Proba.json"));
			System.out.println(schedule);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

```

Export schedule to JSON format:

```
        Schedule schedule = new Schedule();
        EventFactory eventFactory = new EventFactory();
		Event workEvent1 = eventFactory.getEvent("Fun Event");
        workEvent1.setStartActivity(LocalDateTime.of(2018, 1, 12, 12, 25));
        workEvent1.setEndActivity(LocalDateTime.of(2018, 1, 20, 12, 25));
        workEvent1.setTitle("event11111");
		schedule.add(workEvent1);

		Event workEvent2 = eventFactory.getEvent("Work Event");
        workEvent2.setStartActivity(LocalDateTime.of(2018, 1, 13, 12, 25));
        workEvent2.setEndActivity(LocalDateTime.of(2018, 1, 25, 12, 25));
        workEvent2.setTitle("event22222222");
		schedule.add(workEvent2);

        SheduleImportExportJsonImplementation sheduleImportExportJsonImplementation = new SheduleImportExportJsonImplementation();

		try {
            sheduleImportExportJsonImplementation.exportShedule(new File("Proba.json"), schedule);
		} catch (InvalidSheduleExcepiton | IOException e) {
			e.printStackTrace();
		}

```

## Authors

 Name          | E-mail              
---------------| ----------------- 
Mihailo Radovic| mradovic15@raf.rs 