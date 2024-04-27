package tn.com.st2i.project.tools.model;

import java.util.LinkedHashMap;
import java.util.List;

import lombok.Data;
import org.json.JSONObject;

@Data
public class SearchObject {

	private Pagination pagination;
	private Sort sort;
	private List<Sort> listSort;
	private List<CriteriaSearch> dataSearch;
	private List<String> listCol;

	//For Export
	private String language;
//	private String title;
	private String typeExport;
	private Object metadata;

}
