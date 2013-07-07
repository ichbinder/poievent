package de.htw_berlin.opentoken.ApplicationService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public boolean validateUser(Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long createUser(String name, String firstname, String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAdminRole(Long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateAdmin(Long userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeAdmin(Long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(Long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserByEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean validateEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
