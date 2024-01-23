package org.example;

import org.example.card.Ctype.CreditCard;
import org.example.file.FileFormatDetector;
import org.example.file.FileHandlingStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {

    //Factory pattern for CreditCard
    //Strategy pattern for FileHandlingStrategy

    public static void main(String[] args) {
        String userHome = System.getProperty("user.home");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter path e.g. /Desktop/creditcards.json");
        System.out.print("Enter the file path: ");
        String filepath = scanner.nextLine(); // e.g. /Desktop/input1.json

        String filePathFull = userHome + filepath;
        System.out.println("File path: " + filePathFull);

        try {
            FileHandlingStrategy fileHandlingStrategy = FileFormatDetector.detectFileFormat(filePathFull);
            List<CreditCard> creditCards = fileHandlingStrategy.readAndProcessFile(filePathFull);

            // Additional processing or output
            System.out.println("Processing complete.");
            System.out.println("The file is at " + filePathFull + "--output");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

}