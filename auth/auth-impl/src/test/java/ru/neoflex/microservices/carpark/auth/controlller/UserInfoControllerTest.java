package ru.neoflex.microservices.carpark.auth.controlller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.auth.model.UserInfo;
import ru.neoflex.microservices.carpark.auth.service.UserInfoService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserInfoControllerTest {

    private static final String ANYLOGIN = "anylogin";
    private static final String PASS = "password";
    private static final long LOCATION_ID = 10L;
    private static final String USER_ROLE = "userRole";
    private static final long ID = 1L;
    private MockMvc mockMvc;

    @Mock
    private UserInfoService userInfoService;

    @InjectMocks
    private UserInfoController userInfoController;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userInfoController)
                .build();
    }

    @Test
    public void testGetByLogin() throws Exception {
        UserInfo userInfo = getDefaultUserInfo();
        when(userInfoService.getByLogin(ANYLOGIN)).thenReturn(userInfo);
        mockMvc.perform(get("/user/{login}", ANYLOGIN)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.login", is(ANYLOGIN)))
                .andExpect(jsonPath("$.password", is(PASS)))
                .andExpect(jsonPath("$.role", is(USER_ROLE)))
                .andExpect(jsonPath("$.locationId", is((int) LOCATION_ID)))
                .andExpect(jsonPath("$.id", is((int) ID)));
        verify(userInfoService, times(1)).getByLogin(ANYLOGIN);
        verifyNoMoreInteractions(userInfoService);

        mockMvc.perform(get("/user/{login}", "otherLogin")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.login").doesNotExist())
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.role").doesNotExist())
                .andExpect(jsonPath("$.locationId").doesNotExist());
    }

    private UserInfo getDefaultUserInfo() {
        UserInfo userInfoDef = new UserInfo();
        userInfoDef.setLogin(ANYLOGIN);
        userInfoDef.setPassword(PASS);
        userInfoDef.setLocationId(LOCATION_ID);
        userInfoDef.setRole(USER_ROLE);
        userInfoDef.setId(ID);
        return userInfoDef;
    }
}