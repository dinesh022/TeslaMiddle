package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.FriendDao;
import com.niit.model.ErrorClazz;
import com.niit.model.User;

@Controller
public class FriendController {
	@Autowired
	private FriendDao friendDao;
	@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?> suggestedUsers(HttpSession session) {
		String email=(String)session.getAttribute("loginId");
		if(email==null) {
			ErrorClazz error=new ErrorClazz(5,"Unauthorized access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		
	}
		List<User> suggestedUsers=friendDao.suggestedUsers(email);
		return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
	}

}