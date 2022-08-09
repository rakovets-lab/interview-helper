package by.rakovets.interview.data_migration;

import by.rakovets.interview.data_migration.database.InitSchema;
import by.rakovets.interview.data_migration.database.InitSchemaImpl;
import by.rakovets.interview.data_migration.exception.InterviewDataMigrationException;
import by.rakovets.interview.data_migration.service.QuestionService;

public class DataMigrationApplication {
       public static String fileName;

    public static void main(String[] args) {
        fileName = args[0];
        System.out.println(fileName);
        InitSchema initSchema = new InitSchemaImpl();
        QuestionService service = QuestionService.getInstance();
        try {
            initSchema.initSchemaForDataBase();
            service.saveQuestionAnswer();
        } catch (InterviewDataMigrationException e) {
            e.printStackTrace();
        }
    }
}
