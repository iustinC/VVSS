package oiir2160MV.evaluator.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;


import oiir2160MV.evaluator.exception.NotAbleToCreateStatisticsException;
import oiir2160MV.evaluator.exception.NotAbleToCreateTestException;
import oiir2160MV.evaluator.model.Intrebare;
import oiir2160MV.evaluator.exception.DuplicateIntrebareException;
import oiir2160MV.evaluator.model.Statistica;
import oiir2160MV.evaluator.model.Test;

public class IntrebariRepository {

    private static final Logger LOG = Logger.getLogger(IntrebariRepository.class.getName());

    private List<Intrebare> intrebari;

    public IntrebariRepository() {
        setIntrebari(new LinkedList<Intrebare>());
    }

    public void addIntrebare(Intrebare i) throws DuplicateIntrebareException {
        if (exists(i))
            throw new DuplicateIntrebareException("Intrebarea deja exista!");
        intrebari.add(i);
    }

    public boolean exists(Intrebare i) {
        for (Intrebare intrebare : intrebari)
            if (intrebare.equals(i))
                return true;
        return false;
    }

    public Intrebare pickRandomIntrebare() {
        Random random = new Random();
        return intrebari.get(random.nextInt(intrebari.size()));
    }

    public int getNumberOfDistinctDomains() {
        return getDistinctDomains().size();

    }

    public Set<String> getDistinctDomains() {
        Set<String> domains = new TreeSet<String>();
        for (Intrebare intrebre : intrebari)
            domains.add(intrebre.getDomeniu());
        return domains;
    }

    private List<Intrebare> getIntrebariByDomain(String questionDomain) {
        List<Intrebare> intrebariByDomain = new LinkedList<Intrebare>();
        for (Intrebare intrebare : intrebari) {
            if (intrebare.getDomeniu().equals(questionDomain)) {
                intrebariByDomain.add(intrebare);
            }
        }

        return intrebariByDomain;
    }

    public int getNumberOfIntrebariByDomain(String domain) {
        return getIntrebariByDomain(domain).size();
    }

    public List<Intrebare> loadIntrebariFromFile(String f) {

        List<Intrebare> intrebari = new LinkedList<>();

        String line = null;
        List<String> intrebareAux;
        Intrebare intrebare;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            line = br.readLine();
            while (line != null) {
                intrebareAux = new LinkedList<String>();
                while (!line.equals("##")) {
                    intrebareAux.add(line);
                    line = br.readLine();
                }
                intrebare = new Intrebare();
                intrebare.setEnunt(intrebareAux.get(0));
                intrebare.setVarianta1(intrebareAux.get(1));
                intrebare.setVarianta2(intrebareAux.get(2));
                intrebare.setVarianta3(intrebareAux.get(3));
                intrebare.setVariantaCorecta(intrebareAux.get(4));
                intrebare.setDomeniu(intrebareAux.get(5));
                intrebari.add(intrebare);
                line = br.readLine();
            }

        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error opening file. " + e.getMessage());
        }

        return intrebari;
    }

    public List<Intrebare> getIntrebari() {
        return intrebari;
    }

    public void setIntrebari(List<Intrebare> intrebari) {
        this.intrebari = intrebari;
    }

    public Statistica getStatistica() throws NotAbleToCreateStatisticsException{
        if(getIntrebari().isEmpty())
            throw new NotAbleToCreateStatisticsException("Repository-ul nu contine nicio intrebare!");

        Statistica statistica = new Statistica();
        for(String domeniu : getDistinctDomains()){
            statistica.add(domeniu, getNumberOfIntrebariByDomain(domeniu));
        }

        return statistica;
    }

    public Test createNewTest() throws NotAbleToCreateTestException {
        if(getIntrebari().size() < 5)
            throw new NotAbleToCreateTestException("Nu exista suficiente intrebari pentru crearea unui test!(5)");

        if(getNumberOfDistinctDomains() < 5)
            throw new NotAbleToCreateTestException("Nu exista suficiente domenii pentru crearea unui test!(5)");

        List<Intrebare> testIntrebari = new LinkedList<>();
        List<String> domenii = new LinkedList<>();
        Intrebare intrebare;
        Test test = new Test();

        while(testIntrebari.size() < 5){
            intrebare = pickRandomIntrebare();

            if(!testIntrebari.contains(intrebare) && !domenii.contains(intrebare.getDomeniu())){
                testIntrebari.add(intrebare);
                domenii.add(intrebare.getDomeniu());
            }

        }

        test.setIntrebari(testIntrebari);
        return test;
    }
}
