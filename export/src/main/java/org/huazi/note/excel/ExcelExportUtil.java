package org.huazi.note.excel;


import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.*;
import org.huazi.note.word.UtilException;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Excel导出工具类
 */
@UtilityClass
public class ExcelExportUtil {

    /**
     * 导出excel，后缀为.xls,默认单元格样式为居中
     *
     * @param response     HttpServletResponse
     * @param list<?>      导出对象集合
     * @param map          excel表格列别名：key与对象的属性对应，value表示导出转换的中文列名：example:name-名字
     * @param fileName     导出文件名，可以中文
     * @param lstCellStyle 单独设置某些列单元格样式左对齐:从0开始
     */
    public void exportExcel(HttpServletResponse response, List<?> list, Map<String, String> map, String fileName, List<Integer> lstCellStyle) {
        if (CollectionUtils.isEmpty(list) || map.isEmpty()) {
            return;
        }
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.setHeaderAlias(map);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        //列宽自适应
        setSizeColumn(writer.getSheet(), map.size());
        //传入列的单元格样式，左对齐
        if (!CollectionUtils.isEmpty(lstCellStyle)) {
            lstCellStyle.forEach(index -> {
                setCellStyleLeft(writer, index);
            });
        }
        ServletOutputStream out = null;
        //out为OutputStream，需要写出到的目标流
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            fileName = URLEncoder.encode(fileName + ".xls", "UTF-8");
            response.setHeader("Access-Control-Expose-Headers", "filename");
            response.setHeader("filename", fileName);
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            throw new UtilException("exportExcel is exception!");
        } finally {
            // 关闭writer，释放内存
            writer.close();
            //关闭输出Servlet流
            IoUtil.close(out);
        }
    }

    /**
     * 自适应宽度(中文支持)
     *
     * @param sheet
     * @param size  因为for循环从0开始，size值为 列数-1
     */
    public void setSizeColumn(Sheet sheet, int size) {
        for (int columnNum = 0; columnNum <= size; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                columnWidth = getColumnWidth(columnNum, columnWidth, currentRow);
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }

    private static int getColumnWidth(int columnNum, int columnWidth, Row currentRow) {
        if (currentRow.getCell(columnNum) != null) {
            Cell currentCell = currentRow.getCell(columnNum);
            if (currentCell.getCellTypeEnum() == CellType.STRING) {
                int length = currentCell.getStringCellValue().getBytes().length;
                if (columnWidth < length) {
                    columnWidth = length;
                }
            }
        }
        return columnWidth;
    }

    /**
     * 设置index列的样式左对齐，从0开始
     *
     * @param writer ExcelWriter
     * @param index  列数
     */
    public void setCellStyleLeft(ExcelWriter writer, int index) {
        Sheet sheet = writer.getSheet();
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {//行
            CellStyle cellStyle = writer.createCellStyle(index, rowNum);
            Row currentRow = sheet.getRow(rowNum);//行
            Cell currentCell = currentRow.getCell(index);
            cellStyle.cloneStyleFrom(writer.getCellStyle());
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            currentCell.setCellStyle(cellStyle);
        }
    }

}
