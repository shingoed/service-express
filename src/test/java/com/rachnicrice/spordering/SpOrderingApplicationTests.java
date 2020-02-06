package com.rachnicrice.spordering;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class SpOrderingApplicationTests {

	@Autowired
	private	MockMvc mockMvc;

	@Test
	public void testSplashPage() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<a href=\"/\" class=\"navbar-appname\">Service Partner E-store</a>")));
	}


	@Test
	public void testLoginPage() throws Exception {
		this.mockMvc.perform(get("/login"))
				.andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	public void testAboutUsPage() throws Exception {
		this.mockMvc.perform(get("/about-us"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<script type=\"text/javascript\" src=\"/js/app.js\"></script>")));
	}

	@Test
	public void testContactUsPage() throws Exception {
		this.mockMvc.perform(get("/contactus"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<script type=\"text/javascript\" src=\"/js/app.js\"></script>")));
	}

	@Test
	public void testProfilePage() throws Exception {
		this.mockMvc.perform(get("/profile"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<script type=\"text/javascript\" src=\"/js/app.js\"></script>")));
	}

	@Test
	public void testMyCartPage() throws Exception {
		this.mockMvc.perform(get("/mycart"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<script type=\"text/javascript\" src=\"/js/app.js\"></script>")));
	}

	@Test
	public void testMyCartFindOnePage() throws Exception {
		this.mockMvc.perform(get("/mycart/findOne"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<script type=\"text/javascript\" src=\"/js/app.js\"></script>")));
	}

	@Test
	public void testProductsPage() throws Exception {
		this.mockMvc.perform(get("/products"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<script type=\"text/javascript\" src=\"/js/app.js\"></script>")));
	}

	@Test
	public void testFindOnePage() throws Exception {
		this.mockMvc.perform(get("/findOne"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<script type=\"text/javascript\" src=\"/js/app.js\"></script>")));
	}

	@Test
	public void testPopulateDatabasePage() throws Exception {
		this.mockMvc.perform(get("/populateDatabase"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<script type=\"text/javascript\" src=\"/js/app.js\"></script>")));
	}



}

