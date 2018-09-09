package com.skat.smev.fns.domain;

public class AttachmentModel {

	private String type;
	private String name;
	private String content;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "{" +
				"type='" + type + '\'' +
				", name='" + name + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}