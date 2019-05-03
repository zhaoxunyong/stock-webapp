package org.stock.fetch.model;

import java.util.Date;

import com.aeasycredit.commons.lang.base.BaseModel;

public class StockMySubSelectedType extends BaseModel {
    /** 注释内容 */
    private static final long serialVersionUID = 3820976885230254351L;

    private Long id;

    private Long pid;

    private String name;

    private Date createDate;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
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