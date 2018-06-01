import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.dicts.DictsApplication;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;
import ru.neoflex.microservices.carpark.dicts.repository.RubricRepository;
import ru.neoflex.microservices.carpark.dicts.service.RubricService;
import ru.neoflex.microservices.carpark.dicts.service.RubricServiceImpl;

@SpringBootTest(classes = {DictsApplication.class})
public class RubricServiceImplTest {

    @Autowired
    private RubricRepository rubricRepository = mock(RubricRepository.class);

    @Autowired
    private RubricService rubricService;

    @BeforeClass
    public void setupMock(){
        reset(rubricRepository);
        rubricService = new RubricServiceImpl(rubricRepository);
    }

    @Test
    public void findByCodeTest() {
        when(rubricRepository.findByCode(anyString())).thenReturn(new Rubric());
        when(rubricRepository.findByCode("noCode")).thenReturn(null);
        when(rubricRepository.findByCode("")).thenReturn(null);
        when(rubricRepository.findByCode(null)).thenReturn(null);
        Rubric userInfoAny = rubricService.findByCode("Code");
        Rubric userInfoNoLogin = rubricService.findByCode("noCode");
        Rubric userInfoEmpty= rubricService.findByCode("");
        Rubric userInfoNull = rubricService.findByCode(null);
        assertNull(userInfoNoLogin);
        assertNotNull(userInfoAny);
        assertNull(userInfoEmpty);
        assertNull(userInfoNull);
        verify(rubricRepository, times(1)).findByCode(eq("noCode"));
        verify(rubricRepository, times(1)).findByCode(eq("Code"));
        verify(rubricRepository, times(1)).findByCode(eq(""));
        verify(rubricRepository, times(1)).findByCode(eq(null));
    }
}
