package com.group99.javabean;
/**
 * The entity class of bank account.
 * @author group 99
 *
 */
public class BankAccount {
	
	private String name;
	private String accountNum;
	private String accountPassword;
	private float balance;
	/**
	 * This is the constructor of BankAccount.
	 * @param name The owner name of current bank account.
	 * @param accountNum Account number.
	 * @param accountPassword Account password.
	 * @param balance Account balance.
	 */
	public BankAccount(String name, String accountNum, String accountPassword, float balance) {
		super();
		this.name = name;
		this.accountNum = accountNum;
		this.accountPassword = accountPassword;
		this.balance = balance;
	}

	public BankAccount() {
		
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	@Override
	public String toString() {
		return "BankAccount [name=" + name + ", accountNum=" + accountNum + ", accountPassword=" + accountPassword
				+ "]";
	}
	
}
