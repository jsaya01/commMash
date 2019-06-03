package org.findem.findem.lawson_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import cm.Application;
import cm.community.CommunityController;

@RunWith(SpringRunner.class)
@WebMvcTest(CommunityController.class)
@ContextConfiguration(classes=Application.class)
public class LawsonTest {
	
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CommunityController community;
	
	@Test
	public void testCommunityGet()  throws Exception {
		
		mvc.perform(get("/community?name=Racing Club")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testUserGet()  throws Exception {
		
		mvc.perform(get("/community?name=AI Club")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
