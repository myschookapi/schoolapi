package com.mirtanvir.schoolapi.domain;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="apidetails")


public class  SchoolApidetail {

	private float asianApi;
	private float hispanicApi;
	private float whiteApi;
	private float otherApi;
	private int version;
	//private float schoolld;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	public long getId() {
		return id;
	}

	public void setId(long incomingId) {
		this.id = incomingId;
	}

	
	
	public SchoolApidetail() {
		
	}
	
	public SchoolApidetail(float incomingasianApi,float incominghispanicApi,float incomingwhiteApi,float incomingotherApi) {
		
		this.asianApi=incomingasianApi;
		this.hispanicApi=incominghispanicApi;
		this.whiteApi=incomingwhiteApi;
		this.otherApi=incomingotherApi;
		
		
	}
	
	
	public float getAsianapi() {
		return asianApi;
	}
	
	public void setAsianapi(float incomingasianApi) {
		this.asianApi = incomingasianApi;
	}
	
	
	public float gethispanicApi() {
		return hispanicApi;
	}
	
	public void sethispanicApi(float incominghispanicApi) {
		this.hispanicApi = incominghispanicApi;
	}
	



	
	public float getwhiteApi() {
		return this.whiteApi;
	}
	
	public void setwhiteApi(float incomingwhiteApi) {
		this.whiteApi = incomingwhiteApi;
	}
	

	public float getotherApi() {
		return this.otherApi;
	}
	
	public void setotherApi(float incomingotherApi) {
		this.otherApi = incomingotherApi;
	}

	
	
	
	@OneToOne
	@JoinColumn(name="schoolld")
	private School myschool;
	
	
	
	public School getSchool() {
		return this.myschool;
	}

	public void setSchool(School incomingSchool) {
		this.myschool=incomingSchool;	
				
	}


	@Version
	@Column(name="version")
	
	public int getVersion(){
		return this.version;
		
		
	}
	
	public void setVersion(int incomingVersion){
		 this.version=incomingVersion;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}// end of class
