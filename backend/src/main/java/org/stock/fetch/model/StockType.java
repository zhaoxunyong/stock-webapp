package org.stock.fetch.model;

import java.util.Date;

import org.springframework.data.annotation.Transient;

import com.aeasycredit.commons.lang.base.BaseModel;

public class StockType extends BaseModel {
    /** 注释内容 */
    private static final long serialVersionUID = -3459215883680669442L;

    private Long id;

    private String name;
    
    private String url;

    private Integer type;

    private Boolean status;

    private Date createDate;

    private Date updateDate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}