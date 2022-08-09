package by.rakovets.interview.data_migration.parser;

import by.rakovets.interview.data_migration.exception.InterviewDataMigrationException;
import by.rakovets.interview.data_migration.util.PropertiesFileReader;
import by.rakovets.interview.data_migration.util.StringConstants;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CsvFileParser implements FileParser {
    private static final Object LOCK = new Object();
    StringConstants stringConstants = new StringConstants();
    private static CsvFileParser INSTANCE = null;

    private final PropertiesFileReader properties;

    public static CsvFileParser getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new CsvFileParser();
                }
            }
        }
        return INSTANCE;
    }

    public CsvFileParser() {
        this.properties = PropertiesFileReader.getInstance();
    }

    @Override
    public List<String[]> parseFile(String applicationPropertiesFileName) throws InterviewDataMigrationException {
        Set<Path> pathToFile = properties.getPathsToFilesForParsingFromApplicationPropertiesBySuffix(stringConstants.SUFFIX_CSV);
        List<String[]> readFileCsv = new ArrayList<>();
        for (Path path : pathToFile) {
            List<String[]> read = new ArrayList<>();
            try (CSVReader reader = new CSVReader(new FileReader(path.toString(), StandardCharsets.UTF_8))) {
                reader.readNext();
                read = reader.readAll();
                readFileCsv.addAll(read);
            } catch (IOException | CsvException e) {
                throw new InterviewDataMigrationException(stringConstants.FILE_NOT_RECOGNIZED);
            }
        }
        return readFileCsv;
    }
}
