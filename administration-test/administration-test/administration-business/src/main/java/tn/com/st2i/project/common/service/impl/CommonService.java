package tn.com.st2i.project.common.service.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.html.WebColors;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import tn.com.st2i.project.common.dao.ICommonDao;
import tn.com.st2i.project.common.service.ICommonService;
import tn.com.st2i.project.tools.Constante;
import tn.com.st2i.project.tools.ConstanteService;
import tn.com.st2i.project.tools.ConstanteWs;
import tn.com.st2i.project.tools.UtilsDao;
import tn.com.st2i.project.tools.UtilsWs;
import tn.com.st2i.project.tools.model.DaoObject;
import tn.com.st2i.project.tools.model.SearchObject;
import tn.com.st2i.project.tools.model.SendObject;

@Service
public class CommonService implements ICommonService {

	@Value("${assets.pdf-header}")
	private String pdfHeaderUrl;

	private static final Logger logger = LogManager.getLogger(CommonService.class);

	@Autowired
	private ICommonDao commonDao;

	@Autowired
	private UtilsWs utilsWs;

	@Override
	public SendObject getListPaginator(SearchObject objX, Object objClass, String particularSpecifCondi) {
		try {
			final SearchObject obj = new UtilsDao().initSearchObject(objX);

			CompletableFuture<DaoObject> cfL = CompletableFuture
					.supplyAsync(() -> commonDao.getListPaginator(obj, objClass, particularSpecifCondi));
			CompletableFuture<DaoObject> cfC = CompletableFuture
					.supplyAsync(() -> commonDao.getCountPaginator(obj, objClass, particularSpecifCondi));
			DaoObject daoL = cfL.get();
			DaoObject daoC = cfC.get();

			DaoObject daoObject = new DaoObject(daoL.getCode(), daoL.getObjectReturn(), daoC.getCountTotal());

			if (!daoObject.getCode().equals(ConstanteService._CODE_DAO_SUCCESS))
				return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());

			return utilsWs.resultPaginationWs(ConstanteWs._CODE_WS_SUCCESS, daoObject.getObjectReturn(),
					daoObject.getCountTotal());
		} catch (Exception e) {
			logger.error("Error CommonService in method getListPaginator of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	@Override
	public SendObject getListPaginatorNative(SearchObject obj, Object objClass, String particularSpecifCondi) {
		try {
			obj = new UtilsDao().initSearchObject(obj);
			DaoObject daoObject = commonDao.getListPaginatorNative(obj, objClass, particularSpecifCondi);
			if (!daoObject.getCode().equals(ConstanteService._CODE_DAO_SUCCESS))
				return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
			return utilsWs.resultPaginationWs(ConstanteWs._CODE_WS_SUCCESS, daoObject.getObjectReturn(),
					daoObject.getCountTotal());
		} catch (Exception e) {
			logger.error("Error CommonService in method getListPaginator of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	@Override
	public SendObject getObjectById(Object objClass, String valueId, Boolean nativeSQL) {
		try {
			DaoObject daoObjcet = commonDao.getObjectById(objClass, valueId, nativeSQL, null);
			if (daoObjcet.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(daoObjcet.getCode(), daoObjcet.getObjectReturn(), null);
			else
				return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null, null);
		} catch (Exception e) {
			logger.error("Error CommonService in method getObjectById of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR, null, null);
		}
	}

	@Override
	public SendObject getObjectById(Object objClass, String valueId, String particularSpecifCondi, Boolean nativeSQL) {
		try {
			DaoObject daoObjcet = commonDao.getObjectById(objClass, valueId, nativeSQL, particularSpecifCondi);
			if (daoObjcet.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(daoObjcet.getCode(), daoObjcet.getObjectReturn(), null);
			else
				return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null, null);
		} catch (Exception e) {
			logger.error("Error CommonService in method getObjectById of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR, null, null);
		}
	}

	@Override
	public SendObject getListObject(Object objClass, SearchObject obj, Boolean nativeSQL) {
		try {
			DaoObject daoObjcet = commonDao.getListObject(obj, objClass, null, nativeSQL);
			if (daoObjcet.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(daoObjcet.getCode(), daoObjcet.getObjectReturn(), null);
			else
				return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null, null);
		} catch (Exception e) {
			logger.error("Error CommonService in method getListObject of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR, null, null);
		}
	}

	@Override
	public SendObject getListObject(Object objClass, SearchObject obj, String particularSpecifCondi,
			Boolean nativeSQL) {
		try {
			DaoObject daoObjcet = commonDao.getListObject(obj, objClass, particularSpecifCondi, nativeSQL);
			if (daoObjcet.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(daoObjcet.getCode(), daoObjcet.getObjectReturn(), null);
			else
				return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null, null);
		} catch (Exception e) {
			logger.error("Error CommonService in method getListObject of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR, null, null);
		}
	}

	@Override
	public SendObject getUniqueCode(Object objClass, String colCode, Object idValue, String codeValue) {
		try {
			DaoObject daoObject = commonDao.getUniqueCode(objClass, colCode,
					(idValue != null ? idValue.toString() : null), codeValue);
			if (daoObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(((Boolean) daoObject.getObjectReturn() ? ConstanteService._CODE_SERVICE_SUCCESS
						: ConstanteService._CODE_SERVICE_ERROR_UNIQUE_CODE), null, null);
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null);
		} catch (Exception e) {
			logger.error("Error CommonService in method getListObject of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR, null, null);
		}
	}

	@Override
	public SendObject getSingleObject(Object objClass, String particularSpecifCondi, Boolean nativeSQL) {
		try {
			DaoObject daoObject = commonDao.getSingleObject(objClass, particularSpecifCondi, nativeSQL);
			if (daoObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(daoObject.getCode(), daoObject.getObjectReturn());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null);
		} catch (Exception e) {
			logger.error("Error CommonService in method getSingleObject of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null);
		}
	}

	@Override
	public SendObject getDateSystemNow() {
		try {
			DaoObject daoObject = commonDao.getDateSystemNow();
			if (!daoObject.getCode().equals(ConstanteService._CODE_SERVICE_SUCCESS))
				return new SendObject(daoObject.getCode());
			Timestamp date = (Timestamp) daoObject.getObjectReturn();
			return new SendObject(ConstanteService._CODE_SERVICE_SUCCESS, date);
		} catch (Exception e) {
			logger.error("Error CommonService in method getDateSystemNow :: " + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR_DAO, null);
		}
	}

	@Override
	public SendObject getDateSystemNowWs() {
		try {
			SendObject so = this.getDateSystemNow();
			return utilsWs.resultWs(so.getCode(), so.getPayload());
		} catch (Exception e) {
			logger.error("Error CommonService in method getDateSystemNowWs " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	@Override
	public SendObject getObjectByIdWs(Object objClass, String valueId, Boolean nativeSQL) {
		try {
			SendObject so = this.getObjectById(objClass, valueId, nativeSQL);
			return utilsWs.resultWs(so.getCode(), so.getPayload());
		} catch (Exception e) {
			logger.error("Error CommonService in method getObjectByIdWs of class " + objClass.getClass().getName()
					+ " :: " + e.toString());
			return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	private JSONObject putter(Tuple t) {
		JSONObject j = new JSONObject();
		t.getElements().forEach(col -> {
			String[] splitCol = col.getAlias().toLowerCase().split("_");
			if (splitCol.length > 1)
				for (int i = 1; i < splitCol.length; i++) {
					splitCol[i] = String.valueOf(splitCol[i].charAt(0)).toUpperCase() + splitCol[i].substring(1);
				}
			j.put(String.join("", splitCol), t.get(col.getAlias()));
		});
		return j;
	}

	@Override
	public SendObject mapper(Object tt) {
		try {
			if (tt != null) {
				if (tt instanceof java.util.ArrayList) {
					JSONArray list = new JSONArray();
					for (Tuple t : (List<Tuple>) tt) {
						list.put(putter(t));
					}
					return new SendObject(ConstanteWs._CODE_WS_SUCCESS, list);
				} else {
					return new SendObject(ConstanteWs._CODE_WS_SUCCESS, putter((Tuple) tt));
				}
			} else
				return null;
		} catch (Exception e) {
			logger.error("Error CommonService in method mapper ::" + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR_IN_METHOD, null);
		}
	}

	@Override
	public SendObject exportDataWs(SearchObject objX, Object objClass, String particularSpecifCondi) {
		try {
			objX.setPagination(null);

			final SearchObject obj = new UtilsDao().initSearchObject(objX);

			CompletableFuture<DaoObject> cfL = CompletableFuture
					.supplyAsync(() -> commonDao.getListPaginator(obj, objClass, particularSpecifCondi));
			CompletableFuture<DaoObject> cfC = CompletableFuture
					.supplyAsync(() -> commonDao.getCountPaginator(obj, objClass, particularSpecifCondi));
			DaoObject daoL = cfL.get();
			DaoObject daoC = cfC.get();

			DaoObject daoObject = new DaoObject(daoL.getCode(), daoL.getObjectReturn(), daoC.getCountTotal());

			if (!daoObject.getCode().equals(ConstanteService._CODE_DAO_SUCCESS))
				return new SendObject(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());

			if (objX.getTypeExport().equals(Constante.CODE_EXPORT_PDF)) {
				return this.generatePDFFile(objX, daoObject.getObjectReturn());

			} else if (objX.getTypeExport().equals(Constante.CODE_EXPORT_EXCEL)) {
				return this.generateExcelFile(objX, daoObject.getObjectReturn());

			}

			return new SendObject(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		} catch (Exception e) {
			logger.error("Error CommonService in method exportDataWs of class " + objClass.getClass().getName() + " ::"
					+ e.toString());
			return new SendObject(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	private SendObject generateExcelFile(SearchObject objX, Object objectReturnDataList) {
		try {
			JSONArray dataList = new JSONArray();
			Field field = null;
			if (objectReturnDataList instanceof JSONArray) {
				dataList = (JSONArray) objectReturnDataList;
			} else if (objectReturnDataList instanceof ArrayList) {
				for (Object obj : ((ArrayList<?>) objectReturnDataList)) {
					JSONObject jo = new JSONObject();
					for (int i = 1; i < obj.getClass().getDeclaredFields().length; i++) {
						field = obj.getClass().getDeclaredFields()[i];
						field.setAccessible(true);
						jo.put(field.getName(), field.get(obj));
					}
					dataList.put(jo);
				}
			}
			LinkedHashMap<String, Object> metadata = (LinkedHashMap<String, Object>) objX.getMetadata();

			List<LinkedHashMap<String, Object>> columns = ((List<LinkedHashMap<String, Object>>) metadata
					.get("columns"));

			if (objX.getLanguage().equals(Constante.CODE_LANGUAGE_AR)) {
				Collections.reverse(columns);
			}

			List<String> columnsLabels = columns.stream().map(col -> col.get("label").toString())
					.collect(Collectors.toList());

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet((String) metadata.get("title"));
			sheet.createFreezePane(0, 1, 0, 1);

			XSSFFont headerFont = workbook.createFont();
			headerFont.setBold(true);
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
			headerCellStyle.setBorderBottom(BorderStyle.MEDIUM);
			headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			Row headerRow = sheet.createRow(0);
			headerRow.setHeight((short) 500);

			// create header row
			for (int colIdx = 0; colIdx < columnsLabels.size(); colIdx++) {
				Cell cell = headerRow.createCell(colIdx);
				cell.setCellValue(columnsLabels.get(colIdx));
				cell.setCellStyle(headerCellStyle);
			}

			// create data rows
			int rowIdx = 1;
			for (Object o : dataList) {
				JSONObject jo = (JSONObject) o;
				Row row = sheet.createRow(rowIdx++);
				for (int colIdx = 0; colIdx < columns.size(); colIdx++) {
					if (columns.get(colIdx).get("key") instanceof String) {
						row.createCell(colIdx).setCellValue(
								this.getFormattedValueForExport(jo, columns.get(colIdx), objX.getLanguage()));
					} else if (columns.get(colIdx).get("key") instanceof LinkedHashMap) {
						row.createCell(colIdx)
								.setCellValue(jo.get(((LinkedHashMap<?, ?>) columns.get(colIdx).get("key"))
										.get(objX.getLanguage()).toString()).toString());
					}
				}
			}

			// Auto size all the columns
			for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
				sheet.autoSizeColumn(i);
			}

			// Write the book and close the outputStream
			File file = new File(metadata.get("title").toString());
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();

			return new SendObject(ConstanteService._CODE_SERVICE_SUCCESS, file);
		} catch (NoClassDefFoundError e) {
			logger.error("Error CommonService in method generateExcelFile ::" + e.toString());
			return new SendObject(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		} catch (IllegalAccessException e) {
			logger.error("Error CommonService in method generateExcelFile ::" + e.toString());
			return new SendObject(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		} catch (IOException e) {
			logger.error("Error CommonService in method generateExcelFile ::" + e.toString());
			return new SendObject(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		} catch (Exception e) {
			logger.error("Error CommonService in method generateExcelFile ::" + e.toString());
			return new SendObject(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	/////////////////// PDF////////
	private SendObject generatePDFFile(SearchObject objX, Object objectReturnDataList) {
		try {
//adding data
			JSONArray dataList = new JSONArray();
			Field field = null;
			if (objectReturnDataList instanceof JSONArray) {
				dataList = (JSONArray) objectReturnDataList;
			} else if (objectReturnDataList instanceof ArrayList) {
				for (Object obj : ((ArrayList<?>) objectReturnDataList)) {
					JSONObject jo = new JSONObject();
					for (int i = 1; i < obj.getClass().getDeclaredFields().length; i++) {
						field = obj.getClass().getDeclaredFields()[i];
						field.setAccessible(true);
						jo.put(field.getName(), field.get(obj));
					}
					dataList.put(jo);
				}
			}

			LinkedHashMap<String, Object> metadata = (LinkedHashMap<String, Object>) objX.getMetadata();
			List<LinkedHashMap<String, Object>> columns = ((List<LinkedHashMap<String, Object>>) metadata
					.get("columns"));

			if (objX.getLanguage().equals(Constante.CODE_LANGUAGE_AR)) {
				Collections.reverse(columns);
			}

			List<String> columnsLabels = columns.stream().map(col -> col.get("label").toString())
					.collect(Collectors.toList());

			Font font = new Font(Font.HELVETICA, 10, Font.BOLD, java.awt.Color.white);
			Document doc = new Document();
			File file = new File(metadata.get("title").toString());
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file));
			doc.open();
//Creating font
			Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			fontTiltle.setSize(20);
			Font fontt = new Font(Font.HELVETICA, 12, Font.ITALIC, java.awt.Color.black);
			Image image = Image.getInstance(this.pdfHeaderUrl);

			image.scaleAbsolute(600, 80);
			image.setAlignment(Element.ALIGN_CENTER);

			doc.add(image);
			Paragraph esp = new Paragraph("\n");
			doc.add(new Paragraph(esp));

			Font TitleFont = new Font(Font.HELVETICA, 15, Font.BOLD, java.awt.Color.black);

			Paragraph titre = new Paragraph(metadata.get("title").toString(), TitleFont);
			titre.setAlignment(Element.ALIGN_CENTER);
			doc.add(new Paragraph(titre));
			Paragraph titre2 = new Paragraph("\n");
			doc.add(new Paragraph(titre2));

			Calendar cals = Calendar.getInstance();
//Displaying the actual date
			LocalDate todaysDate = LocalDate.now();
			String Date = "Tunis le " + todaysDate.toString();
			Paragraph date = new Paragraph(Date);
			date.setAlignment(Element.ALIGN_RIGHT);

//Creating table
			doc.add(date);

			doc.add(new Paragraph(titre2));

			PdfPTable table = new PdfPTable(columnsLabels.size());
			table.setWidthPercentage(100);
//Creating headers rows
			for (int colIdx = 0; colIdx < columnsLabels.size(); colIdx++) {
				PdfPCell cell = new PdfPCell();
				Color myColor = WebColors.getRGBColor("#1581b3");
				cell.setBackgroundColor(myColor);

				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

				cell.setPhrase(new Phrase(columnsLabels.get(colIdx), font));
				table.addCell(cell);
			}
//create data rows
			Font fontRow = new Font(Font.TIMES_ROMAN, 11, Font.NORMAL);
			for (Object o : dataList) {
				JSONObject jo = (JSONObject) o;
				for (int colIdx = 0; colIdx < columns.size(); colIdx++) {
					if (columns.get(colIdx).get("key") instanceof String) {
						Phrase phrase = new Phrase(
								this.getFormattedValueForExport(jo, columns.get(colIdx), objX.getLanguage()), fontRow);
						table.addCell(new PdfPCell(phrase));
					} else if (columns.get(colIdx).get("key") instanceof LinkedHashMap) {
						Phrase phrase = new Phrase(jo.get(((LinkedHashMap<?, ?>) columns.get(colIdx).get("key"))
								.get(objX.getLanguage()).toString()).toString(), fontRow);
						table.addCell(new PdfPCell(phrase));
					}
				}
			}
			doc.add(table);
			doc.close();
			writer.close();
			return new SendObject(ConstanteService._CODE_SERVICE_SUCCESS, file);
		} catch (NoClassDefFoundError e) {
			logger.error("Error CommonService in method generatePDFFile ::" + e.toString());
			return new SendObject(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		} catch (IOException e) {
			logger.error("Error CommonService in method generatePDFFile ::" + e.toString());
			return new SendObject(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		} catch (Exception e) {
			logger.error("Error CommonService in method generatePDFFile ::" + e.toString());
			return new SendObject(ConstanteWs._CODE_WS_ERROR_IN_METHOD, new JSONObject());
		}
	}

	public void generate(HttpServletResponse response) throws DocumentException, IOException {
		// Create the Object of Document
		Document document = new Document(PageSize.A4);
		// get the document and write the response to output stream
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
	}

	private String getFormattedValueForExport(JSONObject dataObj, LinkedHashMap<String, Object> column,
			String language) {

		Object value = "";

		if (column.get("key") instanceof String) {
			value = dataObj.has(column.get("key").toString()) ? dataObj.get(column.get("key").toString()) : "";
		}

		if (value == null || value == JSONObject.NULL || (value.equals("") && !column.containsKey("type"))
				|| (value.equals("") && column.containsKey("type")
						&& !column.get("type").equals(Constante.CODE_COMBINED))) {
			return "-";
		}

		if (!column.containsKey("type")) {
			return value.toString();
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				language.equals(Constante.CODE_LANGUAGE_AR) ? "YYYY/MM/dd" : "dd/MM/YYYY");
		SimpleDateFormat simpleDatetimeFormat = new SimpleDateFormat(
				language.equals(Constante.CODE_LANGUAGE_AR) ? "YYYY/MM/dd" : "dd/MM/YYYY");
		NumberFormat montantFormat = NumberFormat.getInstance(java.util.Locale.FRENCH);
		montantFormat.setMaximumFractionDigits(2);
		switch (column.get("type").toString()) {
		case Constante.CODE_DATE:
			return simpleDateFormat.format(value);
		case Constante.CODE_DATETIME:
			return simpleDatetimeFormat.format(value);
		case Constante.CODE_MONTANT:
			return montantFormat.format(value);
		case Constante.CODE_COMBINED:
			StringBuilder result = new StringBuilder();
			logger.info(column.get("combination"));

			List<LinkedHashMap<String, Object>> combination = new ArrayList<>();
			for (Object element : (List<Object>) column.get("combination")) {
				logger.info(element);
				combination.add((LinkedHashMap<String, Object>) element);
			}

			// combination.put(column.get("combination"));

			if (combination != null && !combination.isEmpty()) {
				for (LinkedHashMap<String, Object> combObj : combination) {
					if (combObj.get("key") instanceof String) {
						result.append(this.getFormattedValueForExport(dataObj, combObj, language));
						if (combObj.get("sep") != null) {
							result.append(combObj.get("sep"));
						}
					} else if (combObj.get("key") instanceof LinkedHashMap) {
						result.append(dataObj.get(((LinkedHashMap<?, ?>) combObj.get("key")).get(language).toString()));
						if (combObj.get("sep") != null) {
							result.append(combObj.get("sep"));
						}
					}

				}
			}

			return result.toString();
		case Constante.CODE_TEXT:
		default:
			return value.toString();
		}
	}

	public Object getObjectToSave(Object newEntity, Object oldEntity) {
		try {
			Field field = null;
			for (int i = 1; i < newEntity.getClass().getDeclaredFields().length; i++) {
				field = newEntity.getClass().getDeclaredFields()[i];
				field.setAccessible(true);
				if (field.get(newEntity) == null) {
					PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(newEntity);
					myAccessor.setPropertyValue(field.getName(), field.get(oldEntity));
				}
			}
			return newEntity;
		} catch (Exception e) {
			logger.error("Error CommonService in method getEntityToSave ::" + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR_IN_METHOD, null);
		}
	}

	@Override
	public SendObject isUnicode(DataAccessException ex) {
		try {

			if (ex.getRootCause() instanceof SQLException) {
				SQLException sqlException = (SQLException) ex.getRootCause();
				String sqlState = sqlException.getSQLState();
				if (Constante.CODE_SQL_CODE_DUPLICATED.equals(sqlState)) {
					return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_UNIQUE_CODE, new JSONObject());
				} else
					return utilsWs.resultWs(ConstanteWs._CODE_WS_ERROR_SAVE_OR_UPDATE, new JSONObject());
			} else
				return new SendObject(ConstanteService._CODE_SERVICE_ERROR_IN_METHOD, null);

		} catch (Exception e) {
			logger.error("Error CommonService in method isUnicode ::" + e.toString());
			return new SendObject(ConstanteService._CODE_SERVICE_ERROR_IN_METHOD, null);
		}
	}

}
