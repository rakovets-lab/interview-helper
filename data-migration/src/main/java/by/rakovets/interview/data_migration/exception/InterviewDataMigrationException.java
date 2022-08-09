package by.rakovets.interview.data_migration.exception;

/**
 * Class for handling project errors
 */
public class InterviewDataMigrationException extends Exception {

    public InterviewDataMigrationException(String message) {
        super(message);
    }

    public InterviewDataMigrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
