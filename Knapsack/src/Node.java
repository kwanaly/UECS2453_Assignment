import java.util.LinkedList;

public abstract class Node {
	int stage;
	double value;
	LinkedList<Course> courses;
	double weight;
	
	public Node(int stage) {
		this.stage=stage;
		this.value=0;
		this.weight=0;
		this.courses = new LinkedList<>();
	}
	
	public Node(int stage,LinkedList<Course> courses) {
		this.stage=stage;
		this.value=0;
		this.weight=0;
		this.courses = courses;
	}
	

}

