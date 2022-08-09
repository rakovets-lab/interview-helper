package by.rakovets.interview.data_migration.parser;

import by.rakovets.interview.data_migration.exception.InterviewDataMigrationException;

import java.util.List;

/**
 * Used for parsing files
 */
public interface FileParser {
    /**
     * Carries out parsing of files, the path to which is in the property file
     *
     * @param applicationPropertiesFileName - name properties file
     * @return String[] - array of lines from files
     * @throws InterviewDataMigrationException
     */
    List<String[]> parseFile(String applicationPropertiesFileName) throws InterviewDataMigrationException;
}
