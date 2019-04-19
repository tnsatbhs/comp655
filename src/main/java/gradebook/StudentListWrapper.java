package gradebook;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="student-list")
public class StudentListWrapper {
	
	private List<Student> students;
	
	public StudentListWrapper(List<Student> students)
	{
		this.students = students;
	}
	
	@JacksonXmlElementWrapper(useWrapping=false)
	@JacksonXmlProperty(localName="student")
	public List<Student> getStudents()
	{
		return students;
	}
	
	public void setStudents(List<Student> students)
	{
		this.students = students;
	}

}
