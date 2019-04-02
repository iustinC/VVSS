package oiir2160MV.evaluator.controller;

import oiir2160MV.evaluator.exception.DuplicateIntrebareException;
import oiir2160MV.evaluator.exception.InputValidationFailedException;
import oiir2160MV.evaluator.exception.NotAbleToCreateStatisticsException;
import oiir2160MV.evaluator.exception.NotAbleToCreateTestException;
import oiir2160MV.evaluator.model.Intrebare;
import oiir2160MV.evaluator.model.Statistica;
import oiir2160MV.evaluator.model.Test;
import oiir2160MV.evaluator.repository.IntrebariRepository;
import oiir2160MV.evaluator.util.InputValidation;

import java.util.LinkedList;
import java.util.List;

public class AppController {
	
	private IntrebariRepository intrebariRepository;
	
	public AppController() {
		intrebariRepository = new IntrebariRepository();
	}
	
	public Intrebare addNewIntrebare(Intrebare intrebare) throws DuplicateIntrebareException, InputValidationFailedException {

		InputValidation.validateDomeniu(intrebare.getDomeniu());
		InputValidation.validateEnunt(intrebare.getEnunt());
		InputValidation.validateVarianta1(intrebare.getVarianta1());
		InputValidation.validateVarianta2(intrebare.getVarianta2());
		InputValidation.validateVarianta3(intrebare.getVarianta3());
		InputValidation.validateVariantaCorecta(intrebare.getVariantaCorecta());

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
