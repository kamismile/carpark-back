package ru.neoflex.microservices.carpark.employees.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;
import ru.neoflex.microservices.carpark.auth.model.UserInfo;
import ru.neoflex.microservices.carpark.employees.repository.UserInfoRepository;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class UserInfoServiceImplTest {

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
    public void testGetByLogin() {
        when(userInfoRepository.getByLogin(anyString())).thenReturn(userInfoAny);
        when(userInfoRepository.getByLogin(LOGIN)).thenReturn(userInfoLogin);
        when(userInfoRepository.getByLogin(null)).thenReturn(null);

        UserInfo userInfoGetAny = userInfoService.getByLogin(ANY);
        UserInfo userInfoGetLogin = userInfoService.getByLogin(LOGIN);
        UserInfo userInfoGetNull = userInfoService.getByLogin(null);

        assertNotNull(userInfoGetAny);
        assertNotNull(userInfoGetLogin);
        assertNull(userInfoGetNull);

        assertEquals(userInfoGetAny.getLogin(), ANY);
        assertEquals(userInfoGetLogin.getLogin(), LOGIN);

        verify(userInfoRepository, times(1)).getByLogin(eq(ANY));
        verify(userInfoRepository, times(1)).getByLogin(eq(LOGIN));
        verify(userInfoRepository, times(1)).getByLogin(eq(null));
    }
}