package siarhei.pashkou.model;

import java.time.LocalDate;

public class Codecast extends Entity{
	private String title;
	private LocalDate published = LocalDate.now();
	private String permalink;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getPublishedDate() {
		return published;
	}
	public void setPublished(LocalDate published) {
		this.published = published;
	}
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
}
