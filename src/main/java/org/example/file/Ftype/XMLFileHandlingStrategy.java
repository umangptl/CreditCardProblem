package org.example.file.Ftype;

import org.example.card.CardFactory;
import org.example.card.CardFactoryImp;
import org.example.card.Ctype.CreditCard;
import org.example.file.FileHandlingStrategy;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XMLFileHandlingStrategy implements FileHandlingStrategy {
    @Override
    public List<CreditCard> readAndProcessFile(String filePath) {
        List<CreditCard> creditCards = new ArrayList<>();

        try {
            // Read the XML content from the file.
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new FileInputStream(inputFile)));

            doc.getDocumentElement().normalize();

            // Find all CARD elements in the XML.
            NodeList cardList = doc.getElementsByTagName("CARD");

            for (int i = 0; i < cardList.getLength(); i++) {
                Node cardNode = cardList.item(i);

                if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element cardElement = (Element) cardNode;
                    Element cardNumberElement = (Element) cardElement.getElementsByTagName("CARD_NUMBER").item(0);

                    if (cardNumberElement != null) {
                        String cardNumber = cardNumberElement.getTextContent();
                        String cardType = "";

                        // Use a CreditCardFactory to create CreditCard instances.
                        CardFactory cardFactory = new CardFactoryImp();
                        CreditCard creditCard = cardFactory.createCard(cardNumber, cardType);

                        creditCards.add(creditCard);
                    }
                }
            }

            // Write the processed data
            writeProcessedDataToFile(filePath, creditCards);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return creditCards;
    }

    private String generateOutputFilePath(String inputFilePath) {
        // Get the directory where the input file is located.
        File inputFile = new File(inputFilePath);
        String directory = inputFile.getParent();

        // Create a new output file name based on the input file name.
        String inputFileName = inputFile.getName();
        String outputFileName = inputFileName.replaceFirst(".xml$", "--output.xml");

        // Construct the new output file path.
        return directory + File.separator + outputFileName;
    }

    private void writeProcessedDataToFile(String inputFilePath, List<CreditCard> creditCards) {
        try {
            // Generate a new output file path.
            String outputFilePath = generateOutputFilePath(inputFilePath);

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element cardsElement = doc.createElement("CARDS");
            doc.appendChild(cardsElement);

            for (CreditCard creditCard : creditCards) {
                Element cardElement = doc.createElement("CARD");
                cardsElement.appendChild(cardElement);

                Element cardNumberElement = doc.createElement("CARD_NUMBER");
                cardNumberElement.appendChild(doc.createTextNode(creditCard.getCardNumber()));
                cardElement.appendChild(cardNumberElement);

                Element cardTypeElement = doc.createElement("CARD_TYPE");
                cardTypeElement.appendChild(doc.createTextNode(creditCard.getCardType()));
                cardElement.appendChild(cardTypeElement);
            }

            // Write the processed data to the new output file.
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(outputFilePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}