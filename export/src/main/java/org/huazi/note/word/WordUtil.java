package org.huazi.note.word;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description 利用FreeMarker导出Word
 */
public class WordUtil {

    public boolean createDoc(HttpServletResponse response, Map dataMap, String path, String srcFileName, String exportFileName) {
        try {
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + new String(exportFileName.getBytes("gbk"), "iso-8859-1") + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            FreemarkerUtils.build(this.getClass(), path).setTemplate(srcFileName).generate(dataMap,
                    response.getOutputStream());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 导出word
     *
     * @param dataMap        组装数据格式
     * @param srcFileName    模板文件存储路径为：src\main\resources\file\X.ftl，只取文件名
     * @param exportFileName 导出文件名
     * @response
     */
    public static boolean exportWord(HttpServletResponse response, Map<String, Object> dataMap, String srcFileName, String exportFileName) {
        return new WordUtil().createDoc(response, dataMap, "/file/", srcFileName, exportFileName);
    }
}