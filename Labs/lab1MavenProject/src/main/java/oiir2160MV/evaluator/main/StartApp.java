package oiir2160MV.evaluator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


import oiir2160MV.evaluator.controller.AppController;
import oiir2160MV.evaluator.exception.DuplicateIntrebareException;
import oiir2160MV.evaluator.exception.InputValidationFailedException;
import oiir2160MV.evaluator.exception.NotAbleToCreateStatisticsException;
import oiir2160MV.evaluator.exception.NotAbleToCreateTestException;
import oiir2160MV.evaluator.model.Intrebare;
import oiir2160MV.evaluator.model.Statistica;
import oiir2160MV.evaluator.model.Test;
import oiir2160MV.evaluator.util.InputValidation;

//functionalitati
//F01.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//F02.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//F03.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

    private static final Logger LOG = Logger.getLogger(StartApp.class.getName());

    private static final String file = "intrebari.txt";

    private static BufferedReader console;
    private static AppController appController;

    public static void main(String[] args) throws IOException {

        console = new BufferedReader(new InputStreamReader(System.in));

        appController = new AppController();
        appController.loadIntrebariFromFile(file);

        boolean activ = true;
        String optiune = null;

        while (activ) {

            System.out.println();
            System.out.println("1.Adauga intrebare");
            System.out.println("2.Creeaza test");
            System.out.println("3.Statistica");
            System.out.println("4.Exit");
            System.out.println();

            optiune = console.readLine();

            switch (optiune) {
                case "1":
                    Intrebare intrebareNoua;
                    String intrebare;
                    String varianta1;
                    String varianta2;
                    String varianta3;
                    String raspunsCorect;
                    String domeniu;
                    System.out.println("Introduceti intrebarea: ");
                    intrebare = console.readLine();
                    System.out.println("Introduceti raspuns 1: ");
                    varianta1 = console.readLine();
                    System.out.println("Introduceti raspuns 2: ");
                    varianta2 = console.readLine();
                    System.out.println("Introduceti raspuns 3: ");
                    varianta3 = console.readLine();
                    System.out.println("Introdueti raspuns corect: ");
                    raspunsCorect = console.readLine();
                    System.out.println("Introduceti domeniu : ");
                    domeniu = console.readLine();
                    try {
                        intrebareNoua = new Intrebare(intrebare, varianta1, varianta2, varianta3, raspunsCorect, domeniu);
                        appController.addNewIntrebare(intrebareNoua);
                    } catch (InputValidationFailedException ex) {
                        LOG.log(Level.SEVERE, "Intrebare incorect: " + ex.getMessage());
                    } catch (DuplicateIntrebareException ex) {
                        LOG.log(Level.SEVERE, "Intrebare duplicat : " + ex.getMessage());
                    }
                case "2":
                    try {
                        Test newTest = appController.createNewTest();
                        for (Intrebare intrebareCurenta : newTest.getIntrebari()) {
                            System.out.println(intrebareCurenta.getEnunt());
                            System.out.println(intrebareCurenta.getVarianta1());
                            System.out.println(intrebareCurenta.getVarianta2());
                            System.out.println(intrebareCurenta.getVarianta3());
                        }
                    } catch (NotAbleToCreateTestException e) {
                        LOG.log(Level.SEVERE, "Nu s-a putut crea testul :" + e.getMessage());
                    }
                    break;
                case "3":
                    Statistica statistica;
                    try {
                        statistica = appController.getStatistica();
                        System.out.println(statistica);
                    } catch (NotAbleToCreateStatisticsException e) {
                        LOG.log(Level.SEVERE, "Nu s-a putut crea statistica: " + e.getMessage());
                    }

                    break;
                case "4":
                    activ = false;
                    break;
                default:
                    break;
            }
        }

    }

}
