package gradebook;

public class GradeInputChecker {
	
	public boolean checkInput(String grade)
	{
		if (!grade.matches("[a-dA-D][+-]?|[eE]|[fF]|[iI]|[wW]|[zZ]")) return false;
		return true;
	}

}
