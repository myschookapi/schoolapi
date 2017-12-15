package com.mirtanvir.schoolapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="books")
public class Books {

private long id;
private String title;
private String author;
private long isbn;
private String edition;
private int version;



@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id")

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
@Column(name="title")
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}

@Column(name="author")
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}

@Column(name="isbn")
public long getIsbn() {
	return isbn;
}
public void setIsbn(long isbn) {
	this.isbn = isbn;
}
@Column(name="edition")
public String getEdition() {
	return edition;
}

public void setEdition(String edition) {
	this.edition = edition;
}
public int getVersion() {
	return version;
}
public void setVersion(int version) {
	this.version = version;
}





@Override
public String toString() {
	return "Books [id=" + id + ", title=" + title + ", author=" + author
			+ ", isbn=" + isbn + ", edition=" + edition + ", version="
			+ version + "]";
}







}// end of class
