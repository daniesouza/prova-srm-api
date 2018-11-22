package com.srm.provasrm.controller;

import com.srm.provasrm.ProvaSrmApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProvaSrmApplication.class)
public class LancamentoControllerTest {
	
	/** The web application context. */
	@Autowired
	private WebApplicationContext webApplicationContext;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Test ok.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_ok() throws Exception {
		mockMvc.perform(get("/lancamentos/")).andExpect(status().isOk());
	}

	/**
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testNotFound() throws Exception {
		mockMvc.perform(get("/lancamentos/12345")).andExpect(status().isNotFound());
	}

}
