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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="roles")


public class Roles {

	
	
	
	private long id;
	private String ActionList;
	private String GroupId;
	private String ModuleId;
	private String ComponentId;
	private String ViewId;
	private String RoleType;
	private int version;
	
	
	private Set<Users> myUsers=new HashSet<Users>();

	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	@Column(name="actionlist")
	public String getActionList() {
		return ActionList;
	}


	public void setActionList(String actionList) {
		ActionList = actionList;
	}

	@Column(name="groupid")
	public String getGroupId() {
		return GroupId;
	}


	public void setGroupId(String groupId) {
		GroupId = groupId;
	}

	@Column(name="moduleid")
	public String getModuleId() {
		return ModuleId;
	}


	public void setModuleId(String moduleId) {
		ModuleId = moduleId;
	}

	@Column(name="componentid")
	public String getComponentId() {
		return ComponentId;
	}


	public void setComponentId(String componentId) {
		ComponentId = componentId;
	}

	@Column(name="viewid")
	public String getViewId() {
		return ViewId;
	}


	public void setViewId(String viewId) {
		ViewId = viewId;
	}

	@Column(name="roletype")
	public String getRoleType() {
		return RoleType;
	}


	public void setRoleType(String roleType) {
		RoleType = roleType;
	}

	@Column(name="version")
	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="role",orphanRemoval = true)
	
	
	
	public Set<Users> getMyUsers() {
		return myUsers;
	}


	public void setMyUsers(Set<Users> myUsers) {
		this.myUsers = myUsers;
	}
	
	
	public void addUsers(Users incoming_user) {

		// incoming_apidetail.setMyschool(this);

		getMyUsers().add(incoming_user);

	}

	public void removeStudentset(Students incoming_students) {

		getMyUsers().remove(incoming_students);

	}


	@Override
	public String toString() {
		return "Roles [id=" + id + ", ActionList=" + ActionList + ", GroupId="
				+ GroupId + ", ModuleId=" + ModuleId + ", ComponentId="
				+ ComponentId + ", ViewId=" + ViewId + ", RoleType=" + RoleType
				+ ", version=" + version + ", myUsers=" + myUsers + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}// end of roles
