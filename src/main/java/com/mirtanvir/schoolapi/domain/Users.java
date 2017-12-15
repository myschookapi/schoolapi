package com.mirtanvir.schoolapi.domain;



//import javax.management.relation.Role;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import antlr.collections.List;

	import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="users")

public class Users {

private long id;
		private String Name;
		private String Address;
		private String Mobile;
		private String HomePhone;
		private String Relationship;
		private String Email;
		private int version;
		private Roles role;
		
		private Set<School> mySchools;
		private Set<Students> students;
				

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id")
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}

@Column(name="Name")
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}

@Column(name="Address")
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}

@Column(name="Mobile")
public String getMobile() {
	return Mobile;
}
public void setMobile(String mobile) {
	Mobile = mobile;
}

@Column(name="Home_phone")
public String getHomePhone() {
	return HomePhone;
}
public void setHomePhone(String homePhone) {
	HomePhone = homePhone;
}
@Column(name="Relationship")
public String getRelationship() {
	return Relationship;
}
public void setRelationship(String relationship) {
	Relationship = relationship;
}

@Column(name="Email")
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}

@Column(name="Version")
public int getVersion() {
	return version;
}

public void setVersion(int version) {
	this.version = version;
}

		
//==========================for schools========================

//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//@JoinTable(name = "school_user_detail", 
    //  joinColumns = @JoinColumn(name = "user_id", referencedColumnName="id"), 
    //  inverseJoinColumns = @JoinColumn(name = "school_id", referencedColumnName="id"))



//@ManyToMany(fetch = FetchType.LAZY, mappedBy = "myUsers")

@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
@JoinTable(name = "school_user_detail", 
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName="id"), 
   inverseJoinColumns = @JoinColumn(name = "school_id", referencedColumnName="id"))


public Set<School> getMySchools() {
	return (Set<School>) mySchools;
}
public void setMySchools(Set <School> mySchools) {
	this.mySchools = mySchools;
}


public void addSchool(School incoming_school) {

	getMySchools().add(incoming_school);

}




//=============for roles========================

@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
@JoinColumn(name = "Roleid", nullable =true)
public Roles getRole() {
	return role;
}
public void setRole(Roles role) {
	this.role = role;
}







//================================For Students=================


@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
@JoinTable(name = "student_user_detail", joinColumns = { 
		@JoinColumn(name = "user_id", nullable = false, updatable = false)}, 
		inverseJoinColumns = { @JoinColumn(name = "student_id", 
		nullable = false, updatable = false) } )




public Set<Students> getStudents() {
	return students;
}

public void setStudents(Set<Students> students) {
	this.students = students;
}





public void addStudents(Students incoming_student) {

	// incoming_apidetail.setMyschool(this);

	getStudents().add(incoming_student);

}

public void removeStudent(Subjects incoming_student) {

	getStudents().remove(incoming_student);

}



	




@Override
public String toString() {
	return "Users [id=" + id + ", Name=" + Name + ", Address=" + Address
			+ ", Mobile=" + Mobile + ", HomePhone=" + HomePhone
			+ ", Relationship=" + Relationship + ", Email=" + Email
			+ ", version=" + version + ", role=" + role + ", mySchools="
			+ mySchools + ", students=" + students + "]";
}


}// end of class
