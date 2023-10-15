package com.zit.jasper.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Company {
	@Id
	private String companyId;
	private String companyName;
	private String bin;
	private String issuingAddress;

}
