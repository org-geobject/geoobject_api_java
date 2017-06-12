package org.yacare.model.academic.year.admission_period;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdmissionCourseInfo {

	private List<SchoolShiftCourseAdmission> schoolShifts = new ArrayList<SchoolShiftCourseAdmission>();
	private ZonedDateTime startDate;
	private ZonedDateTime endDate;

}
