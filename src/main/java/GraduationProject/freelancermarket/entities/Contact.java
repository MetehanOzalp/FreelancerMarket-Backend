package GraduationProject.freelancermarket.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "contacts")
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull
	@NotBlank(message = "İsim alanı boş bırakılamaz")
	@Column(name = "name")
	private String name;

	@NotNull
	@NotBlank(message = "Soyisim alanı boş bırakılamaz")
	@Column(name = "surname")
	private String surName;

	@NotNull
	@NotBlank(message = "Mail alanı boş bırakılamaz")
	@Column(name = "mail")
	private String mail;

	@NotNull
	@NotBlank(message = "Konu alanı boş bırakılamaz")
	@Column(name = "subject")
	private String subject;

	@NotNull
	@NotBlank(message = "İçerik alanı boş bırakılamaz")
	@Column(name = "content")
	private String content;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "status")
	private boolean status;

}
