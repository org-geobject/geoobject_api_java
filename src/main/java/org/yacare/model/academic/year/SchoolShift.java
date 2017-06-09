package org.yacare.model.academic.year;

import java.sql.Time;

import org.cendra.model.commons.EntityDomain;

public class SchoolShift extends EntityDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9095807184985752730L;

	private AcademicYear academicYear;
	private Time start;
	private Time end;

	public AcademicYear getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}

	public Time getStart() {
		return start;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public Time getEnd() {
		return end;
	}

	public void setEnd(Time end) {
		this.end = end;
	}

}
