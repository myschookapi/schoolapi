package com.mirtanvir.schoolapi.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mirtanvir.schoolapi.domain.School;
import com.mirtanvir.schoolapi.domain.Students;
import com.mirtanvir.schoolapi.domain.Subjects;
import com.mirtanvir.schoolapi.domain.Teachers;
import com.mirtanvir.schoolapi.domain.Users;

@Repository
// @Service("SchoolRepository")
// @Repository("SchoolRepository")
@Transactional
public class SchoolRepositoryImpl implements SchoolRepository {

	private static final String Students = null;

	private final Log log = LogFactory.getLog(SchoolRepositoryImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<School> findAllSchoolWithDetail() {
		return sessionFactory.getCurrentSession()
				.getNamedQuery("School.findAllWithDetail").list();

		
	}

	@Override
	public School findSchoolById(Long Myid) {

		return (School) sessionFactory.getCurrentSession()
				.getNamedQuery("School.findById").setParameter("id", Myid)
				.uniqueResult();

		
	}

	@Override
	public School getSchoolWithCommentsAndDemographicsDetails(Long schoolId) {
		Session currentSession = null;
		currentSession = sessionFactory.getCurrentSession();
		School school = (School) currentSession.get(School.class, schoolId);
		Hibernate.initialize(school.getMyComments());
		Hibernate.initialize(school.getDemographicAPIdetails());
		Hibernate.initialize(school.getMyStudents());
		// Hibernate.initialize(school.getMyUsers());
		return school;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public School getSchoolWithComments(Long schoolId) {
		// public List<School> getSchoolWithComments(Long schoolId) {
		Session currentSession = null;
		School school1 = new School();
		currentSession = sessionFactory.openSession();
		System.out.println("Entering the try block");
		
		 school1=(School) currentSession
		 .createQuery("select distinct s from School s left join fetch s.myComments k left join fetch s.demographicAPIdetails d left join fetch s.myStudents m where s.id = :id")
		 .setParameter("id", schoolId).uniqueResult();

		
		 return school1;

	}

	// =====================Added by kabir for subjects======================

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Subjects getSubjetWithStudents(Long subid) {
		// public List<School> getSchoolWithComments(Long schoolId) {
		Session currentSession = null;
		currentSession = sessionFactory.getCurrentSession();

		
		return (Subjects) currentSession
				.createQuery(
						"select distinct s from Subjects s  join fetch s.mySubjStudent k where s.id = :id")
				.setParameter("id", subid).uniqueResult();

	}

	// =======================login====@@@@@@@@@@@@@@@=========// function=================================

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Users login(long userid) {
		
		
		//List<Users> result = (List<Users>) new ArrayList<Users>();
		
		
		Users myUser;
		
		Users allUsers=null;
		
		Session currentSession = null;
		currentSession = sessionFactory.openSession();

		myUser = (Users) currentSession
				.createQuery("select distinct u from Users u left join fetch u.mySchools m where u.id = :id")
				.setParameter("id", userid).uniqueResult();

if (myUser != null) {
			String roletype;
			
			 roletype=myUser.getRole().getRoleType();
				
			
			if (roletype.equals("adm")||roletype.equals("par"))
			{			
				allUsers=myUser;	
				
			}// end of if (roletype.equals("adm"))
			
}
			
			
		else
		{
			System.out
					.println("No User found in this user id provided by user");

		}

			currentSession.close();

		
	return allUsers;
		
	}// end of login function
	
	
	
	// =============Save User Function=====================

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Users saveUser(Users user) {

		System.out.println("Begin to save the user:--->  ");

		Session session = sessionFactory.getCurrentSession();
		org.hibernate.Transaction trans = session.beginTransaction();

		session.save(user);
		trans.commit();

		return user;
	}

		
	//=============Save Student() Function=====================
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Students saveStudent(Students  st) {
	
		
		System.out.println("Begin to save the Studnts:--->  ");

		Session session = sessionFactory.getCurrentSession();
		org.hibernate.Transaction trans = session.beginTransaction();

		session.save(st);
		trans.commit();
	
		return st;
	
	}//end of saveStudent
	
	// =================Function for save school==========
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public School saveSchool(School school) {

		
		//validateSchool(school);
		 
		 Session session = sessionFactory.openSession();
		org.hibernate.Transaction trans = session.beginTransaction();
		
		 

		session.saveOrUpdate(school);
			trans.commit();
		 session.close();
		return school;
	}

	/*private void validateSchool(School school) {
		
		
		if(school != null){
			if(school.getMyStudents() == null || school.getMyStudents().size() == 0){
				throw new RuntimeException(" My students should not be null. Please provide student data along with school");
			}
		}
		
	}*/


	@Override
	public void deleteSchool(School school) {
		sessionFactory.getCurrentSession().delete(school);
		log.info("Contact deleted with id: " + school.getId());
	}

	@Override
	public List<School> findAllSchool() {

		Session currentSession = null;
		currentSession = sessionFactory.openSession();

		// School school = (School) currentSession.get(School.class, schoolId);
		// Hibernate.initialize(school.getMyComments());
		try {
			return currentSession
					.createQuery("select distinct s from School s").list();
		} finally {
			if (currentSession != null) {
				currentSession.close();
			}
		}
		// return
		// currentSession.createQuery("select student_id,First_Name from student st Where st.student_id=("+"select sud.student_id from  student_user_detail sud where sud.user_id = :id"+")"+")";

		// return school;
	}
//=============Find student by id  function===========
	
	
	
	@Override
	public Students getStudentById(Long studentid) {
		Session currentSession = null;
		Students currStudent = new Students();
		currentSession = sessionFactory.openSession();
		  currStudent=(Students) currentSession
		 .createQuery("select distinct st from Students st left join fetch st.mySubjects k left join fetch st.myStudentUsers u  where st.id = :id")
		 .setParameter("id", studentid).uniqueResult();	
		 return currStudent;
			
	}

	
	
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Subjects>   getSubjectByStudentId(Long studentid) {
		Session currentSession = null;
		currentSession = sessionFactory.openSession();
		//Subjects currSubject = new Subjects();
		//currentSession = sessionFactory.openSession();
		//currSubject=
				
		return (List<Subjects>) currentSession.createQuery("select distinct s from Subjects s left join fetch s.mySubjStudent k where k.id = :id")
				 .setParameter("id", studentid).list();
			
	
		  
		 
			
	}

	
	
	
	
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	// public List<Students> findStudentWithUserid(Long MyUserid) {
	public Students findStudentWithUserid(Long MyUserid) {
		// public Students getSchoolWithoutUsers(Long MyUserid) {

		Session currentSession = null;
		currentSession = sessionFactory.getCurrentSession();

		return (Students) currentSession
				.createQuery(
						"select distinct s from Students s left join fetch s.myUsers m left join fetch s.myComments k left join fetch s.mySubjects j  where m.id = :id")
				.setParameter("id", MyUserid).list();

	}

	// ============For Teacher finding the subjects and students====

	public List<Subjects> getSubjectsForTeacher(Long Teacherid) {
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Teachers getTeacherWithSubjects(Long Teacherid) {
		Session currentSession = null;
		Teachers teacher1 = new Teachers();
		currentSession = sessionFactory.openSession();

		teacher1 = (Teachers) currentSession
				.createQuery(
						"select distinct t from Teachers t  join fetch t.mySubjects k  where t.id = :id")
				.setParameter("id", Teacherid).uniqueResult();

		return teacher1;

	}

}// end of School Repisotory






































//=======================================Garbage Code=====================


/*
long schoolid;



roletype=myUser.getRole().getRoleType();
System.out.println("The roletype from roleTable:"+roletype+"--->");

if (roletype.equals("adm")){			
Set<School> schools = myUser.getMySchools();
for (School mys : schools) {
	schoolid = mys.getId();

	// Note: if there is a object reference with many to one mapping
	// then you have to call the object and then get the id of the
	// object[Ex:st.school.id]
	List<Students> mystudent = currentSession
			.createQuery(
					"from Students st where st.school.id=:idtoreplace")
			.setParameter("idtoreplace", schoolid).list();

	result = mystudent;

}// end of for (School mys : schools)

}// end of roletype == "adm"

else if (roletype.equals("par")){
	//Set<School> schools = myUser.getMySchools();
//	for (School mys : schools) {
		//schoolid = mys.getId();
	List<Students> mystudent = currentSession
			.createQuery(
					"select s from Students s right join fetch s.myStudentUsers m where m.id = :idtoreplace")
			.setParameter("idtoreplace", userid).list();

	result = mystudent;

	
	//}
	



//}// end of roletype == "par"


*/	
// end of myUser != null




//org.hibernate.Transaction tx=
		// sessionFactory.getCurrentSession().beginTransaction();
		// currentSession = sessionFactory.getCurrentSession();
		// currentSession = sessionFactory.getCurrentSession();
		// currentSession = sessionFactory.openSession();
		


//
// @Resource(name = "sessionFactory")
// public void setSessionFactory(SessionFactory sessionFactory) {
// this.sessionFactory = sessionFactory;
// }

/*
 * @SuppressWarnings("unchecked")
 * 
 * @Transactional(readOnly = true) public List<School> findAllSchool() {
 * return sessionFactory.getCurrentSession().createQuery("from School s ")
 * .list(); }
 */

//return
		// sessionFactory.getCurrentSession().createQuery(" from School s DemographicAPI d SchoolComment c where s.id=d.sc  ").list();



//return (School)
		// return (School)
		// sessionFactory.getCurrentSession().createQuery("select  s from School s  join s.myComments a where s.id = :id").setParameter("id",
		// Myid).uniqueResult();



//currentSession.beginTransaction();
		// currentSession.getTransaction().begin();
		// School school = (School) currentSession.get(School.class, schoolId);
		// Hibernate.initialize(school.getMyComments());
		// return school;
		// return (School) currentSession

		// return School school= (School) currentSession

		// return (School) currentSession .createQuery(
		// "select distinct s from School s left join fetch s.myComments k left join fetch s.demographicAPIdetails d left join fetch s.myStudents z left join fetch z.myStudentUsers v left join fetch left join fetch s.myUsers u    where s.id = :id")
		// .setParameter("id", schoolId).uniqueResult();

		// return (School) currentSession .createQuery(
		// "select distinct s from School s  join fetch s.myComments k  join fetch s.demographicAPIdetails d  join fetch s.myStudents z join fetch s.myUsers u    where s.id = :id")
		// .setParameter("id", schoolId).uniqueResult();

		// return (School) currentSession
		// .createQuery("select distinct s from School s  join fetch s.myComments k  join fetch s.demographicAPIdetails d left join fetch s.myStudents z join fetch z.mySubjects b  join fetch z.myStudentUsers y join fetch s.myUsers u    where s.id = :id")
		// .setParameter("id", schoolId).uniqueResult();

		// currentSession.getTransaction().commit();

		// currentSession

		// return (School) currentSession
		// .createQuery("select distinct s from School s  join fetch s.myComments k  join fetch s.demographicAPIdetails d join fetch s.myUsers u where s.id = :id")
		// .setParameter("id", schoolId).uniqueResult();

/*school1 = (School) currentSession
.createQuery("select distinct s from School s where s.id = :id")
.setParameter("id", schoolId).uniqueResult();

// return (School) currentSession
		// .createQuery("select distinct s from School s  join fetch s.myComments k  join fetch s.demographicAPIdetails d join fetch s.myUsers u    where s.id = :id")
		// .setParameter("id", schoolId).uniqueResult();

// sessionFactory.getCurrentSession().saveOrUpdate(school);// please
		// uncomment latter
		
		// School school1=new School();



*/
//==========End of Garvage code===============================================================
