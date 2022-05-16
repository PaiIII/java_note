package org.huazi.note.excel.alibaba;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 临时收费
 *
 * @author huazi
 * @date 2022/1/19 10:16
 */
@Data
@ExcelIgnoreUnannotated
public class HousesParkTemporaryPO {
    private static final long serialVersionUID = 1L;
    private String id;

    private LocalDate payDate;

    private LocalDate receivedDate;

    @ExcelProperty(index = 2)
    private BigDecimal proceedsCash;

    @ExcelProperty(index = 3)
    private BigDecimal proceedsWeChat;

    @ExcelProperty(index = 4)
    private BigDecimal proceedsAliPay;

    @ExcelProperty(index = 5)
    private BigDecimal costDay;

    @ExcelProperty(index = 6)
    private BigDecimal serviceChargeWeChat;

    @ExcelProperty(index = 7)
    private BigDecimal serviceChargeAliPay;

    @ExcelProperty(index = 8)
    private BigDecimal saveBank;

    @ExcelProperty(index = 9)
    private BigDecimal settleAccountsWeChat;

    @ExcelProperty(index = 10)
    private BigDecimal settleAccountsAliPay;

    @ExcelProperty(index = 11)
    private BigDecimal receivedCost;

    private Integer dataFrom;

    @ExcelProperty(index = 0)
    private String payDateText;

    @ExcelProperty(index = 1)
    private String receivedDateText;

}
