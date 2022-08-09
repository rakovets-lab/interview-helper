package by.rakovets.interview.data_migration.database;

import by.rakovets.interview.data_migration.exception.InterviewDataMigrationException;

/**
 *  Creating schema in the database
 */
public interface InitSchema {
    /**
     *  Initialize the schema in the database
     * @throws InterviewDataMigrationException
     */
    void initSchemaForDataBase() throws InterviewDataMigrationException;
}
