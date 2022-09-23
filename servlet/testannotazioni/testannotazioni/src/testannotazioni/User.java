package testannotazioni;

public class User {
	private String name, surname, fullname;

	public String toString() {
		return fullname;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Msg("dammi il cognome")
	public void setSurname(String s) {
		surname = s;
	}

	@PostInit
	public void init() {
		fullname = name + " " + surname;
	}

}
