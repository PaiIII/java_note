package org.huazi.note.excel.alibaba;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 月卡收费
 *
 * @author huazi
 * @date 2022/1/19 10:16
 */
@Data
@ExcelIgnoreUnannotated
public class HousesParkMonthPO {
    private static final long serialVersionUID = 1L;

    private String id;

    @ExcelProperty(index = 0)
    private Integer sortNum;

    private LocalDate payDate;

    private LocalDate receivedDate;

    @ExcelProperty(index = 3)
    private String category;

    @ExcelProperty(index = 4)
    private String tenantName;

    private LocalDate startTime;

    private LocalDate endTime;

    @ExcelProperty(index = 6)
    private BigDecimal costMonth;

    @ExcelProperty(index = 7)
    private String monthSum;

    @ExcelProperty(index = 8)
    private Integer carSum;

    @ExcelProperty(index = 9)
    private BigDecimal costSum;

    @ExcelProperty(index = 10)
    private BigDecimal serviceCharge;

    @ExcelProperty(index = 11)
    private BigDecimal receivedCost;

    @ExcelProperty(index = 12)
    private String payWay;

    private Integer queryCode;

    @ExcelProperty(index = 13)
    private String remark;

    private Integer dataFrom;

    @ExcelProperty(index = 1)
    private String payDateText;

    @ExcelProperty(index = 2)
    private String receivedDateText;

    @ExcelProperty(index = 5)
    private String period;

}
