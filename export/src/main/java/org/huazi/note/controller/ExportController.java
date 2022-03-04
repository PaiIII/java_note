package org.huazi.note.controller;

import cn.hutool.core.convert.NumberChineseFormatter;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import org.huazi.note.excel.ExcelExportUtil;
import org.huazi.note.excel.ExcelTenantInfoDTO;
import org.huazi.note.word.WordUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注释
 *
 * @author huazi
 * @date 2022/3/4 10:36
 */
@RestController
@RequestMapping("/export")
public class ExportController {

    @GetMapping("/exportWord")
    public void exportWord(HttpServletResponse response) {
        Map dataMap = new HashMap();
        //基础信息
        dataMap.put("tenantName", "租户名称");//租户名称
        dataMap.put("leaseFloor", 16);//租赁楼层
        dataMap.put("leaseUnit", "888,666");//租赁单元
        dataMap.put("leaseArea", 205.26);//租赁面积(m2)
        dataMap.put("housesMoney", 360);//房屋租金/平米
        dataMap.put("leaseCar", 6);//租赁停车位数量
        dataMap.put("carMoney", 180);//车位租金/个
        dataMap.put("calculateMoney", 75393.6);//计算总金额+房屋租金+合计
        dataMap.put("calculateMoneyLp", NumberChineseFormatter.format(75393.6, true, true));//计算总金额大写
        dataMap.put("expirationDate", LocalDateTimeUtil.format(LocalDateTimeUtil.now(), DatePattern.CHINESE_DATE_PATTERN));//截止日期（年月日）
        //word表格信息
        dataMap.put("housesSumMoney", 73893.6);//房屋租金（合同取）
        dataMap.put("carportSumMoney", 1080);//车位租金（合同取）
        dataMap.put("otherMoney", 420);//其他费用（默认为0）
        //缴费方式信息
        dataMap.put("bankAccountName", "姜生");//收款人
        dataMap.put("bankName", "深圳市中国银行福田支行");//开户行
        dataMap.put("bankAccount", "432516WTY");//账  号
        dataMap.put("faxDept", "财务部");//汇款凭证传真部门
        dataMap.put("deptPhone", "0738-5589569");//部门电话
        dataMap.put("createTime", LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.CHINESE_DATE_PATTERN));//生成日期
        WordUtil.exportWord(response, dataMap, "paymentBill.ftl", "付款通知单" + DateUtil.format(LocalDateTime.now(), "yyyyMMdd") + ".doc");
    }

    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response, Integer dataStatus) {
        String fileName = "租户信息";
        //设置标题映射（列数）
        Map<String, String> map = MapUtil.newHashMap(true);
        map.put("tenantName", "租户名称");
        map.put("address", "地址");
        map.put("payAccount", "缴费账户");
        //设置某些列单元格样式左对齐:从0开始
        List<Integer> lstCellStyle = new ArrayList<>();
        lstCellStyle.add(0);
        lstCellStyle.add(1);
        //转换为导出类
        List<ExcelTenantInfoDTO> excelDataList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ExcelTenantInfoDTO dto = new ExcelTenantInfoDTO();
            dto.setTenantName("租户名称" + i);
            dto.setAddress("地址XXXXXXXXXXXXXXXXXXXXXXXXXX" + i);
            dto.setPayAccount("X584WE" + i);
            excelDataList.add(dto);
        }
        ExcelExportUtil.exportExcel(response, excelDataList, map, fileName + DateUtil.format(LocalDateTime.now(), "yyyyMMdd"), lstCellStyle);
    }
}
