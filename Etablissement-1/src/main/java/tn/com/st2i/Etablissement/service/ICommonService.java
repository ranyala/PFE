package tn.com.st2i.Etablissement.service;

import org.json.JSONException;
import org.springframework.dao.DataAccessException;
import tn.com.st2i.Etablissement.model.tool.SearchObject;
import tn.com.st2i.Etablissement.model.tool.SendObject;


public interface ICommonService {
    public SendObject getListPaginator(SearchObject obj, Object objClass, String particularSpecifCondi) throws JSONException;

    public SendObject getListPaginatorNative(SearchObject obj, Object objClass, String particularSpecifCondi) throws JSONException;

    public SendObject getObjectById(Object objClass, String valueId, Boolean nativeSQL);

    public SendObject getObjectById(Object objClass, String valueId, String particularSpecifCondi, Boolean nativeSQL);

    public SendObject getListObject(Object objClass, SearchObject obj, Boolean nativeSQL);

    public SendObject getListObject(Object objClass, SearchObject obj, String particularSpecifCondi, Boolean nativeSQL);

    public SendObject getUniqueCode(Object objClass, String colCode, Object idValue, String codeValue);

    public SendObject getSingleObject(Object objClass, String particularSpecifCondi, Boolean nativeSQL);

    public SendObject getDateSystemNow();

    public SendObject getDateSystemNowWs() throws JSONException;

    public SendObject getObjectByIdWs(Object objClass, String valueId, Boolean nativeSQL) throws JSONException;

    public SendObject mapper(Object object);

    public SendObject exportDataWs(SearchObject obj, Object objClass, String particularSpecifCondi);

    public SendObject isUnicode (DataAccessException ex);

}
