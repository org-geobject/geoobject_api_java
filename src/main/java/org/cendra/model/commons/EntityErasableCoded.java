package org.cendra.model.commons;

public class EntityErasableCoded extends EntityErasable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1454368029017932127L;

	private String code;

	public String getCode() {
		code = formatValueCode(code);
		return code;
	}

	public void setCode(String code) {
		code = formatValueCode(code);
		this.code = code;
	}

	@Override
	public EntityErasableCoded clone() throws CloneNotSupportedException {
		EntityErasableCoded other = (EntityErasableCoded) super.clone();

		other.setCode(this.getCode());

		return other;
	}

	@Override
	public int compareTo(Object o) {

		if (o != null && o instanceof EntityErasableCoded == false) {
			throw new IllegalArgumentException("It was expected " + EntityErasableCoded.class.getSimpleName()
					+ " and not " + o.getClass().getCanonicalName());
		}

		if (this.getCode() != null && o != null) {
			return this.getCode().compareTo(((EntityErasableCoded) o).getCode());
		}

		if (this.getCode() != null && o == null) {
			return this.getCode().compareTo("");
		}

		return "".compareTo(((EntityErasableCoded) o).getCode());
	}

	@Override
	public String toString() {

		String s = super.toString();

		if (this.getCode() != null) {
			return (s + " (" + this.getCode() + ")").trim();
		}

		return "";
	}

	protected String formatValueCode(String value) {

		value = super.formatValue(value);

		if (value != null) {
			value = value.toLowerCase();
		}

		return value;
	}

}
