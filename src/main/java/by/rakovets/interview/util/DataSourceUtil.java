package by.rakovets.interview.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceUtil {
    private static DataSourceUtil INSTANCE = null;
    private static DataSource DATA_SOURCE_INSTANCE = null;
    private static final Object LOCK = new Object();

    public static DataSourceUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new DataSourceUtil();
                }
            }
        }
        return INSTANCE;
    }

    public DataSource getDataSource() {
        return DATA_SOURCE_INSTANCE;
    }

    private DataSourceUtil() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        try {
            InitialContext initialContext = new InitialContext();
            Context context = (Context) initialContext.lookup("java:comp/env");
            DATA_SOURCE_INSTANCE = (DataSource) context.lookup("jdbc/postgres");
            DATA_SOURCE_INSTANCE.getConnection();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
