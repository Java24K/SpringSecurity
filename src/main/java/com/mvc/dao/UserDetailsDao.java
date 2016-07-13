package com.mvc.dao;

import com.mvc.vo.UserAttempts;

public interface UserDetailsDao {

	int updateFailAttempts(String username);

	void resetFailAttempts(String username);

	UserAttempts getUserAttempts(String username);

}