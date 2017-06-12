package org.yacare.model.academic.year.admission_period;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.yacare.model.academic.Career;
import org.yacare.model.academic.year.AcademicYear;
import org.yacare.model.student.request_admission.RequestAdmission;

public class AdmissionPeriod {
	
	private AcademicYear forAcademicYear;
	private String title;
	private String comment;
	private List<Career> careers = new ArrayList<Career>();
	private ZonedDateTime start;
	private ZonedDateTime end;		
	private Boolean onlyExam = false;
	private AdmissionCourseInfo admissionCourseInfo = new AdmissionCourseInfo();
	private List<RequestAdmission> requests = new ArrayList<RequestAdmission>();

}
