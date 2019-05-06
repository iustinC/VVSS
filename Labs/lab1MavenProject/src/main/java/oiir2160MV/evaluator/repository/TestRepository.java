package oiir2160MV.evaluator.repository;

import oiir2160MV.evaluator.model.Intrebare;
import oiir2160MV.evaluator.model.Test;

import java.util.ArrayList;
import java.util.List;

public class TestRepository {

    private List<Test> tests = new ArrayList<>();

    public void addTest(final Test test) {
        tests.add(test);
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
