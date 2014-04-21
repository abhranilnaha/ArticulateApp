package edu.sjsu.articulate;

public class Client {
	/** Name of the client*/
    private String firstName;

    /** Last name of the client*/
    private String lastName;

    /** Identification of the client*/
    private String id;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
