package com.lawyer.user.service;

public interface Users {
	public boolean isAccountActivated(String email);
	public boolean ActivateAccount(String email);
	public boolean DeactivateAccount(String email);
	public boolean authenticate(String email, String password);
	public boolean resestPassword(String email , String password);
}
