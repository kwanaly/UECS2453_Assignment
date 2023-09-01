import java.util.*;

public class Main {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
	
		Course c = new Course(null, 0, 0);
		FileRead f = new FileRead();
		
		LinkedList<Course> courses = new LinkedList<Course>();
		courses = f.readCourseFile() ;
	
		int option = 0;
		char ans;

		do {

			System.out.println("\t------------------------------------------");
			System.out.println("\t+\tCourse Selection System\t\t +");
			System.out.println("\t------------------------------------------");

			System.out.println("\t|\tSelect Courses with: \t\t |");
			System.out.println("\t|\t\t\t\t\t |");
			System.out.println("\t|\t1.\tBranch and Bound\t |");
			System.out.println("\t|\t2.\tGreedy Algorithm\t |");
			System.out.println("\t|\t\t\t\t\t |");
			System.out.println("\t------------------------------------------");

			System.out.println();
			System.out.print("\tChoose strategy (1/2): ");
			option = input.nextInt();
			
			System.out.println();

			while (option < 1 || option > 2) {
				System.out.println("Invalid input.");
				System.out.print("Enter your option (1/2): ");
				option = input.nextInt();
			}

			switch (option) {

			case 1:
				BranchandBound(courses,12);	
				break;
			case 2:
				Collections.sort(courses, new CourseComparator());
				GreedyAlgo(courses,12);
				break;
			}

			System.out.println();
		    System.out.println("--------------------------------------------------------------------");
			System.out.print("Do you still want to use the system?[Y/N]: ");
			ans = input.next().charAt(0);
			System.out.println();

			if (ans == 'N' || ans == 'n') {
				System.out.println("Thank you for using!");
			}
			
		} while (ans == 'Y' || ans == 'y');
		
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
			System.out.println();
		    System.out.println("--------------------------------------------------------------------");
			System.out.println("    Solution for knapsack problem with Branch and Bound Method");
		    System.out.println("--------------------------------------------------------------------");
			System.out.println(" Courses Selected:");
			for (int i=0;i<node.getCourses().size();i++) {
				System.out.println(" " + (i+1)+". "+node.getCourses().get(i).getName());
			}
			System.out.println();
			System.out.println(" Total value: "+node.getBound());
			System.out.println(" Total weight: "+node.getWeight());
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

				node.setCost(node.getCost()+(maxC-node.weight)*(c.valuePerWeight()));
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
	
	// Greedy Algorithm
	public static void GreedyAlgo(LinkedList<Course> courses, double maxC) {
	    double totalValue = 0;
	    double totalWeight = 0;
		
		LinkedList<Course> selectedCourses = new LinkedList<Course>();
		
	    for (Course course : courses) {
	        if (totalWeight + course.getWeight() <= maxC) {
	        	selectedCourses.add(course);
	            totalValue += course.getValue();
	            totalWeight += course.getWeight();
	        }
	    }
	    
	    System.out.println("--------------------------------------------------------------------");
	    System.out.println("\tSolution for knapsack problem with Greedy Algorithm");
	    System.out.println("--------------------------------------------------------------------");
	    System.out.println(" Courses Selected:");
	    for (int i = 0; i < selectedCourses.size(); i++) {
	        System.out.println(" " + (i + 1) + ". " + selectedCourses.get(i).getName());
	    }
	    System.out.println();
	    System.out.println(" Total value: " + totalValue);
	    System.out.println(" Total weight: " + totalWeight);
	}


}
