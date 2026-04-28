public class Person implements Comparable<Person>
{
	/***** Part 2: Inner Identity class *****/
	public class Identity {
		private String pronouns;
		private String background;

		public Identity(String pronouns, String background) {
			this.pronouns = pronouns;
			this.background = background;
		}

		public Identity() {
			this("Unknown", "Unknown");
		}

		public void setPronouns(String pronouns) {
			this.pronouns = pronouns;
		}

		public void setBackground(String background) {
			this.background = background;
		}

		public String getPronouns() {
			return pronouns;
		}

		public String getBackground() {
			return background;
		}

		@Override
		public String toString() {
			return "(" + pronouns + ") " + background;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Identity)) return false;
			Identity other = (Identity) obj;
			return pronouns.equals(other.pronouns) &&
				   background.equals(other.background);
		}
	}

	// CONSTANTS
	public static final String DEFAULT_NAME = "Jamie Doe";
	public static final int DEFAULT_PRIVILEGE = 100;

	// INSTANCE VARIABLES
	private String name;
	private Identity story;   // ✅ changed from String
	private int privilege;

	// CONSTRUCTORS
	public Person(String name, String pronouns, String background, int privilege) {
		this.story = new Identity(pronouns, background);
		this.setAll(name, privilege);
	}

	public Person() {
		this(DEFAULT_NAME, "Unknown", "Unknown", DEFAULT_PRIVILEGE);
	}

	public Person(Person original) {
		if(original == null) {
			throw new IllegalArgumentException("Cannot copy null object");
		}
		this.name = original.name;
		this.story = new Identity(
			original.story.getPronouns(),
			original.story.getBackground()
		);
		this.privilege = original.privilege;
	}

	// SETTERS
	public void setName(String name) {
		this.name = name;
	}

	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

	public void setPronouns(String pronouns) {
		this.story.setPronouns(pronouns);
	}

	public void setBackground(String background) {
		this.story.setBackground(background);
	}

	public void setAll(String name, int privilege) {
		this.name = name;
		this.privilege = privilege;
	}

	// GETTERS
	public String getName() {
		return name;
	}

	public Identity getStory() {
		return story;
	}

	public int getPrivilege() {
		return privilege;
	}

	// toString
	@Override
	public String toString()
	{
		return "My name is " + name + " and I identify as " + story +
				"\nAccording to this calculator I ended up with " + privilege + " estimated privilege points";
	}

	// equals
	@Override
	public boolean equals(Object other)
	{
		if (!(other instanceof Person)) return false;

		Person o = (Person) other;

		return name.equals(o.name) &&
			   story.equals(o.story) &&
			   privilege == o.privilege;
	}

	/***** Part 1: Comparable implementation *****/
	@Override
	public int compareTo(Person other) {
		return this.privilege - other.privilege;
	}
}