package vttp2023.batch3.csf.assessment.cnserver.models;

import java.util.Date;
import java.util.List;

/* Do not modify this file */

public class News {

	private String id;
	private long postDate;

	private String title;
	private String description;
	private String image;
	private List<String> tags;

	public News(String string, Long long1, String string2, String string3, String string4, List<String> list) {
	}
	public News() {
	}
	public void setId(String id) { this.id = id; }
	public String getId() { return this.id; }

	
	public Date getPostDateAsDate() { return new Date(this.postDate); }

	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }

	public void setDescription(String description) { this.description = description; }
	public String getDescription() { return this.description; }

	public void setImage(String image) { this.image = image; }
	public String getImage() { return this.image; }

	public void setTags(List<String> tags) { this.tags = tags; }
	public List<String> getTags() { return this.tags; }

	public long getPostDate() {
		return postDate;
	}
	public void setPostDate(long postDate) {
		this.postDate = postDate;
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", postDate2=" + postDate +  ", title=" + title
				+ ", description=" + description + ", image=" + image + ", tags=" + tags + "]";
	}
	
	
	

	// @Override
	// public String toString() {
	// 	return "News{id: %s, postDate: %d, title: %s}".formatted(id, getPostDateAsDate(), title);
	// }

	/* Do not modify this file */
}
