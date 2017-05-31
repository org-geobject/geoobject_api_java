package org.geoobject.model;

import org.cendra.model.commons.EntityId;

public class TimeZone extends EntityId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3129648358890647804L;
	
	private String gmtOffset;
	private String dstOffset;
	private String rawOffsetIndependantOfDST;

	
	public String getGmtOffset() {
		gmtOffset = formatValue(gmtOffset);
		return gmtOffset;
	}

	public void setGmtOffset(String gmtOffset) {
		gmtOffset = formatValue(gmtOffset);
		this.gmtOffset = gmtOffset;
	}

	public String getDstOffset() {
		dstOffset = formatValue(dstOffset);
		return dstOffset;
	}

	public void setDstOffset(String dstOffset) {
		dstOffset = formatValue(dstOffset);
		this.dstOffset = dstOffset;
	}

	public String getRawOffsetIndependantOfDST() {
		rawOffsetIndependantOfDST = formatValue(rawOffsetIndependantOfDST);
		return rawOffsetIndependantOfDST;
	}

	public void setRawOffsetIndependantOfDST(String rawOffsetIndependantOfDST) {
		rawOffsetIndependantOfDST = formatValue(rawOffsetIndependantOfDST);
		this.rawOffsetIndependantOfDST = rawOffsetIndependantOfDST;
	}

	@Override
	public TimeZone clone() throws CloneNotSupportedException {
		TimeZone other = (TimeZone) super.clone();
		
		other.setGmtOffset(this.getGmtOffset());
		other.setDstOffset(this.getDstOffset());
		other.setRawOffsetIndependantOfDST(this.getRawOffsetIndependantOfDST());
		
		return other;
	}
	
	@Override
	public String toString() {
		
		String s = super.toString();

		if (this.getRawOffsetIndependantOfDST() != null) {
			return (s + " " + this.getRawOffsetIndependantOfDST()).trim();
		}

		return "";
	}

}
