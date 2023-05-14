//package com.Entity;
//
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//
//public class ExpenseEntity {
//
//	private Integer expenseId;
//	private String title;
//	
//	
//	@OneToOne
//	@JoinColumn(name = "userid",referencedColumnName = "id") //fk
//	UserEntity user;
//	
//	@OneToOne
//	@JoinColumn(name = "categoryid",referencedColumnName = "categoryId") //fk
//	CategoryEntity category;
//	
//	@OneToOne
//	@JoinColumn(name = "subcategoryid",referencedColumnName = "subcategoryId") //fk
//	SubCategoryEntity subcategory;
//	
//	@OneToOne
//	@JoinColumn(name = "vendorid",referencedColumnName = "vendorId") //fk
//	VendorEntity vendor;
//	
//	@OneToOne
//	@JoinColumn(name = "vendorid",referencedColumnName = "vendorId") //fk
//	VendorEntity vendor;
//	
//	accountTypeId integer references accountType(accountTypeId),
//	
//	statusId integer references status(statusId),
//	
//	private Integer amount;
//	private String date;
//	private String description;
//	
//}
