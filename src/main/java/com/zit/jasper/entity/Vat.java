package com.zit.jasper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Vat {
	@Id
	private String slNo;
	@Column(name = "product_service_name")
	private String productServiceName;
	private String provideUnit;
	private double quantity;
	private double unitPrice;
	private double totalPrice;
	private double somsPersent;


}
