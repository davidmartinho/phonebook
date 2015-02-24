package pt.tecnico.phonebook.domain;

import java.util.Optional;

import pt.tecnico.phonebook.exception.NameAlreadyExistsException;

public class Person extends Person_Base {

	public Person(String name) {
		setName(name);
	}

	public boolean hasContact(String name) {
		return getContactByName(name).isPresent();
	}

	private Optional<Contact> getContactByName(String name) {
		return getContactSet().stream().filter(contact -> contact.getName().equals(name))
				.findAny();
	}

	@Override
	public void addContact(Contact contactToBeAdded)
			throws NameAlreadyExistsException {
		if (hasContact(contactToBeAdded.getName())) {
			throw new NameAlreadyExistsException(contactToBeAdded.getName());
		}
		super.addContact(contactToBeAdded);
	}

	public boolean matchesName(String token) {
		return getName().contains(token);
	}

	public boolean hasName(String name) {
		return getName().equals(name);
	}

	public boolean delete() {
		setPhoneBook(null);
		super.deleteDomainObject();
		return true;
	}

}
