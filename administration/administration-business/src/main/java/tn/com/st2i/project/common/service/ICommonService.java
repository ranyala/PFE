package tn.com.st2i.project.common.service;

import org.springframework.dao.DataAccessException;
import tn.com.st2i.project.tools.model.SearchObject;
import tn.com.st2i.project.tools.model.SendObject;

public interface ICommonService {

	public SendObject getListPaginator(SearchObject obj, Object objClass, String particularSpecifCondi);

	public SendObject getListPaginatorNative(SearchObject obj, Object objClass, String particularSpecifCondi);

	public SendObject getObjectById(Object objClass, String valueId, Boolean nativeSQL);

	public SendObject getObjectById(Object objClass, String valueId, String particularSpecifCondi, Boolean nativeSQL);

	public SendObject getListObject(Object objClass, SearchObject obj, Boolean nativeSQL);

	public SendObject getListObject(Object objClass, SearchObject obj, String particularSpecifCondi, Boolean nativeSQL);

	public SendObject getUniqueCode(Object objClass, String colCode, Object idValue, String codeValue);

	public SendObject getSingleObject(Object objClass, String particularSpecifCondi, Boolean nativeSQL);

	public SendObject getDateSystemNow();

	public SendObject getDateSystemNowWs();

	public SendObject getObjectByIdWs(Object objClass, String valueId, Boolean nativeSQL);

	public SendObject mapper(Object object);

	public SendObject exportDataWs(SearchObject obj, Object objClass, String particularSpecifCondi);
	
	public SendObject isUnicode (DataAccessException ex);


}
