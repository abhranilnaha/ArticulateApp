package edu.sjsu.articulate.model;

import java.io.Serializable;
import java.util.List;

public class Item implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7797978799039303129L;
	
	private String title;
	private String icon;
	private List<CategoryResponse> items;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<CategoryResponse> getItems() {
		return items;
	}
	public void setItems(List<CategoryResponse> items) {
		this.items = items;
	}
	
	
}
