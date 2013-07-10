package de.htw_berlin.opentoken.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.htw_berlin.opentoken.model.UserModel;
import de.htw_berlin.opentoken.springdatarepository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public boolean validateUser(Long userId) {
		// TODO Auto-generated method stub		
		return userRepository.exists(userId);
	}

	@Override
	@Transactional
	public long createUser(String name, String firstname, String email) {
		// TODO Auto-generated method stub
		UserModel user = new UserModel(name, firstname, email);
		userRepository.saveAndFlush(user);
		
		return user.getUserId();
	}

	@Override
	@Transactional
	public void setAdminRole(Long userId) {
		// TODO Auto-generated method stub
		UserModel user = userRepository.findOne(userId);
		user.setAdmin(true);
		userRepository.saveAndFlush(user);
	}

	@Override
	@Transactional
	public boolean validateAdmin(Long userId) {
		// TODO Auto-generated method stub
		UserModel user = userRepository.findOne(userId);
		
		return user.getAdmin();
	}

	@Override
	@Transactional
	public void removeAdmin(Long userId) {
		// TODO Auto-generated method stub
		UserModel user = userRepository.findOne(userId);
		user.setAdmin(false);
		userRepository.saveAndFlush(user);
	}

	@Override
	@Transactional
	public Long getUserByEmail(String email) {
		// TODO Auto-generated method stub
		UserModel user = userRepository.findByEmail(email);
		
		return user.getUserId();
	}

	@Override
	@Transactional
	public void deleteUserById(Long userId) {
		// TODO Auto-generated method stub
		userRepository.delete(userRepository.findOne(userId));
	}

	@Override
	@Transactional
	public void deleteUserByEmail(String email) {
		// TODO Auto-generated method stub
		userRepository.delete(userRepository.findByEmail(email));
	}

	@Override
	@Transactional
	public boolean validateEmail(String email) {
		// TODO Auto-generated method stub
		boolean checkValue = false;
		if(userRepository.findByEmail(email) != null)
			checkValue = true;
		return checkValue;
	}

	@Override
	@Transactional
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		boolean checkValue = false;
		
		if(userRepository.findByEmail(email) != null) 
			checkValue = true;
		
		return checkValue;
	}

	@Override
	public UserModel getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findOne(userId);
	}
}
