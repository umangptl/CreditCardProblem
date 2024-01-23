package org.example.file;

import org.example.card.Ctype.CreditCard;

import java.util.List;

public interface FileHandlingStrategy {
    List<CreditCard> readAndProcessFile(String filePath);
}