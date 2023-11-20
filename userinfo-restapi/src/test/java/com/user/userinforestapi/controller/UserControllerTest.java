package com.user.userinforestapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.usercommon.web.RestResult;
import com.user.userinforestapi.UserinfoRestapiApplication;

import com.user.usercommon.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserinfoRestapiApplication.class})
@WebAppConfiguration
public class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc  mockMvc;


    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        System.out.println("init mock");
    }
    @Test
    public void testGetUserById() throws Exception {
         // get /user/get/17
         MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/get/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

         // get response user
         String responseBody = mvcResult.getResponse().getContentAsString();

         RestResult result = new ObjectMapper().readValue(responseBody, RestResult.class);
         System.out.println(result.getData().toString());
         User user = new ObjectMapper().convertValue(result.getData(),User.class);
        // check  result
        Assert.assertEquals(1L, user.getId().longValue());
        Assert.assertEquals("mmmccccc", user.getName());
    }
    @Test
    public void testInsert() throws Exception {
        // post /user/insert
        String userjson="{\"name\":\"mmmcx\"}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/insert").content( userjson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // get response user
        String responseBody = mvcResult.getResponse().getContentAsString();

        RestResult result = new ObjectMapper().readValue(responseBody, RestResult.class);

        User user = new ObjectMapper().convertValue(result.getData(),User.class);
        // check  result
        // check  result
        Assert.assertEquals(200, result.getCode());
    }
    @Test
    public void testUpdate() throws Exception {
        // post /user/update
        String userjson="{\"id\":\"1\",\"name\":\"x\"}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/update").content(userjson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        // get response user
        String responseBody = mvcResult.getResponse().getContentAsString();

        RestResult result = new ObjectMapper().readValue(responseBody, RestResult.class);

        // check  result
        Assert.assertEquals(200, result.getCode());

    }
}
