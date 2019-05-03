package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.stock.fetch.model.StockMySubSelectedType;

@Mapper
public interface StockMySubSelectedTypeMapper {

    int insert(StockMySubSelectedType record);

    int deleteById(Long id);

    List<StockMySubSelectedType> selectByPid(Long pid);

}