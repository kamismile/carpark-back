package ru.neoflex.microservices.carpark.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;
import ru.neoflex.microservices.carpark.auth.model.UserInfo;
import ru.neoflex.microservices.carpark.auth.repository.UserInfoRepository;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class UserInfoServiceImplTest {

    @Autowired
    private UserInfoRepository userInfoRepository = mock(UserInfoRepository.class);

    @Autowired
    private UserInfoService userInfoService;

    UserInfo userInfoAny, userInfoLogin, userInfoNull;

    @BeforeMethod
    public void setupMock() {
        reset(userInfoRepository);
        userInfoService = new UserInfoServiceImpl(userInfoRepository);
        userInfoAny = new UserInfo();
        userInfoLogin = new UserInfo();

        userInfoAny.setLogin("any");
        userInfoAny.setPassword("anypsss");
        userInfoLogin.setLogin("login");
        userInfoLogin.setPassword("password");
    }

    @Test
    public void testGetByLogin() {
        when(userInfoRepository.getByLogin(anyString())).thenReturn(userInfoAny);
        when(userInfoRepository.getByLogin("login")).thenReturn(userInfoLogin);
        when(userInfoRepository.getByLogin(null)).thenReturn(null);

        UserInfo userInfoGetAny = userInfoService.getByLogin("any");
        UserInfo userInfoGetLogin = userInfoService.getByLogin("login");
        UserInfo userInfoNull = userInfoService.getByLogin(null);

        assertNotNull(userInfoGetAny);
        assertNotNull(userInfoGetLogin);
        assertNull(userInfoNull);

        assertEquals(userInfoGetAny.getLogin(), "any");
        assertEquals(userInfoGetLogin.getLogin(), "login");

        verify(userInfoRepository, times(1)).getByLogin(eq("any"));
        verify(userInfoRepository, times(1)).getByLogin(eq("login"));
        verify(userInfoRepository, times(1)).getByLogin(eq(null));
    }

    @Test
    public void testAuthenticateUserByLoginAndPassword() {
        when(userInfoRepository.getByLoginAndPassword(anyString(), anyString())).thenReturn(userInfoAny);
        when(userInfoRepository.getByLoginAndPassword("login", "password")).thenReturn(userInfoLogin);
        when(userInfoRepository.getByLoginAndPassword(null, eq(anyString()))).thenReturn(null);
        when(userInfoRepository.getByLoginAndPassword(eq(anyString()), null)).thenReturn(null);

        UserInfo userInfoGetAny = userInfoRepository.getByLoginAndPassword("any", "any");
        UserInfo userInfoOK = userInfoRepository.getByLoginAndPassword("login", "password");
        UserInfo userInfoNullLogin = userInfoRepository.getByLoginAndPassword(null, "any");
        UserInfo userInfoNullPassword = userInfoRepository.getByLoginAndPassword("any", null);

        assertNotNull(userInfoGetAny);
        assertNotNull(userInfoOK);
        assertNull(userInfoNullLogin);
        assertNull(userInfoNullPassword);

        verify(userInfoRepository, times(1)).getByLoginAndPassword("any", "any");
        verify(userInfoRepository, times(1)).getByLoginAndPassword("login", "password");
        verify(userInfoRepository, times(1)).getByLoginAndPassword(null, "any");
        verify(userInfoRepository, times(1)).getByLoginAndPassword("any", null);
    }
}