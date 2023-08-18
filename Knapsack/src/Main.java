import java.util.*;

public class Main {

	public static void main(String[] args) {
		LinkedList<Course> courses = new LinkedList<Course>();
		
		//EXAMPLE
		
		Course c1 = new Course("ARTIFICIAL INTELLIGENCE",6,8);
		Course c2 = new Course("PROGRAMMING WITH GAME ENGINES",2,7);
		Course c3 = new Course("ADVANCED DATABASE SYSTEMS",5,9);
		Course c4 = new Course("CLOUD COMPUTING",4,6);
		Course c5 = new Course("DATA MINING",3,8);
		courses.add(c1);
		courses.add(c2);
		courses.add(c3);
		courses.add(c4);
		courses.add(c5);

		BranchandBound(courses,12);
		
		
	}
	
	public static void BranchandBound(LinkedList<Course> courses,double maxC) {

		Queue<Course> q = new LinkedList<Course>();
		Iterator<Course> it = courses.iterator();
		while(it.hasNext()) {
			q.add(it.next());
		}
		
		//Initial stage
		BnBNode initialNode = new BnBNode (-1);
		System.out.println("===Initial Node===");
		
		
		initialNode = ComputeBound(q,initialNode,maxC);

		BranchandBound(q,initialNode,maxC);	 //Recursion Helper method

	}
	
	public static void BranchandBound(Queue<Course> q,BnBNode node,double maxC) {
		
		
		if (!(q.isEmpty())) {

			LinkedList<Course> c1 = (LinkedList<Course>)(node.getCourses().clone());
			LinkedList<Course> c0 = (LinkedList<Course>)(node.getCourses().clone());
			
			int s = node.stage+1;
			System.out.println("Stage "+s);
			BnBNode node1= new BnBNode(s,c1);
			BnBNode node0= new BnBNode(s,c0);
			
			
			
			System.out.println("===Node 1===");
			Queue<Course> temp1 = new LinkedList<>();
			temp1.addAll(c1);
			temp1.addAll(q);
			
			node1 = ComputeBound(temp1, node1, maxC);

			node1.addCourse(q.remove()); 	//Remove the item from queue which is be excluded in node0


			System.out.println("===Node 0===");
			Queue<Course> temp0 = new LinkedList<>();
			temp0.addAll(c0);
			temp0.addAll(q);
			
			node0 = ComputeBound(temp0, node0, maxC);

			
			//Select better value branch
			if ((node1.getBound()>=node0.getBound())||((node1.getBound()==node0.getBound())&&node1.getCost()>=node0.getCost())) {
				System.out.println("Node 1 selected");
				BranchandBound(q,node1,maxC);

			}
			else {
				System.out.println("Node 0 selected");
				BranchandBound(q,node0,maxC);

			}	//Recursion


		}		
		else {
			System.out.println("Solution for knapsack problem with Branch and Bound Method:");
			System.out.println("total value: "+node.getBound());
			System.out.println("Total weight: "+node.getWeight());
			System.out.println("Items :");
			for (int i=0;i<node.getCourses().size();i++) {
				System.out.println((i+1)+". "+node.getCourses().get(i).getName());
			}
			return;
		}
		
	}
	
	public static BnBNode ComputeBound(Queue<Course> q,BnBNode node,double maxC) {
		Iterator<Course> iterator = q.iterator();
		while(iterator.hasNext()) {
			Course c = iterator.next();
			if (node.getWeight()+c.getWeight()<=maxC) {	//Check whether exceed capacity
				node.setBound(node.getBound()+c.getValue());	//If no, continue adding

				node.setCost(node.getCost()+c.getValue());
				node.setWeight(node.getWeight()+c.getWeight());
				
			}
			else if (node.getWeight()<maxC) {	//Not exceed capacity

				node.setCost(node.getCost()+(maxC-node.weight)*(c.ValuePerWeight()));
				//node.setWeight(node.getWeight()+c.getWeight());
				break;

			}
			else {
				break;
			}
			
		}
		System.out.println("Weight: "+node.getWeight());
		System.out.println("Bound: "+node.getBound());
		System.out.println("Cost: "+node.getCost());
		
		
		return node;
	}

}
