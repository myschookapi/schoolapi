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
	@Table(name="school_comment")
	public class SchoolComment {
		
		
		@Override
		public String toString() {
			return "School Comment [id=" + id
					+ ", Recent_comments: "+comment+",comment_version:"+version+ "]";
		}

		
		
		
		
		private long id;
		private String comment;
		private int version;
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="comm_id")
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		
		@Column(name="comment")	
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		

		
		@Version
		@Column(name = "version")
		public int getVersion() {
			return this.version;

		}

		public void setVersion(int incomingVersion) {
			this.version = incomingVersion;

		}
		
		
		
		
		
		
		
		






}
