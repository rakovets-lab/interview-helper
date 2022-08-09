package by.rakovets.interview.data_migration.dao;

import by.rakovets.interview.data_migration.exception.InterviewDataMigrationException;

/**
 * Working entities with database
 */public interface Dao<E> {
    /**
     * Saves Entity in database
     *
     * @param E Entity to save
     * @throws InterviewDataMigrationException
     */
    void save(E entity) throws InterviewDataMigrationException;
}
