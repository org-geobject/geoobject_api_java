package org.cendra.model.commons;

public class File extends EntityErasable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6745225032776174534L;

	private String title;
	private String fileName;
	private String link;
	private String path;

	public String getTitle() {
		title = formatValue(title);
		return title;
	}

	public void setTitle(String title) {
		title = formatValue(title);
		this.title = title;
	}

	public String getFileName() {
		fileName = formatValueFileName(fileName);
		return fileName;
	}

	public void setFileName(String fileName) {
		fileName = formatValueFileName(fileName);
		this.fileName = fileName;
	}

	public String getLink() {
		link = formatValue(link);
		return link;
	}

	public void setLink(String link) {
		link = formatValue(link);
		this.link = link;
	}

	public String getPath() {
		path = formatValue(path);
		return path;
	}

	public void setPath(String path) {
		path = formatValue(path);
		this.path = path;
	}
	
	@Override
	public File clone() throws CloneNotSupportedException {
		File other = (File) super.clone();

		other.setTitle(this.getTitle());
		other.setFileName(this.getFileName());
		other.setLink(this.getLink());
		other.setPath(this.getPath());

		return other;
	}
	
	@Override
	public int compareTo(Object o) {

		if (o != null && o instanceof File == false) {
			throw new IllegalArgumentException("It was expected " + File.class.getSimpleName()
					+ " and not " + o.getClass().getCanonicalName());
		}

		if (this.getFileName() != null && o != null) {
			return this.getFileName().compareTo(((File) o).getFileName());
		}

		if (this.getFileName() != null && o == null) {
			return this.getFileName().compareTo("");
		}

		return "".compareTo(((File) o).getFileName());
	}
	
	@Override
	public String toString() {

		String s = super.toString();

		if (this.getFileName() != null) {
			return (s + " (" + this.getFileName() + ")").trim();
		}

		return "";
	}

	protected String formatValueFileName(String value) {

		value = super.formatValue(value);

		if (value != null) {
			value = value.toLowerCase();
		}

		return value;
	}
	
	

}
