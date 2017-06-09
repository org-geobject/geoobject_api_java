package org.yacare.model.student.request_admission;

import org.yacare.model.academic.year.admission_period.SchoolShiftCourseAdmission;

public class RequestAdmissionCourse {

	private Boolean courseAdmission = true;
	private SchoolShiftCourseAdmission schoolShiftCourseAdmission;

	public Boolean getCourseAdmission() {
		return courseAdmission;
	}

	public void setCourseAdmission(Boolean courseAdmission) {
		this.courseAdmission = courseAdmission;
	}

	public SchoolShiftCourseAdmission getSchoolShiftCourseAdmission() {
		return schoolShiftCourseAdmission;
	}

	public void setSchoolShiftCourseAdmission(SchoolShiftCourseAdmission schoolShiftCourseAdmission) {
		this.schoolShiftCourseAdmission = schoolShiftCourseAdmission;
	}

}
