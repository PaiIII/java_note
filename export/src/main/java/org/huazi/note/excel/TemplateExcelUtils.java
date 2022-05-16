package org.huazi.note.excel;

import lombok.SneakyThrows;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 注释
 *
 * @author huazi
 * @date 2022/5/13 14:27
 */
public class TemplateExcelUtils {

    @SneakyThrows
    public static void downLoadExcel(String fileName, String sourcePath, Map<String, Object> beanParams, HttpServletResponse response) {
        try (OutputStream os = getOutputStream(fileName, response);
             InputStream is = TemplateExcelUtils.class.getClassLoader().getResourceAsStream("template/" + sourcePath)
        ) {
            XLSTransformer transformer = new XLSTransformer();
            Workbook workbook = transformer.transformXLS(is, beanParams);
            workbook.write(os);
        } catch (Exception e) {
            throw new Exception("excel流写入错误");
        }
    }

    @SneakyThrows
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
        try {
            fileName = URLEncoder.encode(fileName + ".xls", "UTF-8");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Access-Control-Expose-Headers", "filename");
            response.setHeader("filename", fileName);
            return response.getOutputStream();
        } catch (Exception e) {
            throw new Exception("获取excel流文件错误", e);
        }

    }
}

