package org.yacare.model.academic.year;

import java.time.Year;
import java.time.ZonedDateTime;

public class AcademicYear {

	private Year year;
	private String description;
	private ZonedDateTime start;
	private ZonedDateTime end;

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ZonedDateTime getStart() {
		return start;
	}

	public void setStart(ZonedDateTime start) {
		this.start = start;
	}

	public ZonedDateTime getEnd() {
		return end;
	}

	public void setEnd(ZonedDateTime end) {
		this.end = end;
	}

}
