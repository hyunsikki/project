/**
 * 
 */
package com.hyunsikki.apps.ui.login;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hyunsikki.apps.common.controller.BaseController;

/**
 * @author Hyunsik Jung
 *
 */
@Controller
public class LoginController extends BaseController{

	@RequestMapping(value = "/Supervisor", method = RequestMethod.GET)
	public String adminMain() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    log.debug("Login User :: {}", name);
	    return "backoffice.backA";
	}

	@RequestMapping(value = "/auth/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		log.info("Admin Login page! {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "login.login";
	}


	@RequestMapping(value = "/auth/logoutSuccess", method = RequestMethod.GET)
	public String logout(Locale locale, Model model) {
		log.info("admin logout page! {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "logout.login";
	}
}
