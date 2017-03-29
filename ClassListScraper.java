import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;

public class ClassListScraper {
    private static final String REGEX = "onMouseOver=\"window.status='Student Information';  return true\" onFocus=\"window.status='Student Information';  return true\" onMouseOut=\"window.status='';  return true\"onBlur=\"window.status='';  return true\">";
    public static void main(String[] args) {
        if(args.length == 0) {
            System.err.println("ERROR: Please pass in an html file with the class list download from Banner.");
            return;
        }

        String fileName = args[0];
        File file = new File(fileName);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch(FileNotFoundException e) {
            System.err.println("ERROR: File not found.");
            return;
        }

        System.out.println("Class list:");
        try{
            PrintWriter writer = new PrintWriter("classList.txt", "UTF-8");
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int index = line.indexOf(REGEX);
                if(index != -1) {
                    String name = line.substring(index + REGEX.length(), line.indexOf("<", index + REGEX.length() + 1));
                    writer.println(name);
                    System.out.println(name);
                }
            }
            writer.close();
        } catch (IOException e) {
           e.printStackTrace();
        }

        System.out.println();
        System.out.println("List of names written to classList.txt");
    }
}
