package com.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Subcategory")
public class SubCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subcategoryId;
	private String subcname;
	
	
	
	@ManyToOne
	@JoinColumn(name = "categoryid",referencedColumnName = "categoryId")
	CategoryEntity category;
	
	public Integer getSubcategoryId() {
		return subcategoryId;
	}
	public void setSubcategoryId(Integer subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	
	public CategoryEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	public String getSubcname() {
		return subcname;
	}
	public void setSubcname(String subcname) {
		this.subcname = subcname;
	}
	public SubCategoryEntity(Integer subcategoryId, String subcname, CategoryEntity category) {
		super();
		this.subcategoryId = subcategoryId;
		this.subcname = subcname;
		this.category = category;
	}
	
	
	
	
}
