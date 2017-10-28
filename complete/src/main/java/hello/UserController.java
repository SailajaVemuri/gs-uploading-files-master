package hello;

import hello.user.ResponseObject;
import hello.user.UserObject;
import hello.user.UserService;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}
	
	@Autowired
	 ServletContext context;
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<?> fetchUser(@PathVariable String userId){
		
		UserObject user = userService.fetchUser(userId);
		if(user != null)		
			return new ResponseEntity<UserObject>(user, HttpStatus.OK);
		else {
			ResponseObject resObj = new ResponseObject();
			resObj.setResMsg("Does not exist");
			resObj.setUserId(userId);
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.OK);
		}
			
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<ResponseObject> createUser(@RequestBody UserObject user){
		
		UserObject userCreated = userService.createUser(user);
		ResponseObject resObj;
		if(userCreated != null){
			resObj = new ResponseObject();
			resObj.setResMsg("User created successfully");
			resObj.setUserId(userCreated.getId());
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.CREATED);
		}else{
			resObj = new ResponseObject();
			resObj.setResMsg("User already exists");
			resObj.setUserId(user.getId());
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.BAD_REQUEST);
		}
				
	}
	
	@PostMapping("/updateUser")
	public ResponseEntity<ResponseObject> updateUser(@RequestBody UserObject user, RedirectAttributes redirectAttributes){
		
		UserObject userUpdated = userService.updateUser(user);
		ResponseObject resObj;
		if(userUpdated != null){
			resObj = new ResponseObject();
			resObj.setResMsg("User updated successfully");
			resObj.setUserId(userUpdated.getId());
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.CREATED);
		}else
		{
			resObj = new ResponseObject();
			resObj.setResMsg("User does not exist");
			resObj.setUserId(user.getId());
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ResponseObject> deleteUser(@PathVariable String userId, RedirectAttributes redirectAttributes){
		Boolean res = userService.deleteUser(userId);
		if(res == Boolean.TRUE){
			ResponseObject resObj = new ResponseObject();
			resObj.setResMsg("User deleted successfully");
			resObj.setUserId(userId);
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.OK);
		}else
		{
			ResponseObject resObj = new ResponseObject();
			resObj.setResMsg("User does not exist");
			resObj.setUserId(userId);
			return new ResponseEntity<ResponseObject>(resObj, HttpStatus.BAD_REQUEST);
		}
		
		
			
		
	}

}
