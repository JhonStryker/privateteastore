package com.priavteTeaStore.zforTest;

import com.priavteTeaStore.DemoForTeaStoreApplication;
import com.priavteTeaStore.controller.RegisterController;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

/**
 * Created by Thoughtworks on 16/6/11.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoForTeaStoreApplication.class)
@WebAppConfiguration
public class TestRegistController {

    @InjectMocks
    @Resource
    RegisterController registController;

    MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registController).build();
    }
}
