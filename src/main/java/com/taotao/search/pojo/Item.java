package com.taotao.search.pojo;

public class Item {
	private String item_id;
	private String item_title;
	private String item_sell_point;
	private Long item_price;
	private String item_image;
	private String item_category_name;

	/**
	 * @return the item_id
	 */
	public String getItem_id() {
		return item_id;
	}

	/**
	 * @param item_id
	 *            the item_id to set
	 */
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	/**
	 * @return the item_title
	 */
	public String getItem_title() {
		return item_title;
	}

	/**
	 * @param item_title
	 *            the item_title to set
	 */
	public void setItem_title(String item_title) {
		this.item_title = item_title;
	}

	/**
	 * @return the item_sell_point
	 */
	public String getItem_sell_point() {
		return item_sell_point;
	}

	/**
	 * @param item_sell_point
	 *            the item_sell_point to set
	 */
	public void setItem_sell_point(String item_sell_point) {
		this.item_sell_point = item_sell_point;
	}

	/**
	 * @return the item_price
	 */
	public Long getItem_price() {
		return item_price;
	}

	/**
	 * @param item_price
	 *            the item_price to set
	 */
	public void setItem_price(Long item_price) {
		this.item_price = item_price;
	}

	/**
	 * @return the item_image
	 */
	public String getItem_image() {
		return item_image;
	}

	/**
	 * @param item_image
	 *            the item_image to set
	 */
	public void setItem_image(String item_image) {
		this.item_image = item_image;
	}

	/**
	 * @return the item_category_name
	 */
	public String getItem_category_name() {
		return item_category_name;
	}

	/**
	 * @param item_category_name
	 *            the item_category_name to set
	 */
	public void setItem_category_name(String item_category_name) {
		this.item_category_name = item_category_name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", item_title=" + item_title + ", item_sell_point=" + item_sell_point
				+ ", item_price=" + item_price + ", item_image=" + item_image + ", item_category_name="
				+ item_category_name + "]";
	}

	/**
	 * @param item_id
	 * @param item_title
	 * @param item_sell_point
	 * @param item_price
	 * @param item_image
	 * @param item_category_name
	 */
	public Item(String item_id, String item_title, String item_sell_point, Long item_price, String item_image,
			String item_category_name) {
		super();
		this.item_id = item_id;
		this.item_title = item_title;
		this.item_sell_point = item_sell_point;
		this.item_price = item_price;
		this.item_image = item_image;
		this.item_category_name = item_category_name;
	}

	/**
	 * 
	 */
	public Item() {
		super();
	}

}
