package com.sbisec.helios.ap.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.sbisec.helios.ap.common.exception.SystemErrorException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Csv file made into tools.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
public class CsvUtil {

    /**
     * Read csv file.
     * 
     * @param br         CSV
     * @param fileHeader CSVHeader
     * @param skipHeader CSVHeaderFlg
     */
    public static List<CSVRecord> readFile(BufferedReader br, boolean skipHeader, String[] fileHeader) {

        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(fileHeader)
                                                   .withSkipHeaderRecord(skipHeader);

        try (CSVParser csvFileParser = new CSVParser(br, csvFileFormat);) {
            return csvFileParser.getRecords();
        } catch (IOException e) {
            throw new SystemErrorException(e);
        }
    }
}
