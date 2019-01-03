package by.iba.crearec.service.impl;

import by.iba.crearec.model.Person;
import by.iba.crearec.service.ILoginService;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.Properties;

public class LoginService implements ILoginService {

	private static final Properties LDAP_CONNECTION_PROPERTIES = new Properties();

	static {
		LDAP_CONNECTION_PROPERTIES.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		LDAP_CONNECTION_PROPERTIES.put(Context.PROVIDER_URL, "ldap://localhost:389");
		LDAP_CONNECTION_PROPERTIES.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=example,dc=org");
		LDAP_CONNECTION_PROPERTIES.put(Context.SECURITY_CREDENTIALS, "admin");
	}

	@Override
	public boolean checkPerson(Person person) throws NamingException {
		InitialDirContext context = new InitialDirContext(LDAP_CONNECTION_PROPERTIES);
		SearchControls ctrls = new SearchControls();
		ctrls.setReturningAttributes(new String[] {"givenName", "userpassword"});
		ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		NamingEnumeration<SearchResult> answers = context.search("dc=example,dc=org", "(objectclass=posixAccount)", ctrls);

		while (answers.hasMoreElements()) {
			SearchResult result = answers.nextElement();
			Attribute givenName = result.getAttributes().get("givenName");
			if (person.getUsername() != null && givenName != null && person.getUsername().equals(givenName.get())) {
				Attribute userPassword = result.getAttributes().get("userpassword");
				if (person.getPassword() != null && userPassword != null && person.getPassword().equals(new String((byte[]) userPassword.get()))) {
					return true;
				}
			}
		}
		return false;
	}
}
