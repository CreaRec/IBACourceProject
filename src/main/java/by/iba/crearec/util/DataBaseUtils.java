package by.iba.crearec.util;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseUtils.class);

	public static boolean isLiquibaseUpdated = false;

	public static void liquibaseUpdateWithChangelog() {
		Connection connection = null;
		try {
			connection = getConnection();
			Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
			Liquibase liquibase = new Liquibase(ProjectConstants.CHANGELOG_XML, new ClassLoaderResourceAccessor(), database);
			liquibase.update("");
		} catch (LiquibaseException | SQLException | ClassNotFoundException e) {
			LOGGER.error("", e);
		} finally {
			isLiquibaseUpdated = true;
			if (connection != null) {
				closeConnection(connection);
			}
		}
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(ProjectConstants.DATABASE_URL, ProjectConstants.DATABASE_USER, ProjectConstants.DATABASE_PASSWORD);
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			LOGGER.error("", e);
		}
	}
}
