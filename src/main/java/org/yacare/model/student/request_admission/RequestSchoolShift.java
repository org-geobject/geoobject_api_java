package org.yacare.model.student.request_admission;

import org.yacare.model.academic.year.SchoolShift;
import org.yacare.model.student.StudentEnrollment;

public class RequestSchoolShift {

	private SchoolShift schoolShift;
	private Boolean brotherStudent = false;
	private StudentEnrollment brother;
	private String commentMotives;

	public SchoolShift getSchoolShift() {
		return schoolShift;
	}

	public void setSchoolShift(SchoolShift schoolShift) {
		this.schoolShift = schoolShift;
	}

	public Boolean getBrotherStudent() {
		return brotherStudent;
	}

	public void setBrotherStudent(Boolean brotherStudent) {
		this.brotherStudent = brotherStudent;
	}

	public StudentEnrollment getBrother() {
		return brother;
	}

	public void setBrother(StudentEnrollment brother) {
		this.brother = brother;
	}

	public String getCommentMotives() {
		return commentMotives;
	}

	public void setCommentMotives(String commentMotives) {
		this.commentMotives = commentMotives;
	}

}
