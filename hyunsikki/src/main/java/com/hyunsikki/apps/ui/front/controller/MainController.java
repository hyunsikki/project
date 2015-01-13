/**
 * 
 */
package com.hyunsikki.apps.ui.front.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyunsikki.apps.common.controller.BaseController;
import com.hyunsikki.apps.ui.front.model.User;
import com.hyunsikki.apps.ui.front.service.UserService;

/**
 * @author Hyunsik Jung
 *
 */
@Controller
@RequestMapping("/front")
public class MainController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String frontView(Locale locale, Model model){
		
		return "contents.frontA";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUserList(Locale locale, Model model){
		log.debug("getCountry :: {}", locale.getCountry());
		List<User> userList = userService.getUserList();
		log.debug("ListSize :: {}", userList.size());
		return userList;
	}

}
