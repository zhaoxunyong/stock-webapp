package org.stock.fetch.model;

import java.util.Date;

import com.aeasycredit.commons.lang.base.BaseModel;

public class StockDailyTransactionsHistory extends BaseModel {
    /** 注释内容 */
    private static final long serialVersionUID = -6436270467129296001L;

    private Long id;

    private String fileMd5;

    private Date createDate;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}