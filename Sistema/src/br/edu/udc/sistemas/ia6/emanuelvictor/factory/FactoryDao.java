package br.edu.udc.sistemas.ia6.emanuelvictor.factory;

import br.edu.udc.sistemas.ia6.emanuelvictor.dao.Dao;

public class FactoryDao {
	public static Dao createDao(Object obj) throws Exception{
		String daoClassName = "br.edu.udc.sistemas.ia6.emanuelvictor.dao.daoDatabase.Dao" + obj.getClass().getSimpleName(); 
		return (Dao) Class.forName(daoClassName).newInstance();
	}
}
