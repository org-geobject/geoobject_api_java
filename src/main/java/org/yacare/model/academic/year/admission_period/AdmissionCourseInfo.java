package org.yacare.model.academic.year.admission_period;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdmissionCourseInfo {

	private List<SchoolShiftCourseAdmission> schoolShifts = new ArrayList<SchoolShiftCourseAdmission>();
	private ZonedDateTime startCourseAdmission;
	private ZonedDateTime endCourseAdmission;

	public List<SchoolShiftCourseAdmission> getSchoolShifts() {
		return schoolShifts;
	}

	public void setSchoolShifts(List<SchoolShiftCourseAdmission> schoolShifts) {
		this.schoolShifts = schoolShifts;
	}

	public ZonedDateTime getStartCourseAdmission() {
		return startCourseAdmission;
	}

	public void setStartCourseAdmission(ZonedDateTime startCourseAdmission) {
		this.startCourseAdmission = startCourseAdmission;
	}

	public ZonedDateTime getEndCourseAdmission() {
		return endCourseAdmission;
	}

	public void setEndCourseAdmission(ZonedDateTime endCourseAdmission) {
		this.endCourseAdmission = endCourseAdmission;
	}

}
