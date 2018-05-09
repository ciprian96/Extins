package Repository;

import Utils.Observable;

public interface IRepository <ID,E> extends Observable<E>{
	void save(E e) throws ExceptiaMea;
	E delete(ID id) throws ExceptiaMea;
	void updateObject(ID id,E e) throws ExceptiaMea;
	Iterable<E> getAll();
	boolean existObject(ID id);
	int size();
	E findObjectId(ID id);
	void saveData();
	void readData();
	//boolean existaObject(Object o);
}
