package org.cendra.model.commons;

public class EntityErasable extends EntityId implements Erasable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6388391477100500120L;
	
	private Boolean erased;

	public Boolean getErased() {
		if(erased == null){
			erased = false;
		}			
		return erased;
	}

	public void setErased(Boolean erased) {
		if(erased == null){
			erased = false;
		}
		this.erased = erased;
	}
	
	@Override
	public EntityErasable clone() throws CloneNotSupportedException {
		EntityErasable other = (EntityErasable) super.clone();

		other.setErased(this.getErased());

		return other;
	}

}
