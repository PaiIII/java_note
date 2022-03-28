package org.huazi.note.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * 注释
 *
 * @author huazi
 * @date 2022/3/28 10:27
 */
public class Test {
    public static void main(String[] args) {
        importExcel();
    }

    public static void importExcel() {
        try {
            InputStream inputStream = new FileInputStream(new File("C:\\Users\\huazi\\Desktop\\test.xlsx"));
            List<ExcelCostDTO> result = ExcelImportUtil.importExcel(inputStream, ExcelCostDTO.class, 0, 1);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
