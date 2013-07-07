package de.htw_berlin.opentoken.ApplicationService;

public interface UserService {

	boolean validateUser(Long userId);

	long createUser(String name, String firstname, String email);

	void setAdminRole(Long userId);

	boolean validateAdmin(Long userId);

	void removeAdmin(Long userId);

	Long getUserByEmail(String email);

	void deleteUserById(Long userId);

	void deleteUserByEmail(String email);

	boolean validateEmail(String email);

	boolean checkEmail(String email);

}
