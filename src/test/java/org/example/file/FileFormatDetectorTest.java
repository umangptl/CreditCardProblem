package org.example.file;

import org.example.file.Ftype.CSVFileHandlingStrategy;
import org.example.file.Ftype.JSONFileHandlingStrategy;
import org.example.file.Ftype.XMLFileHandlingStrategy;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileFormatDetectorTest {

    @Test
    public void testDetectFileFormatForXML() {
        String filePath = "example.xml";
        FileHandlingStrategy strategy = FileFormatDetector.detectFileFormat(filePath);
        assertTrue(strategy instanceof XMLFileHandlingStrategy);
    }

    @Test
    public void testDetectFileFormatForJSON() {
        String filePath = "example.json";
        FileHandlingStrategy strategy = FileFormatDetector.detectFileFormat(filePath);
        assertTrue(strategy instanceof JSONFileHandlingStrategy);
    }

    @Test
    public void testDetectFileFormatForCSV() {
        String filePath = "example.csv";
        FileHandlingStrategy strategy = FileFormatDetector.detectFileFormat(filePath);
        assertTrue(strategy instanceof CSVFileHandlingStrategy);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDetectFileFormatForUnsupportedFormat() {
        String filePath = "example.txt";
        FileFormatDetector.detectFileFormat(filePath);
    }

    @Test
    public void testGetFileExtension() {
        assertEquals("xml", FileFormatDetector.getFileExtension("example.xml"));
        assertEquals("json", FileFormatDetector.getFileExtension("example.json"));
        assertEquals("csv", FileFormatDetector.getFileExtension("example.csv"));
        assertEquals("", FileFormatDetector.getFileExtension("example"));
        assertEquals("", FileFormatDetector.getFileExtension(""));
    }
}

