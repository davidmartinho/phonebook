package pt.tecnico.phonebook;

import pt.ist.fenixframework.Atomic;
import pt.tecnico.phonebook.domain.Contact;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.domain.PhoneBook;

public class PhonebookApplication {

	@Atomic
	public static void main(String[] args) {
		System.out.println("Welcome to the PhoneBook application!");

		PhoneBook pb = PhoneBook.getInstance();
		setupIfNeed(pb);

		for (Person person : pb.getPersonSet()) {
			System.out
					.println("The Contact book of " + person.getName() + " :");
			for (Contact contact : person.getContactSet()) {
				System.out.println("\t Name: " + contact.getName() + " phone: "
						+ contact.getPhoneNumber());
			}
		}

	}

	// setup the initial state if phonebook is empty
	private static void setupIfNeed(PhoneBook pb) {
		if (pb.getPersonSet().isEmpty())
			SetupDomain.populateDomain();
	}
}