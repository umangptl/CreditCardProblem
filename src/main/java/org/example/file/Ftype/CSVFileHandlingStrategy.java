package org.example.file.Ftype;

import org.example.card.CardFactory;
import org.example.card.CardFactoryImp;
import org.example.card.Ctype.CreditCard;
import org.example.file.FileHandlingStrategy;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileHandlingStrategy implements FileHandlingStrategy {
    @Override
    public List<CreditCard> readAndProcessFile(String filePath) {
        List<CreditCard> creditCards = new ArrayList<>();

        try (CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withHeader())) {
            for (org.apache.commons.csv.CSVRecord record : csvParser) {
                String cardNumber = record.get("cardNumber");

                // Use a CreditCardFactory to create CreditCard instances.
                CardFactory cardFactory = new CardFactoryImp();
                CreditCard creditCard = cardFactory.createCard(cardNumber, "");

                creditCards.add(creditCard);
            }

            // Generate a new output file path.
            String outputFilePath = generateOutputFilePath(filePath);

            // Write the processed data to the new output file.
            writeProcessedDataToFile(outputFilePath, creditCards);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return creditCards;
    }

    private String generateOutputFilePath(String inputFilePath) {
        // Get the directory where the input file is located.
        int lastSeparatorIndex = inputFilePath.lastIndexOf(System.getProperty("file.separator"));

        // Create a new output file name based on the input file name.
        String inputFileName = inputFilePath.substring(lastSeparatorIndex + 1);
        String outputFileName = inputFileName.replaceFirst(".csv$", "--output.csv");

        // Construct the new output file path.
        String directory = inputFilePath.substring(0, lastSeparatorIndex);
        return directory + System.getProperty("file.separator") + outputFileName;
    }

    private void writeProcessedDataToFile(String filePath, List<CreditCard> creditCards) {
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filePath), CSVFormat.DEFAULT.withHeader("cardNumber", "cardType"))) {
            for (CreditCard creditCard : creditCards) {
                csvPrinter.printRecord(creditCard.getCardNumber(), creditCard.getCardType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}