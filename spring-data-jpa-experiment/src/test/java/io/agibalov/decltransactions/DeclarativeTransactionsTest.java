package io.agibalov.decltransactions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Config.class)
@DirtiesContext
public class DeclarativeTransactionsTest {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Before
    public void deleteAllPersons() {
        personRepository.deleteAll();
    }

    @Test
    public void changesAreNotCommittedWhenTransactionalMethodThrows() {
        try {
            personService.createPerson(true);
            fail();
        } catch(OopsException e) {
        }

        assertEquals(0, personRepository.count());
    }

    @Test
    public void changesAreCommittedWhenTransactionalMethodDoesNotThrow() {
        personService.createPerson(false);
        assertEquals(1, personRepository.count());
    }
}
