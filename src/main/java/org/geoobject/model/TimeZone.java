package org.geoobject.model;

public class TimeZone {

	private String id;
	private String gmtOffset;
	private String dstOffset;
	private String rawOffsetIndependantOfDST;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGmtOffset() {
		return gmtOffset;
	}

	public void setGmtOffset(String gmtOffset) {
		this.gmtOffset = gmtOffset;
	}

	public String getDstOffset() {
		return dstOffset;
	}

	public void setDstOffset(String dstOffset) {
		this.dstOffset = dstOffset;
	}

	public String getRawOffsetIndependantOfDST() {
		return rawOffsetIndependantOfDST;
	}

	public void setRawOffsetIndependantOfDST(String rawOffsetIndependantOfDST) {
		this.rawOffsetIndependantOfDST = rawOffsetIndependantOfDST;
	}

	@Override
	public String toString() {
		return "TimeZone [id=" + id + ", gmtOffset=" + gmtOffset + ", dstOffset=" + dstOffset
				+ ", rawOffsetIndependantOfDST=" + rawOffsetIndependantOfDST + "]";
	}

}
