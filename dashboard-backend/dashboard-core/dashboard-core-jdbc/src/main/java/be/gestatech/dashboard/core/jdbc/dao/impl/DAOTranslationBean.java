package be.gestatech.dashboard.core.jdbc.dao.impl;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;

import be.gestatech.dashboard.core.api.vo.Translation;
import be.gestatech.dashboard.core.jdbc.dao.api.DAOTranslationLocal;
import be.gestatech.dashboard.core.jdbc.datasource.DataSourceProvider;

/**
 * Created by amurifa on 28/06/2017.
 */
@Stateless
public class DAOTranslationBean extends DataSourceProvider implements DAOTranslationLocal {

	private final Logger LOGGER = Logger.getLogger(DAOTranslationBean.class.getName());

	@Override
	public Translation getByPK(int id) throws ObjectNotFoundException {
		return null;
	}

	@Override
	public Translation getByPKForUpdate(int id) throws ObjectNotFoundException {
		return null;
	}

	@Override
	public Translation add(Translation valueObject) throws RuntimeException {
		return null;
	}

	@Override
	public List<Translation> add(List<Translation> valueObjects) throws RuntimeException {
		return null;
	}

	@Override
	public void update(Translation vo) throws RuntimeException {

	}

	@Override
	public void update(List<Translation> valueObjects) throws RuntimeException {

	}

	@Override
	public void remove(Translation valueObject) throws RuntimeException {

	}

	@Override
	public void remove(List<Translation> valueObjects) throws RuntimeException {

	}

	@Override
	public void removeByPrimaryKey(int primaryKey) throws RuntimeException {

	}

	@Override
	public void removeByPrimaryKey(List<Integer> primaryKey) throws RuntimeException {

	}

	@Override
	public void replaceInDatabase(List<Translation> newCollection, List<Translation> existingCollection) throws RuntimeException {

	}

	@Override
	public List<Translation> getAll() throws RuntimeException {
		return null;
	}
}
