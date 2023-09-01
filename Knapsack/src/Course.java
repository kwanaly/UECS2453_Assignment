
public class Course extends CourseAbstract {
	
	//Constructor
	public Course(String name,double weight,double value) {
		super(name,weight,value);
	}
	
	public double valuePerWeight() {
		return (super.value/super.weight);
	}
	
	public String getName() {
		return super.name;
	}
	
	public double getWeight() {
		return super.weight;
	}
	
	public double getValue() {
		return super.value;
	}
	
	public void setWeight(double weight) {
		super.weight=weight;
	}
	public void setValue(double value) {
		super.value=value;
	}
	
}
