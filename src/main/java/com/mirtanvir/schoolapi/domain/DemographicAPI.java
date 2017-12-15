package com.mirtanvir.schoolapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="demographics_api")
public class DemographicAPI {

	public DemographicAPI(long id, String demographicType, double apiScore,
			int version) {
		super();
		this.id = id;
		this.demographicType = demographicType;
		this.apiScore = apiScore;
		this.version = version;
	}

	public DemographicAPI(){};


	private long id;
	private String demographicType;
	private double apiScore;
	private int version;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name="demographic_type")
	public String getDemographicType() {
		return demographicType;
	}
	public void setDemographicType(String demographicType) {
		this.demographicType = demographicType;
	}
	
	@Column(name="api_score")
	public double getApiScore() {
		return apiScore;
	}
	public void setApiScore(double apiScore) {
		this.apiScore = apiScore;
	}
	
	
	
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
		return "DemographicAPI [ID=" + id
				 + ", demographicTypeK="
				+ demographicType + ", apiScore=" + apiScore +",version="+version+  "]";
	}
	
	
}