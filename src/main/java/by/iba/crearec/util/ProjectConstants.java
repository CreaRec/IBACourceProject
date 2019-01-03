package by.iba.crearec.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProjectConstants {

	public static final String CHANGELOG_XML = "db/changelog.xml";
	public static final String LOGIN_SESSION_NAME = "CourseUser";

	public static final String DATABASE_NAME = "postgres";
	public static final String DATABASE_USER = "postgres";
	public static final String DATABASE_PASSWORD = "postgres";
	public static final String DATABASE_URL = "jdbc:postgresql://localhost:5433/" + DATABASE_NAME;


}
