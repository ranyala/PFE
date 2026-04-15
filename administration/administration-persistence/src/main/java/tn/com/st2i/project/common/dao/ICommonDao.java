package tn.com.st2i.project.common.dao;

import tn.com.st2i.project.tools.model.DaoObject;
import tn.com.st2i.project.tools.model.SearchObject;

public interface ICommonDao {

	public DaoObject getListPaginator(SearchObject obj, Object objClass, String particularSpecifCondi);
	
	public DaoObject getCountPaginator(SearchObject obj, Object objClass, String particularSpecifCondi);

	public DaoObject getListPaginatorNative(SearchObject obj, Object objClass, String particularSpecifCondi);

	public DaoObject getObjectById(Object objClass, String valueId, Boolean nativeSQL, String specifCondi);

	public DaoObject getListObject(SearchObject obj, Object objClas, String specifCondi, Boolean nativeSQL);

	public DaoObject getUniqueCode(Object objClass, String colCode, Object idValue, String codeValue);

	public DaoObject getSingleObject(Object objClass, String specifCondi, Boolean nativeSQL);

	public DaoObject getDateSystemNow();
}
