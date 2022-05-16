package org.huazi.note.excel.alibaba;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ConverterUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注释
 *
 * @author huazi
 * @date 2022/5/16 15:42
 */
@Data
public class ExcelListener<T, K, V> extends AnalysisEventListener {

    private List<T> housesParkMonthList = new ArrayList<>();

    private List<K> housesParkTemporaryList = new ArrayList<>();

    private List<V> housesParkTenantList = new ArrayList<>();

    private Map<Integer, String> tenantHeadMap = new HashMap<>();

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        String sheetName = analysisContext.readSheetHolder().getSheetName();
        switch (sheetName) {
            case ParkCostExcelConstant.MONTH_CARD_REPORT_NAME:
                housesParkMonthList.add((T) o);
                break;
            case ParkCostExcelConstant.TEMP_COST_REPORT_NAME:
                housesParkTemporaryList.add((K) o);
                break;
            case ParkCostExcelConstant.TENANT_REPORT_NAME:
                housesParkTenantList.add((V) o);
                break;
            default:
                break;
        }
    }

    @Override
    public void invokeHead(Map headMap, AnalysisContext context) {
        Map<Integer, String> map = ConverterUtils.convertToStringMap(headMap, context);
        String sheetName = context.readSheetHolder().getSheetName();
        //获取商户的统计日期
        if (ParkCostExcelConstant.TENANT_REPORT_NAME.equals(sheetName) && map.get(0).contains("统计日期")) {
            tenantHeadMap = map;
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
