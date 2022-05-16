package org.huazi.note.excel.alibaba;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 商户收费
 *
 * @author huazi
 * @date 2022/1/19 10:16
 */
@Data
@ExcelIgnoreUnannotated
public class HousesParkTenantPO {
    private static final long serialVersionUID = 1L;

    private String id;

    @ExcelProperty(index = 0)
    private Integer sortNum;

    @ExcelProperty(index = 1)
    private String tenantName;

    @ExcelProperty(index = 2)
    private String preferentialScheme;

    @ExcelProperty(index = 3)
    private BigDecimal preferentialCost;

    @ExcelProperty(index = 4)
    private BigDecimal parkCost;

    @ExcelProperty(index = 5)
    private BigDecimal preferentialParkCost;

    @ExcelProperty(index = 6)
    private BigDecimal payableMoney;

    @ExcelProperty(index = 7)
    private BigDecimal prepayMoneyMonth;

    @ExcelProperty(index = 8)
    private BigDecimal serviceCharge;

    @ExcelProperty(index = 9)
    private BigDecimal receivedCost;

    private LocalDate statisticsStartDate;

    private LocalDate statisticsEndDate;

    @ExcelProperty(index = 10)
    private String remark;

    private Integer queryCode;

    private Integer dataFrom;

}
