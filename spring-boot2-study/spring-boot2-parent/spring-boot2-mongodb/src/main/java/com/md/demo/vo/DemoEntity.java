package com.md.demo.vo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "demo_collection")
public class DemoEntity implements Serializable {

	private static final long serialVersionUID = -2793367984250634455L;
	
	private String _id;

	@Id
	private Long id;

	private String title;

	private String description;

	private String by;

	private String url;
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "DemoEntity [id=" + id + ", title=" + title + ", description=" + description + ", by=" + by + ", url="
				+ url + "]";
	}
}