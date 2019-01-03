package by.iba.crearec;

import by.iba.crearec.util.DataBaseUtils;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;

public class Test {

	public static void main(String[] args) {
		DataBaseUtils.liquibaseUpdateWithChangelog();
	}
}
