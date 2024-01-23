package org.example.file;

import org.example.file.Ftype.CSVFileHandlingStrategy;
import org.example.file.Ftype.JSONFileHandlingStrategy;
import org.example.file.Ftype.XMLFileHandlingStrategy;

public abstract class FileFormatDetector implements FileHandlingStrategy {
    public static FileHandlingStrategy detectFileFormat(String filePath) {
        String fileExtension = getFileExtension(filePath);

        if ("xml".equalsIgnoreCase(fileExtension)) {
            return new XMLFileHandlingStrategy();
        } else if ("json".equalsIgnoreCase(fileExtension)) {
            return new JSONFileHandlingStrategy();
        } else if ("csv".equalsIgnoreCase(fileExtension)) {
            return new CSVFileHandlingStrategy();
        } else {
            // Handle unsupported file format or content-based detection
            throw new UnsupportedOperationException("Unsupported file format or unable to detect format.");
        }
    }

    public static String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf(".");
        if (lastDotIndex != -1) {
            return filePath.substring(lastDotIndex + 1);
        }
        return ""; // Handle the case where there's no file extension
    }
}