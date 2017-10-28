package hello.user;

import static org.junit.Assert.assertEquals;
import hello.UserController;
import hello.storage.StorageService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure =false)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    
    @MockBean
    private StorageService storageService;
    
    UserObject mockUser = new UserObject();
	
    String exampleUserJson = "{\"id\":\"123423\",\"fName\":\"John\",\"lName\":\"Matt\",\"email\":\"John.Smith@gmail.com\",\"pinCode\":\"123456\"}";

    @Test
    public void shouldCreateUser() throws Exception {
    	mockUser.setId("1234");
    	mockUser.setfName("John");
    	mockUser.setlName("Matt");
    	mockUser.setEmail("test@test.com");

    	
    	Mockito.when(userService.createUser(Mockito.any(UserObject.class))).thenReturn(mockUser);
    			
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/createUser")
				.accept(MediaType.APPLICATION_JSON).content(exampleUserJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		//result.getModelAndView().

		System.out.println(result.getResponse());
		
		MockHttpServletResponse response = result.getResponse();
		
		String resObject = response.getContentAsString();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		/*assertEquals("User created successfully",
				((ResponseEntity<ResponseObject>)(response.get).getResMsg());*/


    }
    
    @Test
    public void shoutFetchUser() throws Exception{
    	mockUser.setfName("John");
    	mockUser.setlName("Matt");
    	mockUser.setEmail("test@test.com");

    	Mockito.when(userService.fetchUser(Mockito.anyString())).thenReturn(mockUser);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/123423").accept(MediaType.APPLICATION_JSON);
    	
    	MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
    	
    	System.out.println(mvcResult.getResponse());
    	
    	assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    	//String expected = "";
    	
    	//JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
    	
    }

   
}
