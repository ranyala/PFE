package tn.com.st2i.Etablissement.dao;

import org.json.JSONException;

import tn.com.st2i.Etablissement.model.tool.DaoObject;
import tn.com.st2i.Etablissement.model.tool.SearchObject;

public interface ICommonDao {
    public DaoObject getListPaginator(SearchObject obj, Object objClass, String particularSpecifCondi)throws JSONException;

    public DaoObject getListPaginatorNative(SearchObject obj, Object objClass, String particularSpecifCondi)throws JSONException;

    public DaoObject getObjectById(Object objClass, String valueId, Boolean nativeSQL, String specifCondi)throws JSONException;

    public DaoObject getListObject(SearchObject obj, Object objClas, String specifCondi, Boolean nativeSQL);

    public DaoObject getUniqueCode(Object objClass, String colCode, Object idValue, String codeValue);

    public DaoObject getSingleObject(Object objClass, String specifCondi, Boolean nativeSQL);

    public DaoObject getDateSystemNow()throws JSONException;

    DaoObject getCountPaginator(SearchObject obj, Object objClass, String particularSpecifCondi);

}
