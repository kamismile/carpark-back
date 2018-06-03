import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.neoflex.microservices.carpark.dicts.DictsApplication;
import ru.neoflex.microservices.carpark.dicts.model.Reference;
import ru.neoflex.microservices.carpark.dicts.model.Rubric;
import ru.neoflex.microservices.carpark.dicts.repository.ReferenceRepository;
import ru.neoflex.microservices.carpark.dicts.sender.DictSender;
import ru.neoflex.microservices.carpark.dicts.service.ReferenceService;
import ru.neoflex.microservices.carpark.dicts.service.ReferenceServiceImpl;


@SpringBootTest(classes = {DictsApplication.class})
public class ReferenceServiceImplTest {

    @Autowired
    private ReferenceRepository referenceRepository = mock(ReferenceRepository.class);

    @Autowired
    DictSender sender;

    @Autowired
    private ReferenceService referenceService;

    @BeforeClass
    public void setupMock(){
        reset(referenceRepository);
        referenceService = new ReferenceServiceImpl(referenceRepository, sender);
    }

    @Test
    public void findByRubricTest() {
        when(referenceRepository.findByRubric(anyObject())).thenReturn(null);

    }
}
