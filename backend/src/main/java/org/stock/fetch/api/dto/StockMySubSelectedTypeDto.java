package org.stock.fetch.api.dto;

import java.util.Date;

import com.aeasycredit.commons.lang.base.BaseDto;

public class StockMySubSelectedTypeDto extends BaseDto {
    /** 注释内容 */
    private static final long serialVersionUID = -8525636804532784524L;

    private String id;

    private String pid;

    private String name;

    private Date createDate;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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