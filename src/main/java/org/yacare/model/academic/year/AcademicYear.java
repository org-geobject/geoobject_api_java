package org.yacare.model.academic.year;

import java.time.Year;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.yacare.model.academic.year.admission_period.AdmissionPeriod;

public class AcademicYear {

	private Year year;
	private String description;
	private ZonedDateTime start;
	private ZonedDateTime end;	
	private List<SchoolShift> schoolShifts = new ArrayList<SchoolShift>();
	private List<AdmissionPeriod> periodsAdmission = new ArrayList<AdmissionPeriod>();

}
