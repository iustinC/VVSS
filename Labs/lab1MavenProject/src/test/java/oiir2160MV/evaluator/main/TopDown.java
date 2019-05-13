package oiir2160MV.evaluator.main;

import oiir2160MV.evaluator.controller.AppController;
import oiir2160MV.evaluator.exception.DuplicateIntrebareException;
import oiir2160MV.evaluator.exception.InputValidationFailedException;
import oiir2160MV.evaluator.exception.NotAbleToCreateStatisticsException;
import oiir2160MV.evaluator.exception.NotAbleToCreateTestException;
import oiir2160MV.evaluator.model.Intrebare;
import oiir2160MV.evaluator.model.Statistica;
import oiir2160MV.evaluator.repository.IntrebariRepository;
import org.junit.Test;

public class TopDown {

    private AppController appController;

    @Test
    public void integrareA() {
        // P -> A

        this.appController = new AppController();

        Intrebare intrebareTest = new Intrebare();

        this.appController = new AppController();
        try {
            intrebareTest  = new Intrebare("Intrebare1?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu1");
        } catch (InputValidationFailedException e) {
            assert false;
        }

        try {
            this.appController.addNewIntrebare(intrebareTest);
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }

        assert this.appController.exists(intrebareTest);

    }

    @Test
    public void integrareB() {
        // P -> A -> B

        this.appController = new AppController();

        Intrebare intrebareTest = new Intrebare();

        try {
            intrebareTest  = new Intrebare("Intrebare122?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu6");
        } catch (InputValidationFailedException e) {
            assert false;
        }

        try {
            this.appController.addNewIntrebare(intrebareTest);
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }

        assert this.appController.exists(intrebareTest);


        try {
            this.appController.addNewIntrebare(new Intrebare("Intrebare1?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu1"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare2?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu2"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare3?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu3"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare4?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu4"));

            this.appController.createNewTest();
        } catch (DuplicateIntrebareException | InputValidationFailedException | NotAbleToCreateTestException e) {
            assert false;
        }

        assert this.appController.getAllTests().size() == 1;
    }

    @Test
    public void integrareC() {
        // P -> A -> B -> C

        Intrebare intrebareTest = new Intrebare();

        this.appController = new AppController();

        try {
            intrebareTest  = new Intrebare("Intrebare1123?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu15");
        } catch (InputValidationFailedException e) {
            assert false;
        }

        try {
            this.appController.addNewIntrebare(intrebareTest);
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }


        assert this.appController.exists(intrebareTest);


        try {
            this.appController.addNewIntrebare(new Intrebare("Intrebare1?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu1"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare2?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu2"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare3?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu3"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare4?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu4"));

            this.appController.createNewTest();

            assert this.appController.getAllTests().size() == 1;
        } catch (DuplicateIntrebareException | InputValidationFailedException | NotAbleToCreateTestException e) {
            assert false;
        }

        try {
            Statistica statistica = this.appController.getStatistica();

            assert statistica.getIntrebariDomenii().get("Domeniu1") == 1;
        } catch (NotAbleToCreateStatisticsException e) {
            assert false;
        }
    }

    @Test
    public void F01() {
        this.appController = new AppController();
        Intrebare intrebareTest = new Intrebare();

        this.appController = new AppController();

        try {
            intrebareTest  = new Intrebare("Intrebare1?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu1");
        } catch (InputValidationFailedException e) {
            assert false;
        }

        try {
            appController.addNewIntrebare(intrebareTest);
            assert appController.exists(intrebareTest);
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }
    }

    @Test
    public void F02() {
        this.appController = new AppController();
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
    public void F03() {
        IntrebariRepository intrebariRepository = new IntrebariRepository();

        Intrebare intrebare = null;
        try {
            intrebare = new Intrebare("Intrebare?", "1) a", "2) b", "3) c",
                    "1", "Domeniu");
        } catch (InputValidationFailedException e) {
            assert false;
        }
        try {
            intrebariRepository.addIntrebare(intrebare);
        } catch (DuplicateIntrebareException e) {
            assert false;
        }

        int domeniu = intrebariRepository.getNumberOfIntrebariByDomain("Domeniu");
        assert domeniu == 1;
    }
}