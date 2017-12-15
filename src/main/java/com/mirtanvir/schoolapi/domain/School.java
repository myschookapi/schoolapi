package com.mirtanvir.schoolapi.domain;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "schools",
uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "zip"})})

public class School {
	
	private long id;
	private String school_name;
    private String state;
    private String city;
    private String zip;
    private float apiScore;
	private int version;
	
	
	private Set<DemographicAPI> demographicAPIdetails = new HashSet<DemographicAPI>();
	private Set<SchoolComment> myComments = new HashSet<SchoolComment>();
   
	
	
	private Set<Students> myStudents=new HashSet<Students>();
    
	
	//private Set<Users> myUsers=new HashSet<Users>();
	
	//private Users myUsers;
	
	
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long incomingId) {
		this.id = incomingId;
	}

	@Column(name = "name")
	public String getName() {
		return this.school_name;
	}

	public void setName(String name) {
		this.school_name = name;
	}

	
	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "city")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "zip",unique = true)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "apiScore")
	public float getApiScore() {
		return this.apiScore;
	}

	public void setApiScore(float apiScore) {
		this.apiScore = apiScore;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "school_id")
	public Set<DemographicAPI> getDemographicAPIdetails() {
		return this.demographicAPIdetails;
	}

	public void setDemographicAPIdetails(Set<DemographicAPI> incoming_apidetail) {
		this.demographicAPIdetails = incoming_apidetail;
	}

	public void addApidetail(DemographicAPI incoming_apidetail) {

		// incoming_apidetail.setMyschool(this);

		getDemographicAPIdetails().add(incoming_apidetail);
	}

	public void removeApidetail(DemographicAPI incoming_apidetail) {

		getDemographicAPIdetails().remove(incoming_apidetail);

		System.out.println("Removing this: " + incoming_apidetail
				+ "Demographich Api From the  HashSet<> : ");

	}

	// =====================For Comments==================================

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval =true)
	@JoinColumn(name = "school_id")
	public Set<SchoolComment> getMyComments() {

		return this.myComments;
	}

	public void setMyComments(Set<SchoolComment> incoming_comments) {
		this.myComments = incoming_comments;
	}

	public void addCommentset(SchoolComment incoming_comments) {

		// incoming_apidetail.setMyschool(this);

		getMyComments().add(incoming_comments);

	}

	public void removeCommentset(SchoolComment incoming_comments) {

		getMyComments().remove(incoming_comments);

	}

	
	
	// =====================For Students============================================================================
	@OneToMany(fetch = FetchType.EAGER, mappedBy="school",cascade = CascadeType.ALL, orphanRemoval = true)
	
	public Set<Students> getMyStudents() {

		return this.myStudents;
	}

	public void setMyStudents(Set<Students> incoming_students) {
		this.myStudents = incoming_students;
	}

	
	
	
	public void addStudentset(Students incoming_students) {

		
		
		Students a =(Students)incoming_students;
		
		if(this.checkDuplicateStudents(a))
		
	
		{
			
			System.out.println("The Student already exists in the school ");	
			
		}
		
		
		else{
			System.out.println("The student never exists in the database-->We are adding this as a  New student--->");
			
			getMyStudents().add(a);
			}
	
	
	}

	
	
	
		
	public boolean checkDuplicateStudents(Students incoming_student) {
		
		
		
		
		boolean result = false;
	   
		if (incoming_student == null ) {
	        result = false;
	    								} 
	   
		else {
	    	Students employee = (Students) incoming_student;
	       
	    	
	    	
	    	for(Students s:this.getMyStudents()){
	    	
	    		
	    		
	    		if (s.getFirstName().equalsIgnoreCase(employee.getFirstName()) && s.getLastName().equals(employee.getLastName())) 
	    	
	    	
	    	{
	            result = true;
	        }
	    }// end of for
	    
	}	// end of else
		
		
		
		return result;
	}// end of equals

	public void removeStudentset(Students incoming_students) {

		getMyStudents().remove(incoming_students);

	}

	//-----------------------For users----------------------
	/*
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)

	@JoinTable(name ="school_user_detail", 
	      
	joinColumns={@JoinColumn(name="school_id", referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="user_id", referencedColumnName="id")})

	
	public Users getMyUsers() {

		return this.myUsers;
	}

	public void setMyUsers(Users incoming_users) {
		this.myUsers = incoming_users;
	}

*/	


	@Version
	@Column(name = "version")
	public int getVersion() {
		return this.version;

	}

	public void setVersion(int incomingVersion) {
		this.version = incomingVersion;

	}

	@Override
	public String toString() {
		return "School [id=" + id + ", school_name=" + school_name + ", state="
				+ state + ", city=" + city + ", zip=" + zip + ", apiScore="
				+ apiScore + ", version=" + version
				+ ", demographicAPIdetails=" + demographicAPIdetails
				+ ", myStudents=" + myStudents + "]";
	}

	
	
	
}// End of School class















//=====================All Garbage code below ..delete later...======









/*
//------------------------------Delete later------------------------
@NamedQueries({
//@NamedQuery(name = "School.findById", query =
//"select distinct s from School s left join fetch s.demographicAPIdetails a  where s.id = :id"),

		//@NamedQuery(name = "School.findById", query = "select distinct s from School s left join fetch s.demographicAPIdetails a  where s.id = :id"),

//		 @NamedQuery(name = "School.getComments", query =
//		"select * from SchoolComment c where c."),

		 @NamedQuery(name = "School.findById", query = "select distinct s from School s left join fetch s.demographicAPIdetails a left join fetch s.myComments k  where s.id = :id"),

		@NamedQuery(name = "School.findAllWithDetail", query = "select distinct s from School s left join fetch s.demographicAPIdetails a") 
		
	}
)
//======================Delete later=======================================================================================================================
  
 
	// @Override
	// public String toString() {
	// return "School [id=" + id + ", school_name=" + school_name + ", state="
	// + state + ", city=" + city + ", zip=" + zip + ", apiScore="
	// + apiScore + ", demographicAPIdetails!!!=" + demographicAPIdetails
	// + ", school version=" + version +",Comments="+myComments+ "]";
	// }
	//

	// @NamedQuery(name = "School.findAllWithDetail", query =
	// "select distinct s from School s left join fetch s.demographicAPIdetails a")})

 
 */
/*  ==============Delete later============
@Override
public String toString() {
	return "School [id=" + id + ", school_name=" + school_name + ", state="
			+ state + ", city=" + city + ", zip=" + zip + ", apiScore="
			+ apiScore + ", version=" + version
			+ ", demographicAPIdetails=" + demographicAPIdetails
			+ ", myComments=" + myComments + ", myStudents=" + myStudents
			+ ", myUsers=" + myUsers + "]";
}

*/