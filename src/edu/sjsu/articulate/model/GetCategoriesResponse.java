package edu.sjsu.articulate.model;

import java.io.Serializable;
import java.util.List;

public class GetCategoriesResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9152136461482534395L;
	
	private String name;
	private String link;
	private String icon;
	private List<GetCategoriesResponse> items;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<GetCategoriesResponse> getItems() {
		return items;
	}
	public void setItems(List<GetCategoriesResponse> items) {
		this.items = items;
	}
	
}
