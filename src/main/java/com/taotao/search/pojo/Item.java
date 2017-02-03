package com.taotao.search.pojo;

public class Item {
	private String id;
	private String title;
	private String sell_point;
	private Long price;
	private String image;
	private String category_name;
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the sell_point
     */
    public String getSell_point() {
        return sell_point;
    }
    /**
     * @param sell_point the sell_point to set
     */
    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }
    /**
     * @return the price
     */
    public Long getPrice() {
        return price;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(Long price) {
        this.price = price;
    }
    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }
    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
    /**
     * @return the category_name
     */
    public String getCategory_name() {
        return category_name;
    }
    /**
     * @param category_name the category_name to set
     */
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    /**
     * @param id
     * @param title
     * @param sell_point
     * @param price
     * @param image
     * @param category_name
     */
    public Item(String id, String title, String sell_point, Long price, String image, String category_name) {
        super();
        this.id = id;
        this.title = title;
        this.sell_point = sell_point;
        this.price = price;
        this.image = image;
        this.category_name = category_name;
    }
    /**
     * 
     */
    public Item() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
}
