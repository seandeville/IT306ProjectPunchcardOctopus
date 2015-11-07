import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BookStore {
	public static void main(String[] args) throws IOException{
		ArrayList<Student> studentList = new ArrayList<Student>();
		ArrayList<Course> courseList = new ArrayList<Course>();
		ArrayList<Orders> orderList = new ArrayList<Orders>();
		studentList = initializeStudents();
		courseList = initializeCourses();
		
		Student user = login(studentList);
		
		orderList = order(user, courseList);
		printOrder(orderList);
		
	}
	
	static ArrayList<Student> initializeStudents() throws IOException{
		ArrayList<Student> temp = new ArrayList<Student>();
		try{
		BufferedReader br = new BufferedReader(new FileReader(new File("./src/student.txt")));
		String line;
		while((line = br.readLine()) !=null){
			String fName = line.substring(0, line.indexOf(" "));
			String lName = line.substring(line.indexOf(" ")+1, line.indexOf(";"));
			line = line.substring(line.indexOf(";")+1, line.length()-1);
			String gNum = line.substring(0, line.indexOf(";"));
			line = line.substring(line.indexOf(";")+1, line.length()-1);
			String pNum = line.substring(0, line.indexOf(";"));
			line = line.substring(line.indexOf(";")+1, line.length()-1);
			String eAdd = line.substring(0, line.indexOf(";"));
			line = line.substring(line.indexOf(";")+1, line.length()-1);
			String sAdd = line.substring(0, line.indexOf(";"));
			line = line.substring(line.indexOf(";")+1, line.length()-1);
			String pass = line;
			temp.add(new Student(fName, lName, gNum, pNum, eAdd, sAdd, pass));
			
		}
		
		br.close();
		return temp;
		}
		catch (FileNotFoundException e){
		    JOptionPane.showMessageDialog(null,"File Not Found.","GMU Bookstore",JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
	static ArrayList<Course> initializeCourses() throws IOException{
		ArrayList<Course> temp = new ArrayList<Course>();
		try{
		BufferedReader br = new BufferedReader(new FileReader(new File("./src/course.txt")));
		String line;
		
		while((line = br.readLine()) !=null){
			String name = line.substring(0, line.indexOf(";"));
			String iD = line.substring(line.indexOf(";")+1, line.indexOf("*"));
			int stock = Integer.parseInt(line.substring(line.indexOf("*")+1, line.length()));
			temp.add(new Course(name, iD, stock));
			
		}
		br.close();
		return temp;
		}
		catch (FileNotFoundException e){
		    JOptionPane.showMessageDialog(null,"File Not Found.","GMU Bookstore",JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
	static Student login(ArrayList<Student> studentList){
		int x = -1;
		System.out.println(studentList.get(1).getGNumber());
		System.out.println(studentList.get(1).getPassword());
		while(x == -1){
		  //modified into own function to be used in login and create account sections
		    //tries the user input to see if user inputs a valid Gnumber
		        //gets user input
		          String username = BookStore.GNumber();
			
			         for(int i = 0; i < studentList.size(); i++){
			            if(studentList.get(i).getGNumber().equals(username)){
				            x = i;
				        }
			        }
		    
			if(x == -1){
				x = JOptionPane.showConfirmDialog(null, "Sorry! We don't have your account. Do you want to create an account",
						"GMU Bookstore", JOptionPane.YES_NO_OPTION);
				if(x == JOptionPane.YES_OPTION){
					return createAccount(studentList);
				} 
				else {
					x = -1;
				}
			}
		
		}
		int y = JOptionPane.YES_OPTION;
		String pass = studentList.get(x).getPassword();
		while(y == JOptionPane.YES_OPTION){
			String password = JOptionPane.showInputDialog("Please enter your Password:");
			if(pass.equals(password)){
				return studentList.get(x);
			}
			y = JOptionPane.showConfirmDialog(null, "Thats not your password! Do you want to try again?",
					"GMU Bookstore", JOptionPane.YES_NO_OPTION);
			
			//added this to the code because currently if I say no it would just send a null into my menu system
			if (y==JOptionPane.NO_OPTION){
			    System.exit(0);
			}
		}
		return null;
	}
	
	static Student createAccount(ArrayList<Student> studentList){
	    //built functions to check if the string is greater than zero and puts the values into the fields
	    //makes sure the user inputs a string greater than zero to the fields
		String fName = BookStore.DeclaringUserfields("Enter your first name");
		String lName = BookStore.DeclaringUserfields("Enter your last name");
		String gNum = "";
		int x = -1;
		while(x == -1){
		    //utilizes the Gnumber function to get a valid gnumber.
		gNum = BookStore.GNumber();
		x = 1;
		for(int i = 0; i < studentList.size(); i++){
			if(studentList.get(i).getGNumber().equals(gNum)){
				JOptionPane.showMessageDialog(null, "That GNumber is already in use");
				 x = -1;
			}
		}
		
		}
		//makes sure the user inputs a string greater than zero to the fields
		String pass = BookStore.DeclaringUserfields("Enter your Password");
		String pNum = BookStore.DeclaringUserfields("Enter your phone number");
		String eAdd = BookStore.DeclaringUserfields("Enter your email");
		String shipAdd = BookStore.DeclaringUserfields("Enter your shipAdd");
		//returns the new student to the system for ordering
		return new Student(fName, lName, gNum, pNum, eAdd, shipAdd, pass);
	}
	
	static ArrayList<Orders> order(Student user, ArrayList<Course> courseList){
		Boolean order = false;
		ArrayList<Orders> Stud_orders = new ArrayList<Orders>();
		String UpdateChoice = "";
		int Choice = 0;
		//while loop to keep the menu going until the user chooses to purchase books
		while(order == false){
		    //tries the input and sees if it can be parsed to an int and if it between the range of 1-4
		    try{
		        //asks the user to input 1-4 value
                   	UpdateChoice = JOptionPane.showInputDialog(null,"Please select the books you need: \n1)IT 206\n2)IT 300\n3)IT 306\n4)Purchase Books",
                       "GMU Bookstore",JOptionPane.QUESTION_MESSAGE);
                        Choice = Integer.parseInt(UpdateChoice);
                        //checks if user does not put a valid input to use
                    	while(Choice>4 || Choice<1){
                    		UpdateChoice = JOptionPane.showInputDialog(null, "Please select the books you need: \n1)IT 206\n2)IT 306\n3)IT 300\n4)Purchase Books",
                        	    "GMU Bookstore", JOptionPane.QUESTION_MESSAGE);
                    		//the users choice is set
                		 Choice = Integer.parseInt(UpdateChoice);
                         }
                    //checks the users choices and determines what to do with them
                    //1-3 add a order to the list of objects and 4 returns that list out of the function
                	 if(Choice == 1){
                	   Orders item = null;
                	   item = UserSelection(1, courseList, user);
                	   Stud_orders.add(item);
                	 }
                    	if(Choice == 2){
                	   Orders item = null;
                	   item = UserSelection(2, courseList, user);
                           Stud_orders.add(item);
                	 }
                	 if(Choice == 3){
                	    Orders item = null;
                	    item = UserSelection(3, courseList, user);
                	    Stud_orders.add(item);
                        
                    	}
                    //returns the orders out to be used else where in the program
                	 if(Choice == 4){
                	    return Stud_orders;
                    	}

                    }
            	    catch (NumberFormatException f){
                	JOptionPane.showConfirmDialog(null,"Sorry, the value you entered was not 1-5! Please try again", "GMU Bookstore", JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
        		 }
		}
		
		
		return null;
	}
	//function used for the user inputs beside exitting the program
	//it accepts the choice, course list, and the user into the function
	static Orders UserSelection(int choice, ArrayList<Course> courseList, Student user){
	    //checks if the stock is greater than zero whether the user is waitlisted or not
        if(courseList.get(choice).getTextStock()>0){
            JOptionPane.showMessageDialog(null,"You have received your book!","GMU Bookstore",JOptionPane.INFORMATION_MESSAGE);
            courseList.get(choice).setTextStock(courseList.get(choice).getTextStock()-1);
        }
        else{
            JOptionPane.showMessageDialog(null,"You have been waitlisted for your book.","GMU Bookstore",JOptionPane.INFORMATION_MESSAGE);
        }
        //creates an order from the choice the user made and returns it
        Orders temp = new Orders(user, courseList.get(choice));
        return temp;
	}
	
	//function that prints out the books sold
	static void printOrder(ArrayList<Orders> orderList){
	    int bookssold = 0;
	    bookssold = orderList.size();
	    for (int i =0; i<orderList.size(); i++){
	        System.out.println(orderList.get(i).getCourse().getCourseID());
	        
	    }
	}
static String GNumber(){
	    String username ="";
	    boolean flag = false;
	   while (flag ==false){
	    	try{
        	    //gets user input
        	    username = "G1";
        	    String integer_substring = "";
        	    int username_int =0;
            
            	    //while it is not a proper gnumber it makes the user input until it returns a valid Gnumber
            		while (username.charAt(0)!= 'G' || username.length()!=9){
            			//Displays the format the user should input a gnumber and then asks user to input a gnumber
        			 JOptionPane.showMessageDialog(null,"GNumber Format GIIIIIIII I=Integer G=G", "GMU Bookstore", JOptionPane.INFORMATION_MESSAGE);
                		 username = JOptionPane.showInputDialog(null,"Please enter your GNumber:" , "GMU Bookstore", JOptionPane.INFORMATION_MESSAGE);
                		 //checks if the gnumber is 9 length
                		 if(username.length()!=9)
                		 {
                			    JOptionPane.showMessageDialog(null,"Gnumbers are 9 characters long", "GMU Bookstore", JOptionPane.INFORMATION_MESSAGE); 
                		   }
                		 integer_substring = username.substring(1);
                    		username_int = Integer.parseInt(integer_substring);
        
        		 }
            	  flag = true;
            	  return username;
	    	}
        	//this Exception catches if the user did not put a string higher than zero
            	catch (StringIndexOutOfBoundsException f){
                	JOptionPane.showMessageDialog(null,"Don't leave it blank.","GMU Bookstore",JOptionPane.ERROR_MESSAGE);
                	flag = false;
        	}   
             	catch (NumberFormatException e){
                	 JOptionPane.showMessageDialog(null,"Not a valid Gnumber.","GMU Bookstore",JOptionPane.ERROR_MESSAGE);
                	 flag = false;
                 }
	   }
	    return null;
	    
	}
	//function to take user input and makes sure the values are greater than zero to return a string
	static String DeclaringUserfields(String fieldinfo){
	    boolean flag = false;
	    String test = "";
	    while(flag == false){
	        try{
	             test =JOptionPane.showInputDialog(fieldinfo);
	             //calls a charat function to see if the string exists and throws StringIndexOutOFBound Exception if it doesn't
	             char testing = test.charAt(0);
	            flag = true;
	            return test;
	        }
	        //catches if the string is not greater than or equal to zero meaning it does not exist.
	        catch (StringIndexOutOfBoundsException e){
	            JOptionPane.showMessageDialog(null,"Don't leave it blank.","GMU Bookstore",JOptionPane.ERROR_MESSAGE);
	            flag = false;
	        }
	    }
	    return test;
	}
}
