package model;
import Repository.ExceptiaMea;

public interface Validator<E> {
	void validate(E entity)throws ExceptiaMea;
}
