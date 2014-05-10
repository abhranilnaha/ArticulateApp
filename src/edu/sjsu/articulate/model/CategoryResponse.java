package edu.sjsu.articulate.model;

import java.io.Serializable;
import java.util.List;

public class CategoryResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 330184349555240118L;
	
	private String name;
	private String icon;
	private String link;
	private List<Item> items;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	

}
