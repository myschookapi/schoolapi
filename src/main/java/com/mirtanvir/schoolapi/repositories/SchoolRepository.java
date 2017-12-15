package com.mirtanvir.schoolapi.repositories;

import java.util.List;
import java.util.Set;

import javax.transaction.RollbackException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;











import com.mirtanvir.schoolapi.domain.School;
import com.mirtanvir.schoolapi.domain.Students;
import com.mirtanvir.schoolapi.domain.Subjects;
import com.mirtanvir.schoolapi.domain.Teachers;
import com.mirtanvir.schoolapi.domain.Users;

//public interface SchoolRepository extends PagingAndSortingRepository<School, Long> {
	
	//public Page<School> getByZip(String zipCode, Pageable page);



//----------------------------------------------------



public interface SchoolRepository {

	// Find all contacts
	public List<School> findAllSchool();
	
	
	
	
	// Find all contacts with telephone and hobbies
	public List<School> findAllSchoolWithDetail();
	
	// Find a school with details by id
	public School findSchoolById(Long id);
	
	// Insert or update a school
	public School saveSchool(School contact);
	
	// Delete a school
	public void deleteSchool(School school);
	
	School getSchoolWithComments(Long schoolId);
	
	
	School getSchoolWithCommentsAndDemographicsDetails(Long id);




	Students getStudentById(Long id);
    Students findStudentWithUserid(Long MyUserid);
	//added by kabir for subjects
    Subjects getSubjetWithStudents(Long id);

	Teachers getTeacherWithSubjects(Long id);




	public Users login(long userid);




	// Insert or update a user
		public Users saveUser(Users user) throws RollbackException;



//insert students
		public Students saveStudent(Students st);


		 List<Subjects> getSubjectByStudentId(Long studentid);
		
	
}
