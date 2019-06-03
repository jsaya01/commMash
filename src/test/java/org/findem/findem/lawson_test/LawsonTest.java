package org.findem.findem.lawson_test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.*;

import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import cm.Application;
import cm.community.Community;
import cm.community.CommunityController;

@RunWith(SpringRunner.class)
@WebMvcTest(CommunityController.class)
@ContextConfiguration(classes=Application.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class LawsonTest {
	
//	@LocalServerPort
//	private int port;
//	
//	@Autowired
//	private TestRestTemplate restTemplate;
//	
//	@Test
//	public void CommunityGet() throws Exception {
//		Community comm = new Community("Racing Club", "path", "I love AI");
//		assertThat(restTemplate.getForEntity("http://localhost:" + port + "/community?name=AI Club", Community.class)).
//			contains()
//		
//	}
	
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
