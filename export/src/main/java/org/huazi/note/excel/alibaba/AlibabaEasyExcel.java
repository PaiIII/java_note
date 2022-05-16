package org.huazi.note.excel.alibaba;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.List;

/**
 * 注释
 *
 * @author huazi
 * @date 2022/5/16 15:38
 */
@UtilityClass
public class AlibabaEasyExcel {

    public void importExcelMoreSheet(InputStream inputStream) {
        ExcelReader excelReader = EasyExcel.read(inputStream).build();
        ExcelListener excelListener = new ExcelListener<HousesParkMonthPO, HousesParkTemporaryPO, HousesParkTenantPO>();

        // 这里为了简单 所以注册了 同样的head 和Listener 自己使用功能必须不同的Listener
        ReadSheet readSheet1 =
                EasyExcel.readSheet(ParkCostExcelConstant.MONTH_CARD_REPORT_NAME).headRowNumber(3).head(HousesParkMonthPO.class).registerReadListener(excelListener).build();
        ReadSheet readSheet2 =
                EasyExcel.readSheet(ParkCostExcelConstant.TEMP_COST_REPORT_NAME).headRowNumber(4).head(HousesParkTemporaryPO.class).registerReadListener(excelListener).build();
        ReadSheet readSheet3 =
                EasyExcel.readSheet(ParkCostExcelConstant.TENANT_REPORT_NAME).headRowNumber(3).head(HousesParkTenantPO.class).registerReadListener(excelListener).build();

        excelReader.read(readSheet1, readSheet2, readSheet3);

        //业务操作
        workService(excelListener);

    }

    private void workService(ExcelListener excelListener) {
        //月卡收费报表
        List<HousesParkMonthPO> housesParkMonthList = excelListener.getHousesParkMonthList();
        //临时收费报表
        List<HousesParkTemporaryPO> housesParkTemporaryList = excelListener.getHousesParkTemporaryList();
        //商户报表
        List<HousesParkTenantPO> housesParkTenantList = excelListener.getHousesParkTenantList();

    }
}
