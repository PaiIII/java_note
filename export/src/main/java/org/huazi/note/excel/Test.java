package org.huazi.note.excel;

import cn.hutool.core.date.DateUtil;
import org.huazi.note.excel.alibaba.AlibabaEasyExcel;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注释
 *
 * @author huazi
 * @date 2022/3/28 10:27
 */
public class Test {
    public static void main(String[] args) {
        //importExcel();
        importExcelMoreSheet();
    }

    public static void importExcel() {
        try {
            InputStream inputStream = new FileInputStream(new File("C:\\Users\\huazi\\Desktop\\test.xlsx"));
            List<ExcelCostDTO> result = ExcelImportUtil.importExcel(inputStream, ExcelCostDTO.class, 0, 1);
            System.out.println(result);
        } catch (Exception e) {
        }
    }

    /**
     * 多sheet页导入
     */
    public static void importExcelMoreSheet() {
        try {
            //InputStream inputStream1 = new FileInputStream(new File("C:\\Users\\huazi\\Desktop\\停车场收入模板.xlsx"));
            InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("file/" + "test1.xlsx");
            AlibabaEasyExcel.importExcelMoreSheet(inputStream);
        } catch (Exception e) {
        }
    }

    /**
     * 根据模板导出
     *
     * @param response
     */
    public static void downLoadExcel(HttpServletResponse response) {
        //list数据为数据库查询，对象与模板得属性，相对应
        List<String> list = new ArrayList<>();
        String fileName = "物资品类" + DateUtil.format(LocalDateTime.now(), "yyyyMMdd");
        Map<String, Object> param = new HashMap<>();
        param.put("list", list);
        TemplateExcelUtils.downLoadExcel(fileName, "clqc.xls", param, response);
    }

}
