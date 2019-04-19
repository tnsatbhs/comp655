package gradebook;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentResource {
	
	@Autowired
	private StudentRepository studentRepository;
	
	private GradeInputChecker checker = new GradeInputChecker();
	
	
	@RequestMapping(path = "/student/{name}/grade/{grade}", method = RequestMethod.POST)
	public void createStudent(@PathVariable String name, @PathVariable String grade)
	{
		createUpdateOp(name, grade);		
	}
	
	@RequestMapping(path = "/student/{name}", method = RequestMethod.GET,
			produces={"text/xml;charset=utf-8"})
	public Student getStudent(@PathVariable String name)
	{
		Optional<Student> student = studentRepository.findByNameIgnoreCase(name);
		
		if (!student.isPresent()) throw new StudentNotFoundException("name-" + name);
		return student.get();
	}
	
	@RequestMapping(path = "/student/{name}", method = RequestMethod.DELETE)
	@Transactional
	public void deleteStudent(@PathVariable String name)
	{
		Optional<Student> student = studentRepository.findByNameIgnoreCase(name);		
		if (!student.isPresent()) throw new StudentNotFoundException("name-" + name);
		
		studentRepository.deleteByNameIgnoreCase(name);
		System.out.println(name + " deleted");
	}
	
	@RequestMapping(path = "/student/{name}/grade/{grade}", method = RequestMethod.PUT)
	public void updateCustomer(@PathVariable String name, @PathVariable String grade)
	{
		createUpdateOp(name, grade);
	}
	
	
	@RequestMapping(path = "/student", method = RequestMethod.GET,
			produces={"text/xml;charset=utf-8"})
	public StudentListWrapper getAllStudents()
	{
		List<Student> temp = studentRepository.findAll();
		return new StudentListWrapper(temp);
	}
	
	public void createUpdateOp(String name, String grade)
	{
		if (checker.checkInput(grade)==false) throw new InvalidGradeException("grade-" + grade);
		
		Optional<Student> studentOpt = studentRepository.findByNameIgnoreCase(name);
		
		if (!studentOpt.isPresent()) 
		{
			Student student = new Student();
			student.setGrade(grade);
			student.setName(name);
			studentRepository.save(student);
			
			System.out.println("Created Student " + student.getName());
		}
		else
		{
			Student tempStudent = studentOpt.get();
			Student student = new Student();
			student.id = tempStudent.id;
			student.setGrade(grade);
			student.setName(name);
			studentRepository.save(student);
			System.out.println(name + " changed");
		}
	}

}
