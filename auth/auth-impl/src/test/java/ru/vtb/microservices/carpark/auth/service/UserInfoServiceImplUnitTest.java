package ru.vtb.microservices.carpark.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;
import ru.vtb.microservices.carpark.auth.model.UserInfo;
import ru.vtb.microservices.carpark.auth.repository.UserInfoRepository;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class UserInfoServiceImplUnitTest {

    private static final String ANY = "any";
    private static final String ANYPASS = "anypasss";
    private static final String LOGIN = "login";
    private static final String PASS = "password";
    @Autowired
    private UserInfoRepository userInfoRepository = mock(UserInfoRepository.class);

    @Autowired
    private UserInfoService userInfoService;

    private UserInfo userInfoAny;
    private UserInfo userInfoLogin;

    @BeforeMethod
    public void setupMock() {
        reset(userInfoRepository);
        userInfoService = new UserInfoServiceImpl(userInfoRepository);
        userInfoAny = new UserInfo();
        userInfoLogin = new UserInfo();

        userInfoAny.setLogin(ANY);
        userInfoAny.setPassword(ANYPASS);
        userInfoLogin.setLogin(LOGIN);
        userInfoLogin.setPassword(PASS);
    }

    @Test
    public void testAuthenticateUserByLoginAndPassword() {
        when(userInfoRepository.getByLoginAndPassword(anyString(), anyString())).thenReturn(userInfoAny);
        when(userInfoRepository.getByLoginAndPassword(LOGIN, PASS)).thenReturn(userInfoLogin);
        when(userInfoRepository.getByLoginAndPassword(null, eq(anyString()))).thenReturn(null);
        when(userInfoRepository.getByLoginAndPassword(eq(anyString()), null)).thenReturn(null);

        UserInfo userInfoGetAny = userInfoRepository.getByLoginAndPassword(ANY, ANYPASS);
        UserInfo userInfoOK = userInfoRepository.getByLoginAndPassword(LOGIN, PASS);
        UserInfo userInfoNullLogin = userInfoRepository.getByLoginAndPassword(null, ANYPASS);
        UserInfo userInfoNullPassword = userInfoRepository.getByLoginAndPassword(ANY, null);

        assertNotNull(userInfoGetAny);
        assertNotNull(userInfoOK);
        assertNull(userInfoNullLogin);
        assertNull(userInfoNullPassword);

        verify(userInfoRepository, times(1)).getByLoginAndPassword(ANY, ANYPASS);
        verify(userInfoRepository, times(1)).getByLoginAndPassword(LOGIN, PASS);
        verify(userInfoRepository, times(1)).getByLoginAndPassword(null, ANYPASS);
        verify(userInfoRepository, times(1)).getByLoginAndPassword(ANY, null);
    }
}