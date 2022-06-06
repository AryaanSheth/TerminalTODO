package src;

import java.io.*;

// TODO: GENERATE .EXE FROM CODE

/**
 * It gets the current date and time and returns it as a string
 */
class dateTime {
    /**
     * It gets the current date and time and returns it as a string
     * 
     * @return The date and time
     */
    public static String getDateTime() {
        java.util.Date date = new java.util.Date(); // fetches the current date and time
        String dateTime = date.toString(); // makes a string that will hold the date and time
        return dateTime;
    }
}

/**
 * It creates a class called todo.
 */
class todo {

    /**
     * It asks the user for a todo item, gets the user input, adds the item to the
     * array, and formats the
     * item to be added to the file
     */
    public static void addItem() {
        // ask the user for a todo item
        System.out.println("\nEnter a todo item: ");
        // get the user input
        String item = System.console().readLine();

        try { // try to add the item to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter("data\\todo.txt", true));
            writer.write(String.format("%s %s\n", item, dateTime.getDateTime())); // formats the item to be added to the
                                                                                  // file
            System.out.println("\nNew Task Added");
            writer.close();
        } catch (Exception ex) { // catch any exceptions
            return; // return if there is an error
        }
    }

    /**
     * It reads the file line by line, and prints each line with a line number
     */
    public static void listItems() {
        try {
            // read the file line by line
            BufferedReader reader = new BufferedReader(new FileReader("data\\todo.txt"));
            String line = reader.readLine();
            int lineNumber = 1;
            while (line != null) { // while there are still lines to read
                System.out.println(String.format("%s. %s", lineNumber, line)); // print the line with a line number
                lineNumber++; // increment the line number
                line = reader.readLine();
            }
            reader.close();

        } catch (Exception ex) { // catch any exceptions
            return;
        }
    }

    /**
     * It reads the todo items from the file, and writes them to a temporary file,
     * except for the item that
     * the user wants to remove. Then it deletes the old todo file and renames the
     * temporary file to the
     * todo file
     */
    public static void removeItem() { // mark item done
        listItems();
        // ask the user for an item to remove
        System.out.println("Enter the number of the item to remove: ");
        // get the user input
        int item = Integer.parseInt(System.console().readLine());
        // make a temporary text file
        try {
            // generates temporary file to store the todo items
            BufferedWriter writer = new BufferedWriter(new FileWriter("data\\temp.txt"));
            BufferedReader reader = new BufferedReader(new FileReader("data\\todo.txt"));

            // read the todo items from the file
            String line = reader.readLine();
            int lineNumber = 1;

            while (line != null) { // while there are still items in the file
                if (lineNumber != item) {
                    writer.write(line + "\n");
                }
                line = reader.readLine();
                lineNumber++; // increment the line number
            }
            writer.close();
            reader.close();

            // deletes the old todo file and renames the temporary file to the todo file
            File oldFile = new File("data\\todo.txt");
            oldFile.delete();
            File newFile = new File("data\\temp.txt");
            newFile.renameTo(oldFile); // renames the temporary file to the todo file

        } catch (Exception ex) {
            return;
        }
    }

    /**
     * This function asks the user to choose an item from a list of options, and
     * returns the number of the
     * item chosen
     * 
     * @return The user input
     */
    public static int chooseItem() {
        // ask the user for an item to remove
        System.out.println(
                "\nEnter the number of the item to choose: \n1. add task \n2. view tasks \n3. mark done\n4. exit\n");
        // get the user input
        int item = Integer.parseInt(System.console().readLine());
        return item;
    }

    /**
     * The main function is a while loop that runs until the user chooses to exit
     */
    public static void main(String[] args) {
        while (true) {
            switch (chooseItem()) {
                case 1:
                    addItem();
                    break;
                case 2:
                    listItems();
                    break;
                case 3:
                    removeItem();
                    break;
                default:
                    System.out.println("Exiting...");
                    System.exit(0); // exit the program
            }

        }
    }
}