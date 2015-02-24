package pt.tecnico.phonebook.domain;

import java.util.Optional;
import java.util.stream.Stream;

import pt.ist.fenixframework.Atomic;
import pt.ist.fenixframework.Atomic.TxMode;
import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.phonebook.exception.PersonDoesNotExistException;

public class PhoneBook extends PhoneBook_Base {

	private PhoneBook() {
		FenixFramework.getDomainRoot().setPhonebook(this);
	}

	public static PhoneBook getInstance() {
    	if (FenixFramework.getDomainRoot().getPhonebook() == null) {
			return initialize();
		}
    	return FenixFramework.getDomainRoot().getPhonebook();
	}

    @Atomic(mode = TxMode.WRITE)
    private static PhoneBook initialize() {
    	if (FenixFramework.getDomainRoot().getPhonebook() == null) {
            return new PhoneBook();
        }
        return FenixFramework.getDomainRoot().getPhonebook();
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
