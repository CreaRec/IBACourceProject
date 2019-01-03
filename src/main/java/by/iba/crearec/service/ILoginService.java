package by.iba.crearec.service;

import by.iba.crearec.model.Person;

import javax.naming.NamingException;

public interface ILoginService {

	boolean checkPerson(Person person) throws NamingException;

}
