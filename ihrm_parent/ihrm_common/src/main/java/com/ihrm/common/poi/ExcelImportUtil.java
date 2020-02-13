package com.ihrm.common.poi;

import com.ihrm.domain.poi.ExcelAttribute;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExcelImportUtil<T> {
 
    private Class clazz;
    private  Field fields[];
 
    public ExcelImportUtil(Class clazz) {
        this.clazz = clazz;
        fields = clazz.getDeclaredFields();
    }
 
    /**
     * 基于注解读取excel
     */
    public List<T> readExcel(InputStream is, int rowIndex,int cellIndex) {
        List<T> list = new ArrayList<T>();
        T entity = null;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            // 不准确
            int rowLength = sheet.getLastRowNum();

            System.out.println(sheet.getLastRowNum());
            for (int rowNum = rowIndex; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                entity = (T) clazz.newInstance();
                System.out.println(row.getLastCellNum());
                for (int j = cellIndex; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    for (Field field : fields) {
                        if(field.isAnnotationPresent(ExcelAttribute.class)){
                            field.setAccessible(true);
                            ExcelAttribute ea = field.getAnnotation(ExcelAttribute.class);
                            if(j == ea.sort()) {
                                field.set(entity, covertAttrType(field, cell));
                            }
                        }
                    }
                }
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
 

    /**
     * 类型转换 将cell 单元格格式转为 字段类型
     */
    private Object covertAttrType(Field field, Cell cell) throws Exception {
        String fieldType = field.getType().getSimpleName();
        if ("String".equals(fieldType)) {
            return getValue(cell);
        }else if ("Date".equals(fieldType)) {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(getValue(cell)) ;
        }else if ("int".equals(fieldType) || "Integer".equals(fieldType)) {
            return Integer.parseInt(getValue(cell));
        }else if ("double".equals(fieldType) || "Double".equals(fieldType)) {
            return Double.parseDouble(getValue(cell));
        }else {
            return null;
        }
    }
 
 
    /**
     * 格式转为String
     * @param cell
     * @return
     */
    public String getValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getRichStringCellValue().getString().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date dt = DateUtil.getJavaDate(cell.getNumericCellValue());
                    return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dt);
                } else {
                    // 防止数值变成科学计数法
                    String strCell = "";
                    Double num = cell.getNumericCellValue();
                    BigDecimal bd = new BigDecimal(num.toString());
                    if (bd != null) {
                        strCell = bd.toPlainString();
                    }
                    // 去除 浮点型 自动加的 .0
                    if (strCell.endsWith(".0")) {
                        strCell = strCell.substring(0, strCell.indexOf("."));
                    }
                    return strCell;
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}