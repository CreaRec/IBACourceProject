package by.iba.crearec.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class TestModel {

	private String id;
	private String name;
	private String surname;
	private String age;

	public TestModel(String name, String surname, String age) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.surname = surname;
		this.age = age;
	}
}
