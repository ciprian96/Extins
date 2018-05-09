package model;
import Repository.ExceptiaMea;

public class ValidatorPost implements Validator<Post>{

	@Override
	public void validate(Post entity) throws ExceptiaMea {
		/* TODO Auto-generated method stub
		//Valideaza un obiect de tip Post astfel: Id- ul trebuie sa fie mai mare ca 0, denumirea nu trebuie sa lipseasca
		//si tipul postului trebuie sa fie fulltime sau partime
		daca una dintre conditii nu este indeplinita arunca exceptii*/
		
		String erori="";
		if(entity.getId()<=0)
			erori="Id post trebuie sa fie mai mare ca 0\n";
		
		if(entity.getDenumire().isEmpty())
			erori=erori+"Denumirea postului lipseste\n";
		
		if(entity.getTip().compareToIgnoreCase("fulltime")!=0 && entity.getTip().compareToIgnoreCase("partime")!=0)
			erori=erori+"Tipul postului trebuie sa fie fulltime sau partime\n";
		if(entity.getSalariu()<1250)
			erori=erori+"Salariul nu poate di mai mic de 1250 !!\n";
		
		if(erori.length()!=0)
			throw new ExceptiaMea(erori);
	}
}

