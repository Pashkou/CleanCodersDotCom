package siarhei.pashkou.fixture;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Codecast;

public class GivenCodecasts {
	private String title;
	private LocalDate published;
	private String permalink;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public LocalDate getPublished() {
		return published;
	}
	
	public void setPublished(String published) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		this.published = LocalDate.parse(published, formatter);
	}
	
	public String getPermalink() {
		return permalink;
	}
	
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public void execute(){
		Codecast codecast = new Codecast();
		codecast.setTitle(title);
		codecast.setPublished(published);
		codecast.setPermalink(permalink);
		Context.codecastGateway.saveCodecast(codecast);
	}
}
