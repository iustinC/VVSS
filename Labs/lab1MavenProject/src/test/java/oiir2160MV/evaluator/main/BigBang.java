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

import static org.junit.Assert.assertTrue;

public class BigBang {
    private AppController appController;

    @Test
    public void integrareP() {
        // P -> A -> B -> C
        this.appController = new AppController();

        try {
            this.appController.addNewIntrebare(new Intrebare("Intrebare?", "1) a", "2) b", "3) c",
                    "1", "Domeniu"));

            assert this.appController.exists(new Intrebare("Intrebare?", "1) a", "2) b",
                    "3) c","1", "Domeniu"));
        } catch (DuplicateIntrebareException | InputValidationFailedException e) {
            assert false;
        }

        try {
            this.appController.addNewIntrebare(new Intrebare("Intrebare2?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu2"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare3?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu3"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare4?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu4"));
            this.appController.addNewIntrebare(new Intrebare("Intrebare5?", "1) v1", "2) v2", "3) v3",
                    "1", "Domeniu5"));

            assert this.appController.createNewTest().getIntrebari().size() == 5;
        } catch (DuplicateIntrebareException | InputValidationFailedException | NotAbleToCreateTestException e) {
            assert false;
        }

        try {
            Statistica statistica = this.appController.getStatistica();

            assert statistica.getIntrebariDomenii().get("Domeniu") == 1;
            assert statistica.getIntrebariDomenii().get("Domeniu2") == 1;
            assert statistica.getIntrebariDomenii().get("Domeniu3") == 1;
            assert statistica.getIntrebariDomenii().get("Domeniu4") == 1;
            assert statistica.getIntrebariDomenii().get("Domeniu5") == 1;
        } catch (NotAbleToCreateStatisticsException e) {
            assert false;
        }
    }

    @Test
    public void F01() {
        this.appController = new AppController();
        try {
            boolean newIntrebare = appController.addNewIntrebare(new Intrebare("Enunt?", "1) R1", "2) R2","3) R3",
                    "1", "Domeniu"));
            assertTrue(newIntrebare);
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