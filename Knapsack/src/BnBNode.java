import java.util.*;
public class BnBNode extends Node {

	double cost;

	
	
	public BnBNode(int stage) {
		super(stage);
		this.cost=0;
	}
	
	public BnBNode(int stage,LinkedList<Course> courses) {
		super(stage,courses);
		this.cost=0;
	}

	public void addCourse(Course c) {
		super.courses.add(c);
	}
	
	public void setCourses(LinkedList<Course> courses) {
		super.courses=courses;
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public double getWeight() {
		return super.weight;
	}
	public double getBound() {
		return super.value;
	}
	public LinkedList<Course> getCourses(){
		return super.courses;
	}	
	
	public void setWeight(double weight) {
		super.weight=weight;
	}
	public void setBound(double bound) {
		super.value=bound;
	}
	public void setCost(double cost) {
		this.cost=cost;
	}
	
	
	
	
	
	
	
}
