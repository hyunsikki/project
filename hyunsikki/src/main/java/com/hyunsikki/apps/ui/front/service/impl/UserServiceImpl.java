/**
 * 
 */
package com.hyunsikki.apps.ui.front.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyunsikki.apps.ui.front.common.service.impl.BaseServiceImpl;
import com.hyunsikki.apps.ui.front.mapper.UserMapper;
import com.hyunsikki.apps.ui.front.model.User;
import com.hyunsikki.apps.ui.front.service.UserService;

/**
 * @author Hyunsik Jung
 *
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	/* (non-Javadoc)
	 * @see com.hyunsikki.apps.ui.front.service.UserService#getUserList()
	 */
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		log.debug("userMapper :: {}", userMapper);
		List<User> userList = userMapper.getUserList();
		log.debug("userList Size :: {}",userList.size());
		return userList;
	}

}
