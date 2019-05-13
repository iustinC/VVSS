package oiir2160MV.evaluator.repository;

import oiir2160MV.evaluator.model.Intrebare;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class F03Test {

    private IntrebariRepository intrebariRepository;

    @Before
    public void setUp() throws Exception {
        intrebariRepository = new IntrebariRepository();

        intrebariRepository.addIntrebare(new Intrebare("Intrebare1?", "1) v1", "2) v2", "3) v3",
                "1", "Domeniu1"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void F03ValidTest() {
        int domeniu1 = intrebariRepository.getNumberOfIntrebariByDomain("Domeniu1");

        assertTrue(domeniu1 == 1);

    }

    @Test
    public void F03NonValidTest() {
        int numberOfQuestions = intrebariRepository.getNumberOfIntrebariByDomain("TeST");

        assertTrue( numberOfQuestions == 0);
    }
}