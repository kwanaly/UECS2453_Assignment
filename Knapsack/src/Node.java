import java.util.*;
public class Node {
	int stage;
	public double bound;
	double cost;
	LinkedList<Course> courses;
	double weight;
	
	
	public Node(int stage) {
		this.stage=stage;
		this.bound=0;
		this.cost=0;
		this.weight=0;
		this.courses = new LinkedList<>();
	}
	
	public Node(int stage,LinkedList<Course> courses) {
		this.stage=stage;
		this.bound=0;
		this.cost=0;
		this.weight=0;
		this.courses=courses;
	}

	public void addCourse(Course c) {
		courses.add(c);
	}
	
	public void setCourses(LinkedList<Course> courses) {
		this.courses=courses;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public double getWeight() {
		return this.weight;
	}
	public double getBound() {
		return this.bound;
	}
	public LinkedList<Course> getCourses(){
		return this.courses;
	}	
	
	public void setWeight(double weight) {
		this.weight=weight;
	}
	public void setBound(double bound) {
		this.bound=bound;
	}
	public void setCost(double cost) {
		this.cost=cost;
	}
	
	
	
	
	
	
	
}
