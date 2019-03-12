package oiir2160MV.evaluator.controller;

import oiir2160MV.evaluator.exception.DuplicateIntrebareException;
import oiir2160MV.evaluator.exception.NotAbleToCreateStatisticsException;
import oiir2160MV.evaluator.exception.NotAbleToCreateTestException;
import oiir2160MV.evaluator.model.Intrebare;
import oiir2160MV.evaluator.model.Statistica;
import oiir2160MV.evaluator.model.Test;
import oiir2160MV.evaluator.repository.IntrebariRepository;

import java.util.LinkedList;
import java.util.List;

public class AppController {
	
	private IntrebariRepository intrebariRepository;
	
	public AppController() {
		intrebariRepository = new IntrebariRepository();
	}
	
	public Intrebare addNewIntrebare(Intrebare intrebare) throws DuplicateIntrebareException{
		
		intrebariRepository.addIntrebare(intrebare);
		
		return intrebare;
	}
	
	public boolean exists(Intrebare intrebare){
		return intrebariRepository.exists(intrebare);
	}
	
	public Test createNewTest() throws NotAbleToCreateTestException{
		return intrebariRepository.createNewTest();
	}
	
	public void loadIntrebariFromFile(String f){
		intrebariRepository.setIntrebari(intrebariRepository.loadIntrebariFromFile(f));
	}
	
	public Statistica getStatistica() throws NotAbleToCreateStatisticsException{
		return  intrebariRepository.getStatistica();
	}

}
