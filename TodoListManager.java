import java.util.*;
import java.io.*;

// Evelyn Salas
// C0: Todo List Manager
// This class creates and manages a todo list.

public class TodoListManager {
    public static final boolean EXTENSION_FLAG = true;

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> todos = new ArrayList<String>();
        System.out.println("Welcome to your TODO List Manager!");
        System.out.println("What would you like to do?");
        System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs,"
            + " (S)ave TODOs, (Q)uit? ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(
            !input.equalsIgnoreCase("q")) {
            if(!input.equalsIgnoreCase("a")&& !input.equalsIgnoreCase("m")
                && !input.equalsIgnoreCase("l") && !input.equalsIgnoreCase("s")
                && !input.equalsIgnoreCase("q")) {
                System.out.println("Unknown input: " + input);
                printTodos(todos);
            }
            if (input.equalsIgnoreCase("a")) {
                addItem(scanner, todos);
            } else if(input.equalsIgnoreCase("m")) {
                markItemAsDone(scanner, todos);
            } else if(input.equalsIgnoreCase("l")) {
                loadItems(scanner, todos);
            } else if (input.equalsIgnoreCase("s")){
                saveItems(scanner, todos);
            }

            if (EXTENSION_FLAG) {
                System.out.print("Would you like to remove an item using the name? ");
                String answer = scanner.nextLine();
                if(answer.equalsIgnoreCase("yes")){
                    removeItem(scanner, todos);
                }

            }

            System.out.println("What would you like to do?");
            System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs,"
            + " (S)ave TODOs, (Q)uit? ");
            input = scanner.nextLine();


        }


    }

    //Takes in a list of todo items as a parameter and prints them
    public static void printTodos(List<String> todos) {
        System.out.println("Today's TODOs:");
        if (todos.isEmpty()) {
                System.out.println("  You have nothing to do yet today! Relax!");
        } else {
            String list = "";
            for(int i = 0; i < todos.size(); i++) {
                list = "  " + (i+1) + ": " + todos.get(i);
                System.out.println(list);
            }
        }
    }

    //takes in user input and a todo list as a parameter and adds items to the list
    public static void addItem(Scanner console, List<String> todos) {
        System.out.print("What would you like to add? ");
        String item = console.nextLine();
        int size = todos.size();
            if(size == 0){
                todos.add(size , item);
               printTodos(todos);
               size++;
            }
            else {
                System.out.print("Where in the list should it be (1-" + (size + 1) +
                                                                ")? (Enter for end): ");
                String limit = console.nextLine();
                if (!limit.equals("")){
                    int num = Integer.parseInt(limit);
                    todos.add(num-1, item);
                    printTodos(todos);
                } if (limit.equals("")){
                    todos.add(size, item);
                    printTodos(todos);
                }
            }

    }

    //takes in user input and a todo list as parameters and removes competeled items from list
    public static void markItemAsDone(Scanner console, List<String> todos) {
        if(todos.isEmpty()){
            System.out.println("All done! Nothing left to mark as done!");
            printTodos(todos);

        } if(!todos.isEmpty()) {
            int range = todos.size();
            System.out.print("Which item did you complete (1-" + range + ")? ");
            String index = console.nextLine();
            int numIndex = Integer.parseInt(index);
            todos.remove(numIndex-1);
            printTodos(todos);
        }

    }

    //takes in user input and a list of todos as parameters, loads todo items
    //from another file and stores them in list
    //throws File Not Found Exception
    public static void loadItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        System.out.print("File name? ");
        File file = new File(console.nextLine());

        todos.clear();
        int count = 0;
        console = new Scanner(file);
        while (console.hasNextLine()) {
            String line = console.nextLine();
            todos.add(count, line);
            count++;
        }
        printTodos(todos);
    }

    //takes user input and a todo list as parameters, saves todo list as a file
    //throws a file not found exception
    public static void saveItems(Scanner console, List<String> todos)
                                  throws FileNotFoundException{
        System.out.print("File name? ");
		String outFile = console.nextLine();

		PrintStream output = new PrintStream(new File(outFile));
		for (int i = 0; i < todos.size(); i++) {
			output.println(todos.get(i));
		}
        printTodos(todos);
	}

    //takes user input and a todo list as parameters, removes items by name
    public static void removeItem(Scanner console, List<String> todos) {
        System.out.print("What is the name of the item you want to remove? ");
        String item = console.nextLine();

        for (int i = 0; i < todos.size(); i++) {
            if (item.equals(todos.get(i))) {
                todos.remove(i);
                printTodos(todos);
            } else {
                System.out.println("Item not on the list");
            }
        }
    }

}