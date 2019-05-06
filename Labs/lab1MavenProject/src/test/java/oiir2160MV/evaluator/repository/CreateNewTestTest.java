package oiir2160MV.evaluator.repository;

import oiir2160MV.evaluator.controller.AppController;
import oiir2160MV.evaluator.exception.DuplicateIntrebareException;
import oiir2160MV.evaluator.exception.InputValidationFailedException;
import oiir2160MV.evaluator.exception.NotAbleToCreateTestException;
import oiir2160MV.evaluator.model.Intrebare;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateNewTestTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createNewTest() {
    }

    private AppController appController;

    @Before
    public void setupController() {
        appController = new AppController();
    }

    @Test
    public void tc1() {
        try {
            this.appController.createNewTest();
            assert false;
        } catch (NotAbleToCreateTestException e) {
            assert true;
        }
    }

    @Test
    public void tc2() {
        try {
            this.appController.addNewIntrebare(new Intrebare("Intrebare1?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu1"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare2?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu1"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare3?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu2"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare4?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu2"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare5?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu3"));

            this.appController.createNewTest();
            assert false;
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        } catch (NotAbleToCreateTestException e) {
            assert true;
        }
    }

    @Test
    public void tc3() {
        try {
            this.appController.addNewIntrebare(new Intrebare("Intrebare1?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu1"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare2?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu2"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare3?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu3"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare4?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu4"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare5?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu5"));

            this.appController.createNewTest();
            assert this.appController.getAllTests().size() == 1;
        } catch (DuplicateIntrebareException | InputValidationFailedException | NotAbleToCreateTestException e) {
            assert false;
        }
    }

    @Test
    public void tc4() {
        try {
            this.appController.addNewIntrebare(new Intrebare("Intrebare1?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu1"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare2?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu2"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare3?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu3"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare4?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu4"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare5?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu5"));

            this.appController.createNewTest();
            assert this.appController.getAllTests().size() == 1;
        } catch (DuplicateIntrebareException | InputValidationFailedException | NotAbleToCreateTestException e) {
            assert false;
        }
    }
}