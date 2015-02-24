package pt.tecnico.phonebook.exception;

public class PersonDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	private String personName;

	public PersonDoesNotExistException(String personName) {
		this.personName = personName;
	}

	public String getPersonName() {
		return personName;
	}
}
