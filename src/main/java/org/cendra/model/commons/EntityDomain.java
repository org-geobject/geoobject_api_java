package org.cendra.model.commons;

public class EntityDomain extends EntityErasable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2750073722800758098L;

	private String name;
	private String description;

	public String getName() {
		name = formatValue(name);
		return name;
	}

	public void setName(String name) {
		name = formatValue(name);
		this.name = name;
	}

	public String getDescription() {
		description = formatValue(description);
		return description;
	}

	public void setDescription(String description) {
		description = formatValue(description);
		this.description = description;
	}

	@Override
	public EntityDomain clone() throws CloneNotSupportedException {
		EntityDomain other = (EntityDomain) super.clone();

		other.setName(this.getName());
		other.setDescription(this.getDescription());

		return other;
	}
	
	@Override
	public int compareTo(Object o) {

		if (o != null && o instanceof EntityDomain == false) {
			throw new IllegalArgumentException("It was expected " + EntityDomain.class.getSimpleName() + " and not "
					+ o.getClass().getCanonicalName());
		}

		if (this.getName() != null && o != null) {
			return this.getName().compareTo(((EntityDomain) o).getName());
		}

		if (this.getName() != null && o == null) {
			return this.getName().compareTo("");
		}

		return "".compareTo(((EntityDomain) o).getName());
	}
	
	@Override
	public String toString() {
		
		String s = super.toString();

		if (this.getName() != null) {
			return (s + " " + this.getName()).trim();
		}

		return "";
	}

}
