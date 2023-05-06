package com.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Category")
public class CategoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	private String cname;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<SubCategoryEntity> subCategory;
    
	
	@ManyToOne
	@JoinColumn(name = "userid",referencedColumnName = "id")
	UserEntity usercat;
	
	
	
	public UserEntity getUsercat() {
		return usercat;
	}

	public void setUsercat(UserEntity usercat) {
		this.usercat = usercat;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<SubCategoryEntity> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<SubCategoryEntity> subCategory) {
		this.subCategory = subCategory;
	}


	
	

}
