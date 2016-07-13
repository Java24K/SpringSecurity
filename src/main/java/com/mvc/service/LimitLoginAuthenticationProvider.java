package com.mvc.service;

import java.util.Date;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.mvc.dao.UserDetailsDao;
import com.mvc.vo.UserAttempts;

public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

	UserDetailsDao userDetailsDao;

	UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {

			Authentication auth = super.authenticate(authentication);

			// if reach here, means login success, else exception will be thrown
			// reset the user_attempts
			userDetailsDao.resetFailAttempts(authentication.getName());

			return auth;

		} catch (BadCredentialsException e) {
				
			int attempts = userDetailsDao.updateFailAttempts(authentication.getName());
			String message = "";
			if(0 == attempts){
				message = "�û��������ڣ�";
			}else{
				message = "�������, �������Գ��Ե�¼" + (3 - attempts) + "�Σ�";
			}
			throw new BadCredentialsException(message);
			
		} catch (LockedException e) {

			String error = "";
			UserAttempts userAttempts = userDetailsDao.getUserAttempts(authentication.getName());
			if (userAttempts != null) {
				Date lastAttempts = userAttempts.getLastModified();
				error = "�˻��ѱ�����! <br><br>Username : " + authentication.getName() + "<br>Last Attempts : "
						+ lastAttempts;
			} else {
				error = e.getMessage();
			}

			throw new LockedException(error);
		}

	}

	public UserDetailsDao getUserDetailsDao() {
		return userDetailsDao;
	}

	public void setUserDetailsDao(UserDetailsDao userDetailsDao) {
		this.userDetailsDao = userDetailsDao;
	}
}