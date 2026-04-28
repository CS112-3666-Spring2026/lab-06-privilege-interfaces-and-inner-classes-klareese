import java.util.Scanner;

public class Main
{
	public static final String[] STATEMENTS = {
		"English is my native language.",
		"My parents graduated college.",
		"I have never wondered where my next meal will come from.",
		"I have no disabilities.",
		"My work and school holidays coincide with the religious holidays I celebrate.",
		"I studied the culture and history of my ancestors in elementary school.",
		"I have never been bullied or been made fun of based on something I could not change (ie. race, ethnicity, sexual orientation, disabilities.)",
		"I have never been stopped by law enforcement due to mere suspicion as opposed to legitimate wrongdoing.",
		"I or my parents have inherited money or property.",
		"I am a US citizen.",
		"I feel safe going for a walk alone.",
		"I go by the same name I was given at birth.",
		"I am comfortable presenting my ID because it properly identifies me.",
		"My ancestors were not forced to come to the United States against their will to be enslaved.",
		"I have family or friends that can give me employment if I need it.",
		"I have never been told my natural hair looks dirty or unprofessional.",
		"I have gone to private school.",
		"I can easily find souvenirs with my name on them."
	};

	public static final int PTS_PER_ANSWER = 10,
		TOTAL_PTS_POSSIBLE = PTS_PER_ANSWER * STATEMENTS.length,
		MAX = Person.DEFAULT_PRIVILEGE + TOTAL_PTS_POSSIBLE,
		MIN = Person.DEFAULT_PRIVILEGE - TOTAL_PTS_POSSIBLE;

	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args)
	{
		// Preloaded people (UPDATED constructor)
		Person p1 = new Person("Amira", "she/her", "I am a Syrian refugee.", 40);
		Person p2 = new Person("D'Andra", "she/her", "I am an African-American trans woman.", -20);
		Person p3 = new Person("Jennifer", "she/her", "I am a New Yorker", 140);
		Person p4 = new Person("Pete", "he/him", "I am a guy from Pennsylvania", 200);

		Person self = new Person();
		Person[] people = {p1, p2, p3, p4, self};

		boolean done = false;
		int input;

		System.out.println("Welcome to the Privilege Calculator.\n");

		fillInfo(self);

		do {
			System.out.println("\n~~~Main Menu~~~\n");
			System.out.println("1. Take questionnaire");
			System.out.println("2. Check my estimate");
			System.out.println("3. Compare with others");
			System.out.println("4. Exit");
			System.out.print("Enter choice: ");

			input = keyboard.nextInt();
			keyboard.nextLine(); // fix newline issue

			switch(input)
			{
				case 1:
					self.setPrivilege(doPrivilegeQuestionnaire());
					System.out.println("Your score: " + self.getPrivilege());
					break;

				case 2:
					System.out.println(self);
					break;

				case 3:
					System.out.println("\n--- Comparison ---");

					for (int i = 0; i < people.length - 1; i++) {
						Person other = people[i];

						int result = self.compareTo(other);

						if (result > 0)
							System.out.println("You have MORE privilege than " + other.getName());
						else if (result < 0)
							System.out.println("You have LESS privilege than " + other.getName());
						else
							System.out.println("You have EQUAL privilege to " + other.getName());
					}
					break;

				case 4:
					done = true;
					break;

				default:
					System.out.println("Invalid choice.");
			}

		} while(!done);

		keyboard.close();
	}

	// UPDATED: includes pronouns + background
	public static void fillInfo(Person person){
		String name, pronouns, background;

		System.out.print("What is your name? ");
		name = keyboard.nextLine();

		System.out.print("Enter your pronouns (he/him, she/her, they/them, etc.): ");
		pronouns = keyboard.nextLine();

		System.out.print("Tell us about yourself: ");
		background = keyboard.nextLine();

		person.setName(name);
		person.setPronouns(pronouns);
		person.setBackground(background);
	}

	public static int doPrivilegeQuestionnaire() {
		int privilege = Person.DEFAULT_PRIVILEGE;

		for (String s : STATEMENTS) {
			System.out.println(s);
			System.out.print("1=True, 2=False: ");
			int choice = keyboard.nextInt();

			if (choice == 1) privilege += 10;
			else privilege -= 10;
		}

		return privilege;
	}
}