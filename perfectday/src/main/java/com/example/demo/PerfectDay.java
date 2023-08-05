package com.example.demo;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PerfectDay {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String dob;
	private String qual;
	private String height;
	private String caste;
	private String phoneno;
	@Column(length=1000000)
	private byte[] image;
	
	
	public PerfectDay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PerfectDay(int id, String name, String dob, String qual, String height, String caste,String phoneno,
			byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.qual = qual;
		this.height = height;
		this.caste = caste;
		this.phoneno = phoneno;
		this.image = image;
	}
	public PerfectDay( String name, String dob, String qual, String height, String caste,String phoneno,
			byte[] image) {
		super();
		this.name = name;
		this.dob = dob;
		this.qual = qual;
		this.height = height;
		this.caste = caste;
		this.phoneno = phoneno;
		this.image = image;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob= dob;
	}
	public String getQual() {
		return qual;
	}
	public void setQual(String qual) {
		this.qual= qual;
	}
	public String getHeight() {
		return height ;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
	
	
}

