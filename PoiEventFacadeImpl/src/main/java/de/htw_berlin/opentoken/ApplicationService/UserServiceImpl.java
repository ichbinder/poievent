package de.htw_berlin.opentoken.ApplicationService;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.htw_berlin.opentoken.model.UserModel;
import de.htw_berlin.opentoken.springdatarepository.EventRepository;
import de.htw_berlin.opentoken.springdatarepository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class); 
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	EventRepository eventRepository;
	
	@Override
	@Transactional
	public boolean validateUser(Long userId) {
	
		if (userRepository.findOne(userId) != null)
		{	
			logger.info("User gefunden");
			return true;
		}
		else
		{
			logger.info("User nicht gefunden");
			return false;
		}
	}

	@Override
	@Transactional
	public long createUser(String name, String firstname, String email) {
	
		UserModel user = new UserModel(name, firstname, email);
		userRepository.saveAndFlush(user);
		
		logger.info("User erstellt");
		
		return user.getUserId();
	}

	@Override
	@Transactional
	public void setAdminRole(Long userId) {

		UserModel user = userRepository.findOne(userId);
		user.setAdmin(true);
		userRepository.saveAndFlush(user);
		logger.info("User zum Admin gemacht");
	}

	@Override
	@Transactional
	public boolean validateAdmin(Long userId) {

		UserModel user = userRepository.findOne(userId);
		
		logger.info("User auf Adminrechte überprüft");
		return user.getAdmin();
	}

	@Override
	@Transactional
	public void removeAdmin(Long userId) {

		UserModel user = userRepository.findOne(userId);
		user.setAdmin(false);
		userRepository.saveAndFlush(user);
		logger.info("User Adminrechte entzogen");
	}

	@Override
	@Transactional
	public Long getUserByEmail(String email) {

		UserModel user = userRepository.findByEmail(email);
		
		logger.info("User per Email gesucht");
		return user.getUserId();
	}

	@Override
	@Transactional
	public void deleteUserById(Long userId) {

		userRepository.delete(userRepository.findOne(userId));
		logger.info("User per UserID versucht zu löschen");
	}

	@Override
	@Transactional
	public void deleteUserByEmail(String email) {

		userRepository.delete(userRepository.findByEmail(email));
		logger.info("User per Email gelöscht");
	}

	@Override
	@Transactional
	public boolean validateEmail(String email) {

		boolean checkValue = false;
		if(userRepository.findByEmail(email) != null)
			checkValue = true;
		
		logger.info("User per Email gefunden");
		return checkValue;
	}

	@Override
	@Transactional
	public boolean checkEmail(String email) {
		boolean checkValue = false;
		
		if(userRepository.findByEmail(email) != null) 
			checkValue = true;
		
		logger.info("Überprüft ob User mit der Email vorhanden ist");
		return checkValue;
	}

	@Override
	public UserModel getUserById(Long userId) {

		logger.info("User per UserID gesucht");
		return userRepository.findOne(userId);
	}

}
