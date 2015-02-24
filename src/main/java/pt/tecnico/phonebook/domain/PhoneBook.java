package pt.tecnico.phonebook.domain;

import java.util.Optional;
import java.util.stream.Stream;

import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class PhoneBook extends PhoneBook_Base {

	private PhoneBook() {
		FenixFramework.getDomainRoot().setPhonebook(this);
	}

	public static PhoneBook getInstance() {
		PhoneBook pb = FenixFramework.getDomainRoot().getPhonebook();
		if (pb == null)
			pb = new PhoneBook();

		return pb;
	}

	private Optional<Person> getPersonByName(String name) {
		return getPersonSet().stream().filter(person -> person.hasName(name)).findAny();
	}

	public boolean hasPerson(String personName) {
		return getPersonByName(personName).isPresent();
	}

	public void removePerson(String personName)
			throws PersonDoesNotExistException {
		Optional<Person> personByName = getPersonByName(personName);
		personByName.ifPresent(Person::delete);
		personByName.orElseThrow(() -> new PersonDoesNotExistException(personName));
	}

	public void removePerson(Person person) {
		person.delete();
	}

	public Stream<Person> searchPerson(String token) {
		return getPersonSet().stream().filter(
				person -> person.matchesName(token));
	}

}
