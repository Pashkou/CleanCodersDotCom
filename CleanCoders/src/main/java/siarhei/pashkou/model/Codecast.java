package siarhei.pashkou.model;

import java.time.LocalDate;

public class Codecast extends Entity{
	private String title;
	private LocalDate published = LocalDate.now();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getPublished() {
		return published;
	}
	public void setPublished(LocalDate published) {
		this.published = published;
	}
}
