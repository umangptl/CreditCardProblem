package org.example.file.Ftype;

import org.example.card.CardFactory;
import org.example.card.CardFactoryImp;
import org.example.card.Ctype.CreditCard;
import org.example.file.FileHandlingStrategy;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONFileHandlingStrategy implements FileHandlingStrategy {
    @Override
    public List<CreditCard> readAndProcessFile(String filePath) {
        List<CreditCard> creditCards = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filePath)) {
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONObject jsonObject = new JSONObject(jsonTokener);
            JSONArray cardsArray = jsonObject.getJSONArray("cards");

            for (int i = 0; i < cardsArray.length(); i++) {
                JSONObject cardObject = cardsArray.getJSONObject(i);
                if (cardObject.has("cardNumber")) {
                    String cardNumber = cardObject.getString("cardNumber");

                    // Use a CreditCardFactory to create CreditCard instances.
                    CardFactory cardFactory = new CardFactoryImp();
                    CreditCard creditCard = cardFactory.createCard(cardNumber, "");

                    creditCards.add(creditCard);
                }
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
        int lastSeparatorIndex = inputFilePath.lastIndexOf(File.separator);

        // Create a new output file name based on the input file name.
        String inputFileName = inputFilePath.substring(lastSeparatorIndex + 1);
        String outputFileName = inputFileName.replaceFirst(".json$", "--output.json");

        // Construct the new output file path.
        String directory = inputFilePath.substring(0, lastSeparatorIndex);
        return directory + File.separator + outputFileName;
    }

    private void writeProcessedDataToFile(String filePath, List<CreditCard> creditCards) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{\n");
            jsonBuilder.append("\t\"cards\": [\n");

            for (int i = 0; i < creditCards.size(); i++) {
                CreditCard creditCard = creditCards.get(i);

                jsonBuilder.append("\t\t{\n");
                jsonBuilder.append("\t\t\t\"cardNumber\": \"").append(creditCard.getCardNumber()).append("\",\n");
                jsonBuilder.append("\t\t\t\"cardType\": \"").append(creditCard.getCardType()).append("\"\n");
                jsonBuilder.append("\t\t}");

                if (i < creditCards.size() - 1) {
                    jsonBuilder.append(",");
                }

                jsonBuilder.append("\n");
            }

            jsonBuilder.append("\t]\n");
            jsonBuilder.append("}\n");

            // Write the processed data to the new output file.
            fileWriter.write(jsonBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}