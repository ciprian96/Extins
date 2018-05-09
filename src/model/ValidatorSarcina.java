package model;
//import model.Sarcina;

import Repository.ExceptiaMea;

public class ValidatorSarcina implements Validator<Sarcina> {

	@Override
	public void validate(Sarcina entity) throws ExceptiaMea {
		// TODO Auto-generated method stub
		// Valideaza un obiect de tip Sarcina astfel: id- ul trebuie sa fie mai mare ca 0 
		//si descrierea nu trebuie sa lipseasca
		//Daca una dintre conditii nu este indeplinita arunca exceptii
		String erori="";
		if(entity.getId()<1)
			erori = erori+"Id sarcina trebuie sa fie mai mare ca 0 !!\n";
		if(entity.getDescriere().isEmpty())
			erori =erori+"Descrierea sarcini nu poate lipsi !!";
		if(!(erori.isEmpty()))
			throw new ExceptiaMea(erori);
	}

}
