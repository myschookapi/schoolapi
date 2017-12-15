package com.mirtanvir.schoolapi.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="teachers")
public class Teachers {

	private long id;
	private String FirstName;
	private String LastName;
	private String Phone;
	private String Email;
	private int version;

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
	@Column(name="Version")
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	
	// =====================For Subjects==================================
		@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="teacher",orphanRemoval = true)
		//@JoinColumn(name = "school_id")//commented  by kabir
		//@JoinColumn(name = "school_id")// added by kabir
		public Set<Subjects> getmySubjects() {
			return this.mySubjects;
		}

	public void setMySubjects(Set<Subjects>incoming_mySubjects) {
		this.mySubjects = incoming_mySubjects;
	}
	
	

	public void addSubjects(Subjects incoming_subjects) {

		// incoming_apidetail.setMyschool(this);

		getmySubjects().add(incoming_subjects);
	}

	public void removeSubjects(Subjects incoming_subjects) {

		getmySubjects().remove(incoming_subjects);
	
	}
	
	

	
	
	
	
	@Override
	public String toString() {
		return "Teachers [id=" + id + ", FirstName=" + FirstName
				+ ", LastName=" + LastName + ", Phone=" + Phone + ", Email="
				+ Email + ", version=" + version + 
				 "]";
	}
	









}// end of Teachers class