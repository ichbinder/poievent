package de.htw_berlin.opentoken.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.htw_berlin.f4.ai.kbe.model.UserModel;
import de.htw_berlin.f4.ai.kbe.springdatarepository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean validateUser(Long userId) {
		// TODO Auto-generated method stub		
		return userRepository.exists(userId);
	}

	@Override
	public long createUser(String name, String firstname, String email) {
		// TODO Auto-generated method stub
		UserModel user = new UserModel(name, firstname, email);
		userRepository.saveAndFlush(user);
		
		return user.getUserId();
	}

	@Override
	public void setAdminRole(Long userId) {
		// TODO Auto-generated method stub
		UserModel user = userRepository.findOne(userId);
		user.setAdmin(true);
		userRepository.saveAndFlush(user);
	}

	@Override
	public boolean validateAdmin(Long userId) {
		// TODO Auto-generated method stub
		UserModel user = userRepository.findOne(userId);
		
		return user.getAdmin();
	}

	@Override
	public void removeAdmin(Long userId) {
		// TODO Auto-generated method stub
		UserModel user = userRepository.findOne(userId);
		user.setAdmin(false);
		userRepository.saveAndFlush(user);
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
