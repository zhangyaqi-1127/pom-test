package com.week3.pojo;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class WtGoods implements Serializable{
	@Field
    private Integer id;

	@Field("item_name")
    private String name;
	@Field("item_price")
    private String price;
	@Field("item_brandname")
    private String brandName;
	@Field("item_num")
    private Integer num;
	@Field("item_sjdate")
    private String sjDate;
	@Field("item_status")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSjDate() {
        return sjDate;
    }

    public void setSjDate(String sjDate) {
        this.sjDate = sjDate == null ? null : sjDate.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	@Override
	public String toString() {
		return "WtGoods [id=" + id + ", name=" + name + ", price=" + price + ", brandName=" + brandName + ", num=" + num
				+ ", sjDate=" + sjDate + ", status=" + status + "]";
	}
    
}