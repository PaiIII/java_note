package org.huazi.note.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 简单表格导入
 *
 * @author huazi
 * @date 2022/3/28 9:51
 */
@UtilityClass
public class ExcelImportUtil {

    /**
     * @param inputStream    Excel文件流
     * @param bean           实体对象属性的顺序，需与表格顺序一致
     * @param headerRowIndex 表头所在行数   从0开始
     * @param startRowIndex  数据开始读取位置 从0开始
     * @return
     */
    public <T> List<T> importExcel(InputStream inputStream, Class<T> bean, int headerRowIndex, int startRowIndex) {
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //读取表头
        List<Object> header = reader.readRow(headerRowIndex);
        //导入表格表头为空
        if (CollUtil.isEmpty(header)) {
            return null;
        }
        //导入表格与实体类属性数量不一致
        if (getFiledName(bean).size() != header.size()) {
            return null;
        }
        //替换表头
        for (int i = 0; i < header.size(); i++) {
            reader.addHeaderAlias((String) header.get(i), getFiledName(bean).get(i));
        }
        return reader.read(headerRowIndex, startRowIndex, bean);
    }

    /**
     * 获取属性名集合
     */
    private <T> List<String> getFiledName(Class<T> bean) {
        Field[] fields = bean.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return CollUtil.toList(fieldNames);
    }
}
