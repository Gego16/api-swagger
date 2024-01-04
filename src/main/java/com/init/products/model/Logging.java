package com.init.products.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name="logging")
public class Logging {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="usuario",nullable = false)
	@NotBlank
	private String nomUsuario;
	
	@Column(name="ap_paterno",nullable = false)
	@NotBlank
	private String apPaterno;
	
	@Column(name="apMaterno",nullable = false)
	@NotBlank
	private String apMaterno;

}