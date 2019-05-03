package org.stock.fetch.api.dto;

import java.util.List;

import com.aeasycredit.commons.lang.base.BaseDto;

public class ChangeStockMySubSelectedDto extends BaseDto {
    /** 注释内容 */
    private static final long serialVersionUID = 348947625707581700L;
    private String currSelectedType;
    private String stockId;
    private List<String> subSelectedType;

    public String getStockId() {
        return this.stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }


    public List<String> getSubSelectedType() {
        return this.subSelectedType;
    }

    public void setSubSelectedType(List<String> subSelectedType) {
        this.subSelectedType = subSelectedType;
    }
    
    public String getCurrSelectedType() {
        return this.currSelectedType;
    }

    public void setCurrSelectedType(String currSelectedType) {
        this.currSelectedType = currSelectedType;
    }
    
    
}
