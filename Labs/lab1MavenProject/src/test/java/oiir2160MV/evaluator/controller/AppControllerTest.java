package oiir2160MV.evaluator.controller;

import oiir2160MV.evaluator.exception.DuplicateIntrebareException;
import oiir2160MV.evaluator.exception.InputValidationFailedException;
import oiir2160MV.evaluator.model.Intrebare;
import oiir2160MV.evaluator.util.InputValidation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppControllerTest {

    private AppController controller;

    @Before
    public void setUp() throws Exception {
        controller = new AppController();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void ECP_valid1() {
        Intrebare intrebare = new Intrebare();
        intrebare.setDomeniu("Masini");
        intrebare.setEnunt("Afara este cald?");
        intrebare.setVarianta1("1)Raspuns1");
        intrebare.setVarianta2("2)Raspuns2");
        intrebare.setVarianta3("3)Raspuns3");
        intrebare.setVariantaCorecta("2");
        try {
            controller.addNewIntrebare(intrebare);
            assertTrue(true);
        } catch (DuplicateIntrebareException e) {
            assertTrue(false);
        } catch (InputValidationFailedException e) {
            assertTrue(false);
        }
    }

    @Test
    public void ECP_valid2() {
        Intrebare intrebare = new Intrebare();
        intrebare.setDomeniu("Vreme");
        intrebare.setEnunt("Este cald afara?");
        intrebare.setVarianta1("1)Da");
        intrebare.setVarianta2("2)Nu");
        intrebare.setVarianta3("3)Poate");
        intrebare.setVariantaCorecta("2");
        try {
            controller.addNewIntrebare(intrebare);
            assertTrue(true);
        } catch (DuplicateIntrebareException e) {
            assertTrue(false);
        } catch (InputValidationFailedException e) {
            assertTrue(false);
        }
    }

    @Test
    public void ECP_invalid1() {
        Intrebare intrebare = new Intrebare();
        intrebare.setDomeniu("Vremeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        intrebare.setEnunt("Este cald afara?");
        intrebare.setVarianta1("1)Da");
        intrebare.setVarianta2("2)Nu");
        intrebare.setVarianta3("3)Poate");
        intrebare.setVariantaCorecta("2");
        try {
            controller.addNewIntrebare(intrebare);
            assertTrue(false);
        } catch (DuplicateIntrebareException e) {
            assertTrue(true);
        } catch (InputValidationFailedException e) {
            assertTrue(true);
        }
    }

    @Test
    public void ECP_invalid2() {
        Intrebare intrebare = new Intrebare();
        intrebare.setDomeniu("remeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        intrebare.setEnunt("Este cald afara?");
        intrebare.setVarianta1("1)Da");
        intrebare.setVarianta2("2)Nu");
        intrebare.setVarianta3("3)Poate");
        intrebare.setVariantaCorecta("2");
        try {
            controller.addNewIntrebare(intrebare);
            assertTrue(false);
        } catch (DuplicateIntrebareException e) {
            assertTrue(true);
        } catch (InputValidationFailedException e) {
            assertTrue(true);
        }
    }

    @Test
    public void BVA_valid1() {
        Intrebare intrebare = new Intrebare();
        intrebare.setDomeniu("M");
        intrebare.setEnunt("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa?");
        intrebare.setVarianta1("1)Raspuns1");
        intrebare.setVarianta2("2)Raspuns2");
        intrebare.setVarianta3("3)Raspuns3");
        intrebare.setVariantaCorecta("2");
        try {
            controller.addNewIntrebare(intrebare);
            assertTrue(true);
        } catch (DuplicateIntrebareException e) {
            assertTrue(false);
        } catch (InputValidationFailedException e) {
            assertTrue(false);
        }
    }

    @Test
    public void BVA_valid2() {
        Intrebare intrebare = new Intrebare();
        intrebare.setDomeniu("Maaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        intrebare.setEnunt("A?");
        intrebare.setVarianta1("1)Raspuns1");
        intrebare.setVarianta2("2)Raspuns2");
        intrebare.setVarianta3("3)Raspuns3");
        intrebare.setVariantaCorecta("2");
        try {
            controller.addNewIntrebare(intrebare);
            assertTrue(true);
        } catch (DuplicateIntrebareException e) {
            assertTrue(false);
        } catch (InputValidationFailedException e) {
            assertTrue(false);
        }
    }

    @Test
    public void BVA_invalid1() {
        Intrebare intrebare = new Intrebare();
        intrebare.setDomeniu("remeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        intrebare.setEnunt("Este cald afara?");
        intrebare.setVarianta1("1)Da");
        intrebare.setVarianta2("2)Nu");
        intrebare.setVarianta3("3)Poate");
        intrebare.setVariantaCorecta("2");
        try {
            assertFalse(controller.addNewIntrebare(intrebare));
        } catch (DuplicateIntrebareException e) {
            assertTrue(true);
        } catch (InputValidationFailedException e) {
            assertTrue(true);
        }
    }

    @Test
    public void BVA_invalid2() {
        Intrebare intrebare = new Intrebare();
        intrebare.setDomeniu("");
        intrebare.setEnunt("Este cald afara?");
        intrebare.setVarianta1("1)Da");
        intrebare.setVarianta2("2)Nu");
        intrebare.setVarianta3("3)Poate");
        intrebare.setVariantaCorecta("2");
        try {
            controller.addNewIntrebare(intrebare);
            assertTrue(false);
        } catch (DuplicateIntrebareException e) {
            assertTrue(true);
        } catch (InputValidationFailedException e) {
            assertTrue(true);
        }
    }

}