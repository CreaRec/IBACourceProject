package by.iba.crearec.controller;

import by.iba.crearec.model.Person;
import by.iba.crearec.service.ILoginService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class LoginController {

	@Inject
	private ILoginService loginService;

	public boolean checkPerson(Person person) throws NamingException {
		return loginService.checkPerson(person);
	}
}
