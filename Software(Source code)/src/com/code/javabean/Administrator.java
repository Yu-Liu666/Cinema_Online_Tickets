package com.group99.javabean;
/**
 * The entity class of administrator.
 * @author group 99
 *
 */
public class Administrator {

	private String id;
	private String password;
	
	public Administrator() {
	}
	/**
	 * This is the constructor of Administrator.
	 * @param id ID of administrator.
	 * @param password Password of administrator.
	 */
	public Administrator(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", password=" + password + "]";
	}
	
}
