package pt.tecnico.phonebook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.ist.fenixframework.Atomic;
import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;

public class SetupDomain {
	
	private static final Logger logger = LoggerFactory.getLogger(SetupDomain.class);
	
	@Atomic
	public static void main(String[] args) {
		populateDomain();
	}

	static void populateDomain() {
		PhoneBook pb = PhoneBook.getInstance();

		Person person = new Person("Manel");
		pb.addPerson(person);
		person.addContact(new Contact("SOS", 112));
		Contact c = new Contact("IST", 214315112);
		c.setPerson(person);

		try {
			c = new Contact("IST", 214315112);
			c.setPerson(person);
			logger.error("Error! Business rule violated!");
		} catch (pt.tecnico.phonebook.exception.NameAlreadyExistsException nae) {
			System.out
					.println("Could not add two equals contacts to the same person");
		}
	}
}
