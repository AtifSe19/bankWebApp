
package com.redmath.bankWebApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class BankWebAppApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testContextLoads() {
		Assertions.assertNotNull(applicationContext);
		Assertions.assertNotNull(applicationContext.getBean(BankWebAppApplication.class));
	}

	@Test
	public void testLoginSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/login")
						.param("username", "admin")
						.param("password", "admin")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost:3000/"));
	}

	@Test
	public void testLoginFailure() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/login")
						.param("username", "atif")
						.param("password", "321")
						.with(SecurityMockMvcRequestPostProcessors.csrf()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.header().string("Location", "/login?error"));
	}
}