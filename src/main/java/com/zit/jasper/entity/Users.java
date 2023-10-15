package com.zit.jasper.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {
	@Id
	private String userId;
	private String userName;
	private String bin;
	private String address;
	private String provideArea;

}
