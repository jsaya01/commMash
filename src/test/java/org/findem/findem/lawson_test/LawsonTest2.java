package org.findem.findem.lawson_test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import cm.Application;
import cm.user.UserController;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes=Application.class)
public class LawsonTest2 {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserController user;

	private void preformMvcGet(String url){
        try {
            mvc.perform(get(url)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@Test
	public void testCommunityGet()  {
		try {
			preformMvcGet("/user?username=KingNick");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUserGet() {
		try {
			preformMvcGet("/user?username=Speedy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
