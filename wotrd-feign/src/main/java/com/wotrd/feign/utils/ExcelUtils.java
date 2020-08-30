package com.wotrd.feign.utils;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ExcelUtils
 * @Description: excel工具类
 * @author: wotrd
 * @date: 2019年5月17日上午10:47:14
 */
public class ExcelUtils {

    private HSSFWorkbook wb = new HSSFWorkbook();
    private List<HSSFSheet> sheets = new ArrayList<>();

    /**
     * 导出单页Excel
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <E> void exportExcel(OutputStream os, String[] header, int[] columnWidth, String[] properties,
                                       List<E> list) {
        // 创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建一个sheet
        HSSFSheet sheet = wb.createSheet("统计数据");
        HSSFRow headerRow = sheet.createRow(0);
        HSSFRow contentRow = null;

        // 设置标题
        for (int i = 0; i < header.length; i++) {
            headerRow.createCell(i).setCellValue(header[i]);
            sheet.setColumnWidth(i, columnWidth[i]);
        }
        try {
            for (int i = 0; i < list.size(); i++) {
                contentRow = sheet.createRow(i + 1);
                // 获取每一个对象
                E o = list.get(i);
                Class cls = o.getClass();
                for (int j = 0; j < properties.length; j++) {
                    String fieldName =
                            properties[j].substring(0, 1).toUpperCase() + properties[j].substring(1);
                    Method getMethod = cls.getMethod("get" + fieldName);
                    Object value = getMethod.invoke(o);
                    if (value != null) {
                        contentRow.createCell(j).setCellValue(value.toString());
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 导出单页Excel
     */
    public static <E> void exportExcel(HttpServletResponse response, String[] header,
                                       String[] properties, int[] columnWidth, List<E> list,
                                       String downloadName) {
        OutputStream os = null;
        try {
            response.reset();
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(downloadName, "utf-8") + ".xls");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            os = response.getOutputStream();
            exportExcel(os, header, columnWidth, properties,  list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 添加标签页
     */
    public <E> void addSheet(String sheetName, String[] headers, String[] properties,
                             List<E> dataList)
            throws NoSuchMethodException, SecurityException {
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 标题栏
        HSSFRow headerRow = sheet.createRow(0);
        // 设置标题
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
        // 设置内容行
        HSSFRow contentRow = null;
        for (int i = 0; i < dataList.size(); i++) {
            contentRow = sheet.createRow(i + 1);
            // 获取每一个对象
            E o = dataList.get(i);
            Class<? extends Object> cls = o.getClass();
            for (int j = 0; j < properties.length; j++) {
                String fieldName = properties[j].substring(0, 1).toUpperCase() + properties[j].substring(1);
                Method getMethod = cls.getMethod("get" + fieldName);
                try {
                    Object value = getMethod.invoke(o);
                    if (value != null) {
                        contentRow.createCell(j).setCellValue(value.toString());
                    }
                } catch (Exception e) {
                    throw new RuntimeException("导出Excel错误");
                }
            }
        }
        sheets.add(sheet);
    }

    /**
     * 导出多标签的Excel
     */
    public void export(HttpServletResponse response, String downloadName) {
        OutputStream os = null;
        try {
            response.reset();
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(downloadName, "utf-8") + ".xls");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            os = response.getOutputStream();
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("导出excel失败!" + e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 导出多页Excel
     */
    public void export(OutputStream os) {
        try {
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("导出excel失败!" + e);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
