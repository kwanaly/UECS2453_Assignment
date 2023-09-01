import java.io.*;
import java.util.*;

public class FileRead {
	
	public FileRead() {}
	
	File courseFile = new File("Course.txt");

	public LinkedList<Course> readCourseFile() {
	
		Scanner read = null;
		LinkedList<Course> courses = new LinkedList<Course>();
	
		try {
			read = new Scanner(courseFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!!");
		}
	
		while (read.hasNext()) {
	
			String[] info = read.nextLine().split("---");
	
			Course c = new Course(info[0], Double.parseDouble(info[1]), Double.parseDouble(info[2]));
			courses.add(c);
		}
	
		return courses;
	}
}
