package org.huazi.note.manager.entity;

import lombok.Data;

/**
 * 注释
 *
 * @author huazi
 * @date 2021/12/24 10:36
 */
@Data
public class Goods {

    private Integer goodsId;
    private String goodsName;

    public Goods() {

    }

    public Goods(Integer goodsId, String goodsName) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
    }

}
