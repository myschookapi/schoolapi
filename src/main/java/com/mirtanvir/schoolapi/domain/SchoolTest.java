package com.mirtanvir.schoolapi.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.relation.Role;
import javax.transaction.RollbackException;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mirtanvir.schoolapi.repositories.SchoolRepository;

@Transactional
@Component
public class SchoolTest {

	
	public static void main(String[] args) {
		
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext(
				Configs.ROOT_CONTEXT_PATH);

		
		
		SchoolTest p = ctx.getBean(SchoolTest.class);
        p.start(args);
	

	}

		
	@Autowired
	SchoolRepository schoolDao;
	
	@Autowired
    private SessionFactory sessionFactory;

	public void start(String[] args)  {

		
		//Testing the login function.
		
		
		/*School onlySchoolsave=new School();
		
		onlySchoolsave.setName("Trying to save only school without other child");
		onlySchoolsave.setApiScore(400);
		onlySchoolsave.setCity("Killeen");
		onlySchoolsave.setZip("78064");
		onlySchoolsave.setState("Tx");
		schoolDao.saveSchool(onlySchoolsave);
		*/
		
		
		
		Users foundUser= schoolDao.login(9L);
		
		
		
		
		//School school1= (School) schoolDao.getSchoolWithComments(8L);
		
		//System.out.println("School name"+school1.getName());	
		
		
		/*System.out.println("School comments"+allSchools.getMyComments());			
		System.out.println("School comments"+allSchools.getDemographicAPIdetails());	
		
		System.out.println("School Students"+allSchools.getMyStudents());	
		
		
		*/
		
		System.out.println();
		System.out.println(" User: "+foundUser.getName()+"("+foundUser.getRole().getRoleType()+")");
		
		
		
		
		for(School sch:foundUser.getMySchools()){
			System.out.println(" School Id: "+sch.getId());
			System.out.println(" School Name: "+sch.getName()+"  City:->"+sch.getCity());
			System.out.println(" STate: "+sch.getState()+"  Zip:->"+sch.getZip());
			System.out.println(" API Score: "+sch.getApiScore());
		
			System.out.println();
		
			System.out.println("All the students of"+sch.getName()+":");
			
			
			System.out.println("-------------------------------------------------------------------------------");
			
			System.out.println("StudentId: " +"   First Name: "+"    Last Name: "+"       Phone: "+"           Email: "+"                     School_Name:");

			System.out.println("--------------------------------------------------------------------------------");	
			for(Students std:sch.getMyStudents()){
				
					
					
					System.out.println(std.getId()+"            "+std.getFirstName()+"            "+std.getLastName()+"        "+std.getPhone()+"        "+std.getEmail()+"          "+std.getSchool().getName()    );
				
					System.out.println();
				}
			
			
			
			System.out.println();
			
			System.out.println("Lets See the Detail of Strudent 23:");
			System.out.println();
			
			Students Studetail13=schoolDao.getStudentById(13L);
			System.out.println("StudentId: " +"   First Name: "+"    Last Name: "+"       Phone: "+"           Email: "+"                     School_Name:");
			
			System.out.println(Studetail13.getId()+"            "+Studetail13.getFirstName()+"            "+Studetail13.getLastName()+"        "+Studetail13.getPhone()+"        "+Studetail13.getEmail()+"          "+Studetail13.getSchool().getName()    );
			
			System.out.println();
			System.out.println("Parents:   "+Studetail13.getMyStudentUsers().size());
			for(Users p:Studetail13.getMyStudentUsers()){
			
			System.out.println("--------------------------------------------------------------------------------");	
			System.out.println("Pratnts: "+p.getName()+"  Relationship: "+p.getRelationship()+"  Mobile: "+p.getMobile());	
			
			
			
			
			}
			
			for(Subjects sub:Studetail13.getMySubjects()){
				
				
				System.out.println("--------------------------------------------------------------------------------");	
				System.out.println("SubId: "+sub.getId()+"  Name: "+sub.getName()+"  Desc: "+sub.getDesc()+
						"   Grade:"+sub.getGrader()+"  Teacher:"+sub.getTeacher().getFirstName()+" "+sub.getTeacher().getLastName());	
				
				
				
				
			
			}
			
			
			
		
		
		
		
		}
			
	
		
		
		System.out.println("Just Gettign the Subject only by student id: ");
		
		List <Subjects> mysubonly=schoolDao.getSubjectByStudentId(13L);
		
		
		for(Subjects sb:mysubonly){
			
			System.out.println("All Subjects taken by this student  : "+sb.getName() + ""+sb.getId()+" "+sb.getDesc()+" "+sb.getGrader());
			System.out.println("All Student took   : "+sb.getName() + ""+sb.getId()+" "+sb.getDesc()+" "+sb.getGrader());
			
					for(Students std:sb.getMySubjStudent()){
			
						System.out.println("First Name:  "+std.getFirstName()+" Last Name:  "+std.getLastName()+" Eamil :  "+std.getEmail()+" Student ID:  "+std.getId()+" Student School Name:  "+std.getSchool().getName());
			
						}
			
			
		}
		
		
		
		
		
		
		
		
		
	/*		
			//System.out.println("Demographic id for this school  "+sc.getDemographicAPIdetails());
			//System.out.println("School comments"+sc.getMyComments());			
			    for(Students st :sc.getMyStudents()){
			    	
			    	
			    	System.out.println("Student First Name: "+st.getFirstName());
			    	
			    	System.out.println("Student Last Name: "+st.getLastName());
			    	System.out.println("Student's School Name: "+st.getSchool().getName());
			    	
			    	
			    	
			       }
			
			
			
			
			}
		


		
		Students addingStudent=new Students();
		addingStudent.setFirstName("Mainuddin");
		addingStudent.setLastName("Rumee");
		addingStudent.setEmail("abc@yahoo.com");
		addingStudent.setPhone("408-725-9653");
		
		
		
		
		School mySchool=allSchools.get(0);
		
		//System.out.println("School demographic from mySchool im am sending--->: "+mySchool.getDemographicAPIdetails());
		System.out.println("Before Adding Rumee All the student I got-->");
		for(Students st :mySchool.getMyStudents()){
	    	
			
	    	System.out.println("Student First Name from mySchool: "+st.getFirstName());
	      System.out.println("Student Last Name from mySchool: "+st.getLastName());
	    	System.out.println("Student's School Name from mySchool: "+st.getSchool().getName());
	    	
	    	
	    	
	       }
	
	*/
		
		
		
		//mySchool.getMyStudents().
		
		
		/*
		DemographicAPI schoolDemograph=new DemographicAPI();
		
		schoolDemograph.setApiScore(930);
		schoolDemograph.setDemographicType("Indian");
		mySchool.addApidetail(schoolDemograph);
		*/
		
		
		
		/*addingStudent.setSchool(mySchool);
		
		
		mySchool.addStudentset(addingStudent);
		
		
			
		try {
		schoolDao.saveSchool(mySchool);
		} catch (Exception e) {
			System.out.println("Something Went Wrong while saving the school object");
			e.printStackTrace();
		}
		System.out.println("After  Adding Rumee asll student I got-->");	
		
for(Students st :mySchool.getMyStudents()){
	    	
			
	    	System.out.println("Student First Name from mySchool: "+st.getFirstName());
	      System.out.println("Student Last Name from mySchool: "+st.getLastName());
	    	System.out.println("Student's School Name from mySchool: "+st.getSchool().getName());
	    	
	    	
	    	
	       }
		
		
		
		

Users clark=new Users();
clark.setName("James Bond");
clark.setEmail("James_bond@gmail.com");
clark.setAddress("Mi5 headquarter london");
clark.setRelationship("School_clark");

Roles clarkRole= new Roles();
clarkRole.setId(4);
*/


//clark.setRole(role);




		
		
		
		//============================================================================================================
		
		
		
		/*		System.out.println("--------------------------------------------------");	        
		System.out.println("First Name:"+stud.getFirstName());
		System.out.println("Last Name:"+stud.getLastName());
		System.out.println("Phone:"+stud.getPhone());
		System.out.println("Email:"+stud.getEmail());
		System.out.println("School Name:"+stud.getSchool().getName()+","+" "+stud.getSchool().getCity()+","+stud.getSchool().getState()+"-"+stud.getSchool().getZip());


Set<Users> parents=(Set<Users>) stud.getMyStudentUsers();
for(Users par:parents)
{
System.out.println("Gurdians:"+par.getName()+" Relationship:"+par.getRelationship()+" Phone:"+par.getMobile());    	
}

System.out.println("----------------------------------------------------");*/
		
		
		
		
		/*School rumiSchool=new School();
		
		rumiSchool.setName("Govt High School");
		rumiSchool.setCity("Jhenidah");
		rumiSchool.setApiScore(650);
		rumiSchool.setZip("7200");
		rumiSchool.setState("Bangladesh");
		
		SchoolComment  commentssave=new SchoolComment(); 		
		commentssave.setComment("New principle will be held meeting before start");
		rumiSchool.addCommentset(commentssave);
		
		DemographicAPI mydemoaoisave=new DemographicAPI();
		
		
		mydemoaoisave.setDemographicType("East Asia");
		mydemoaoisave.setApiScore(750);
		rumiSchool.addApidetail(mydemoaoisave);
		
		
		Students Student_Of_rumiSchool=new Students();
		
		Student_Of_rumiSchool.setFirstName("Van");
		Student_Of_rumiSchool.setLastName("Damme");
		Student_Of_rumiSchool.setPhone("50-97-5477");
		Student_Of_rumiSchool.setEmail("abc@bbc.com");
		Student_Of_rumiSchool.setSchool(rumiSchool);
		rumiSchool.addStudentset(Student_Of_rumiSchool);
		
		*/
		/*try {
			schoolDao.saveSchool(rumiSchool);
		} catch (RollbackException e) {
			System.out.println("Somethig Went wrong!!!");
			e.printStackTrace();
		}*/
		
		
		
		//Set<School> school1=new HashSet<School>();
		
		
		//school1.add(rumiSchool);
		
		/*Users father_of_Rumi=new Users();
		
		father_of_Rumi.setName("Ak B Karim");
		father_of_Rumi.setAddress("Jhenidah Bangladesh");
		father_of_Rumi.setEmail("bkarim@hotmail.com");
		father_of_Rumi.setHomePhone("880-25477");
		father_of_Rumi.setMobile("880-41-91145");
		father_of_Rumi.setRelationship("FATHER");	
		
		
		
		//father_of_Rumi.setMySchools(school1);
	
		
		
		try {
			schoolDao.saveUser(father_of_Rumi);
		} catch (RollbackException e) {
			System.out.println("Somethig Went wrong in saving the user!!!");
			e.printStackTrace();
		}
		
		
		*/
		
		
			
		
		
	/*	
		DemographicAPI myapi=new DemographicAPI();
		myapi.setDemographicType("Asian");
		myapi.setApiScore(750);
		
		rumiSchool.addApidetail(myapi);
		
	
		SchoolComment rumisSchoolComment=new SchoolComment();
		rumisSchoolComment.setComment("Free new books will be given on Jan 3,2016");
		
		rumiSchool.addCommentset(rumisSchoolComment);
			

		
		Students rmsSchoolStudent=new Students();
		rmsSchoolStudent.setFirstName("Nishat");
		rmsSchoolStudent.setLastName("Tasnim");
		rmsSchoolStudent.setEmail("xyz@yahoo.com");
		rmsSchoolStudent.setPhone("405-888-3625");
		
		
		//Adding the school object to the student object as ref
		rmsSchoolStudent.setSchool(rumiSchool);
		
		
		
		Users Parents1=new Users();
		Parents1.setName("Bazlul Karim");
		Parents1.setAddress(" 444 Dempsey Road Milpitas,CA-95035");
		Parents1.setHomePhone("408-941-9981");
		Parents1.setMobile("408-83-5541");
		Parents1.setEmail("bkarim@hotmail.com");
		Parents1.setRelationship("Father");
		
		// Adding this parent/user to  this school object
		rumiSchool.addUserset(Parents1);
		
		// Adding this parents/user to the student object
		rmsSchoolStudent.addUsers(Parents1);
		
	// Adding subjects to the student object
		
		Subjects rumiSubjects=new Subjects();
		rumiSubjects.setName("Chemistry 202");
		rumiSubjects.setDesc("Organic Chemistry1");
		rumiSubjects.setGrader(9);
		
		 //Adding books to the subject
		Books chemBook=new Books();
		chemBook.setTitle("Oraganic chemistry Dummies");
		chemBook.setAuthor(" Dr.Faruk E azam");
		chemBook.setIsbn(123975);
		chemBook.setEdition("7th Addition");
		rumiSubjects.addBooks(chemBook);
		
		
		
		
		Teachers chemistryTecher=new Teachers();
		chemistryTecher.setFirstName("Van");
		chemistryTecher.setLastName("Reeves");
	     chemistryTecher.setEmail("uuu@hotmail.com");
		 chemistryTecher.setPhone("99-222-854");
		
		// Adding this teacher  to subject  bec. subject is the owning side
		//rumiSubjects.setTeacher(chemistryTecher);
		
		
		chemistryTecher.addSubjects(rumiSubjects);
		
		
		rmsSchoolStudent.addSubjects(rumiSubjects);
		
		rumiSchool.addStudentset(rmsSchoolStudent);
			
		try {
			schoolDao.saveSchool(rumiSchool);
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		*/
		
	
		
		/*
		
		
		
		//===========================================
		
	//	Parents1.addSchool(rumiSchool);
		
		
		//============================================
		
	// Adding the student to the user/parents object
	//Parents1.addStudents(rmsSchoolStudent);
		
		//rumiSchool.addStudentset(rmsSchoolStudent);
		
		
		//schoolDao.saveSchool(rumiSchool);
		
		
		
		
		
		//Uncomment the letter when you retrive school
		
		
		*/
		
			//@SuppressWarnings("unchecked")
			//School school1= (School) schoolDao.getSchoolWithComments(8L);
	  //List<School> student=schoolDao.findAllSchool();
		//System.out.println("Student found by providing userid:"+student);
		//System.out.println("Student found by providing userid:"+school1);
		
		/*
			for(Users parent: school1.getMyUsers()) {
			//System.out.println("parent Name: " + parent.getName()+"--Relationship to Student :"+ parent.getRelationship());
			//System.out.println("Students Found under user/Perents:"+parent.getStudents());
			Set<Students> students = parent.getStudents();
			
			if (students != null) {
				for (Students student : students) {				
					System.out.println("Student First Name: "+student.getFirstName());
					School school = student.getSchool();
					System.out.println("School Name: "+school.getName());
					
					
					
					Set<Subjects> subjects = student.getMySubjects();
					if (subjects != null) {
						for (Subjects subject : subjects) {	
					System.out.println("Subject Taken By Students:"+subject.getName());
					
					Teachers teacher = subject.getTeacher();
					System.out.println("Teacher of this subject:"+teacher.getFirstName()+" "+teacher.getLastName());	
						
						
						
						
						
					}	                 }
				}
			}
		
		
		}
		
		
		
		// code for finding Teacher with subjects and students
		
		@SuppressWarnings("unchecked")
		Teachers teacher1= (Teachers) schoolDao.getTeacherWithSubjects(10L);
		
		System.out.println("Teacher found by giving teacher name:"+teacher1.getFirstName()+" "+teacher1.getLastName());	
		
		
		
		
		Set<Subjects> subjects = teacher1.getmySubjects();
		
		
		for (Subjects sub : subjects) {	
			System.out.println("Subject Taken By:-"+teacher1.getFirstName()+" "+teacher1.getLastName()+" , SubjectID:"+sub.getId()+":Class Code:-"+sub.getName()+": Description:-"+sub.getDesc()+":Grade:-"+sub.getGrader());
		
		
		}
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
/*
		
		// SchoolRepository schoolDao = ctx.getBean( SchoolRepository.class);//
		// check this line later

		// List contacts without details
		//List<School> schools = schoolDao.findAllSchool(); //Deleted by kabir add later

		//listContacts(schools);//Deleted by kabir add later

		// List contacts with details
		//schools = schoolDao.findAllSchoolWithDetail();//Deleted by kabir add later
		//listContactsWithDetail(schools);//Deleted by kabir add later

		//School school;

		// Find contact by ID
		//school= schoolDao.findSchoolById(100l);
		
		
		
		

		// Add new contactkabir
		
		/* Temporarily commented
		School	myschool = new School();
		myschool.setName("Mission High Elementary");
		myschool.setApiScore(950);
		myschool.setCity("Freemont");
		myschool.setState("CA");
		myschool.setZip("95051");
		//myschool.setId(4);
		
		System.out.println("School Without schoold id:" + myschool);
		
		DemographicAPI myapi=new DemographicAPI();
		myapi.setDemographicType("Hispanic");
		myapi.setApiScore(750);
		//myapi.setId(5);
		DemographicAPI my_api1=new DemographicAPI();
		my_api1.setDemographicType("Asian");
		my_api1.setApiScore(980);
		//myapi1.setId(6);
		
		myschool.addApidetail(myapi);
		
		myschool.addApidetail(my_api1);
		
		System.out.println("MY SCHOOL After Adding New Dempgraphic Api in the Hash list and Before saving  " + myschool);
		
		
	
		
		
	
		
		//School retrieveSchool=schoolDao.find;

	//Students mystudent=new Students();
	//mystudent.setFirstName("Rumi");
	//mystudent.setLastName("karim");
	///mystudent.setPhone("408-945-2265");
	//mystudent.setEmail("rumi@yahoo.com");
	
	//Users Parents1=new Users();
	//Parents1.setName("Maleka Karim");
	//Parents1.setAddress("444 Dempsey Road Milpitas,CA-95035");
	//Parents1.setHomePhone("408-941-9981");
	//Parents1.setMobile("408-83-5541");
	//Parents1.setEmail("maleka@hotmail.com");
	//Parents1.setRelationship("Mother");
	
	//School	myschool = new School();
	//myschool.setName("Berriyesa High Elementary");
	//myschool.setApiScore(910);
	//myschool.setCity("San jose");
	//myschool.setState("CA");
	//myschool.setZip("95051");
	
	// Set<Users> studentUsers = new HashSet<Users>();
	 //studentUsers.addAll(Arrays.asList(new Users[] {Parents1}));
	
	 //studentUsers.add(Parents1);
	 
	//mystudent.setMyUsers(studentUsers);
	
	//myschool.addStudentset(mystudent);
	//myschool.addUserset(Parents1);
	//schoolDao.saveSchool(myschool);	
	
	//System.out.println("School Retrieve from DataBase Here@@:  " + myschool);
	
	
	//Students student = schoolDao.getStudentById(14L);
	//Set<Users> myUsers = student.getMyUsers();
	
	//Students student=schoolDao.findStudentWithUserid(7L);
		

	//	Session session = sessionFactory.getCurrentSession();
		 
		//session.beginTransaction();
		

*/

		

	//System.out.println("The subjects taken by this teacher:"+teacher1.getmySubjects());	
	
	
	
	
	
	//System.out.println("Tsubjects found by giving teacher name:"+teacher1.getFirstName()+" "+teacher1.getLastName());	
	
	
	
	
	
//===============================================================	
	
	
	
	
	
	
	
	
	
	
	
	
	//For get all the students fom a specific subjects
	
	//Subjects subject1=(Subjects)schoolDao.getSubjetWithStudents(1L);
	
	
	//for(Students studentBySubject: subject1.getMySubjStudent()) {

	
		//System.out.println("Student First Name: " +studentBySubject.getFirstName()+"Student Last Name:"+studentBySubject.getLastName());
		
		

	
	//}
	
	
	
	
	//========================================================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//session.getTransaction().commit();


	
	
	
	
	
	
	
	
	
	
	
	/*		
		
		Users parent1=new Users();
		
		parent1.setName("Mohammad Kabir");
		parent1.setAddress("444 Dempsey road,Milpitas,ca-95035");
		parent1.setHomePhone("408-941-9981");
		parent1.setMobile("408-836-5541");
		parent1.setEmail("boby@yahoo.com");
		parent1.setRelationship("Father");
		
		School schoolWithStudents=new School();
		schoolWithStudents.setName("Saint Joseph High School");
		schoolWithStudents.setApiScore(900);
		schoolWithStudents.setCity("Belmont");
		schoolWithStudents.setState("CA");
		schoolWithStudents.setZip("93034");
		
		Students myStudents=new Students();
		myStudents.setFirstName("Rifat");
		myStudents.setLastName("Kabir");
		myStudents.setPhone("408-836-2081");
		myStudents.setEmail("rifat@gmail.com");
		
		
		
		
		
		//myStudents.addUserset(parent1);
		//DemographicAPI my_api1=new DemographicAPI();
		//my_api1.setDemographicType("Asian");
		//my_api1.setApiScore(930);
		
		schoolWithStudents.addStudentset(myStudents);
		schoolWithStudents.addUserset(parent1);
		
		schoolDao.saveSchool(schoolWithStudents);
		
	*/	
		
		
	//	---------------------------------------
		
	
		
	/*	
		
		School retrieveSchool=schoolDao.findSchoolById((long) 8);	
		
			
		System.out.println("School Retrieve from DataBase Here:  " + retrieveSchool);
		
		retrieveSchool = schoolDao.getSchoolWithCommentsAndDemographicsDetails(7L);
		System.out.println(retrieveSchool);
		for (SchoolComment c:retrieveSchool.getMyComments()) {
			System.out.println(c.toString());
		}
		for (DemographicAPI d: retrieveSchool.getDemographicAPIdetails()) {
			System.out.println(d.toString());
		}
	
	*/	
		/*
		
		Set <DemographicAPI> retrivedDemographicapi=retrieveSchool.getDemographicAPIdetails();
		
		
		DemographicAPI Africanapi=new DemographicAPI();
		Africanapi.setDemographicType("African American");
		Africanapi.setApiScore(750);
		
		
		DemographicAPI Asianapi=new DemographicAPI();
		Asianapi.setDemographicType("Asian");
		Asianapi.setApiScore(900);
		
		retrieveSchool.addApidetail(Africanapi);
	
		retrieveSchool.addApidetail(Asianapi);
		
		*/
		
		/*
		retrieveSchool.setName("Rose Elementary");
		
		Set <DemographicAPI> retrivedDemographicapi=retrieveSchool.getDemographicAPIdetails();
		
		DemographicAPI chngedApi=null;
		
				for (DemographicAPI eachApi:retrivedDemographicapi )
						{
			
			            if(eachApi.getDemographicType().equalsIgnoreCase("asian"))
			            		{
			            	
			            	System.out.println("Found one school with : " +eachApi.getDemographicType()+"Demographic Type");
			            	eachApi.setDemographicType("Indian");   
			            	eachApi.setApiScore(900);
			            		}
							
						}
		
		
		
		
		*/
	/*	
		
	// Deleting the Hispanic Api	
		
		
		Set <DemographicAPI> retrivedDemographicapi=retrieveSchool.getDemographicAPIdetails();
		
		DemographicAPI deleteApi=null;
		
		for (DemographicAPI eachApi:retrivedDemographicapi )
				{
	
	            if(eachApi.getDemographicType().equalsIgnoreCase("Indian"))
	            		{
	            	
	            	
	            	
	            	System.out.println("Found one school with : " +eachApi.getDemographicType()+"Demographic Type");
	            	deleteApi=eachApi;
	            	
	            	
	            	//eachApi.setDemographicType("Indian");   
	            	//eachApi.setApiScore(900);
	            		}
					
				}

	
	
		
		
		retrieveSchool.removeApidetail(deleteApi);
		
		*/
		
		
	// Adding comments for Testing
		/*
		SchoolComment myComments=new SchoolComment();
		
		myComments.setComment("2014 Year school will be started on August 18,2014");
		
		schoolDao.saveSchool(retrieveSchool);

		*/
	
		
		//List <School>	myschools = schoolDao.findAllSchoolWithDetail();
		//listSchoolsWithDetail(myschools);
		

//	=========================================================================	
		
	/*	
		School	myschool = new School();
		myschool.setName("Harrsion High Elementary");
		myschool.setApiScore(850);
		myschool.setCity("Hayward");
		myschool.setState("CA");
		myschool.setZip("95051");
		
		DemographicAPI Africanapi=new DemographicAPI();
		Africanapi.setDemographicType("African American");
		Africanapi.setApiScore(750);	
		
		myschool.addApidetail(Africanapi);

		
		
SchoolComment myComments=new SchoolComment();
myComments.setComment("2014 Year school will be started on August 18,2014");
	
myschool.addCommentset(myComments);		
		
schoolDao.saveSchool(myschool);		

*/
//===================================================================================		
		
		
		
		
		
		
		
		
		
		
		
//	
		
		
		
		
/*	
		
		
		School school1 = schoolDao.findSchoolById(2L);
		DemographicAPI api = new DemographicAPI();
		api.setApiScore(896);
		api.setDemographicType("fas");
		api.setId(10);
		school1.getDemographicAPIdetails().add(api);
		schoolDao.saveSchool(school1);
		System.out.println(school1);
		
		
		/*List <School>	myschools = schoolDao.findAllSchoolWithDetail();
		listSchoolsWithDetail(myschools);
*/
		
	}

	
	

	private static void listContacts(List<School> incomingSchools) {
		System.out.println("");
		System.out.println("Listing contacts without details:");
		for (School school : incomingSchools) {
			System.out.println(school);
			System.out.println();
		}
	}

	private static void listSchoolsWithDetail(List<School> schools) {
		System.out.println("");
		System.out.println("Listing contacts with details:");
		for (School school : schools) {
			System.out.println(school);
			// if (school.getSchoolApiDetail() != null) {
			// for (SchoolApidetail apiDetail: school.getSchoolApiDetail()) {
			// System.out.println(apiDetail);
			// }
			// }
			// if (contact.getHobbies() != null) {
			// for (Hobby hobby: contact.getHobbies()) {
			// System.out.println(hobby);
			// }
			// }
			System.out.println();
		}
	}

}// end of class
