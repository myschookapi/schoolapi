package com.mirtanvir.schoolapi.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="students")
public class Students {

private long id;
private String FirstName;
private String LastName;
private String Phone;
private String Email;
private int version;
private School school;

private Set<Users> myStudentUsers=new HashSet<Users>();
private Set<Subjects> mySubjects=new HashSet<Subjects>();

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id")
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}

@Column(name="First_Name")
public String getFirstName() {
	return FirstName;
}
public void setFirstName(String firstName) {
	FirstName = firstName;
}
@Column(name="Last_Name")
public String getLastName() {
	return LastName;
}
public void setLastName(String lastName) {
	LastName = lastName;
}

@Column(name="Phone")
public String getPhone() {
	return Phone;
}
public void setPhone(String phone) {
	Phone = phone;
}


@Column(name="Email")
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}

@Version
@Column(name = "version")
public int getVersion() {
	return this.version;

}

public void setVersion(int incomingVersion) {
	this.version = incomingVersion;

}
//Note:optional = false means No student can be saved without any school reference. School Refeence is mandatory.
@ManyToOne(fetch = FetchType.EAGER,optional = false)
@JoinColumn(name = "school_id", nullable =true)
public School getSchool() {
	return school;
}
public void setSchool(School school) {
	this.school = school;
}




/// =============for joining table student_user_detail  many to many relationship




@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
@JoinTable(name = "student_user_detail",       
joinColumns={@JoinColumn(name="student_id", referencedColumnName="id")},
inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})



public Set<Users> getMyStudentUsers() {

	return this.myStudentUsers;
}



public void setMyStudentUsers(Set<Users> incoming_users) {
	this.myStudentUsers = incoming_users;
}




public void addUsers(Users incoming_user) {

	// incoming_apidetail.setMyschool(this);

	getMyStudentUsers().add(incoming_user);

}

public void removeUsers(Users incoming_user) {

	getMyStudentUsers().remove(incoming_user);

}






//================for subjects================================================================



@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

@JoinTable(name ="student_subject_detail", 
joinColumns={@JoinColumn(name="student_id", referencedColumnName="id")},
inverseJoinColumns={@JoinColumn(name="subject_id", referencedColumnName="id")})


public Set<Subjects> getMySubjects() {

	return this.mySubjects;
}


public void setMySubjects(Set<Subjects> incoming_subjects) {
	this.mySubjects = incoming_subjects;
}



public void addSubjects(Subjects incoming_subjects) {

	// incoming_apidetail.setMyschool(this);

	getMySubjects().add(incoming_subjects);

}

public void removeSubjects(Subjects incoming_subjects) {

	getMySubjects().remove(incoming_subjects);

}





@Override
public String toString() {
	return "Students [id=" + id + ", FirstName=" + FirstName + ", LastName="
			+ LastName + ", Phone=" + Phone + ", Email=" + Email + ", version="
			+ version + ", school=" + school + ", myStudentUsers="
			+ myStudentUsers + ", mySubjects=" + mySubjects + "]";
}






}// end of class
