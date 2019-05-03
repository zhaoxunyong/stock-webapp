package org.stock.fetch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.stock.fetch.model.StockMySubSelected;

@Mapper
public interface StockMySubSelectedMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockMySubSelected record);

    StockMySubSelected selectByPrimaryKey(Long id);
    
    StockMySubSelected select(@Param("stockId") Long stockId, @Param("selectedType") Long selectedType);

    int update(@Param("stockId") Long stockId, @Param("selectedType") Long selectedType);
    
    int delete(@Param("selectedType") Long selectedType);
    
    int deleteByStockId(@Param("stockId") Long stockId,@Param("selectedType") Long selectedType);
    
    int removeOneStockMySelected(@Param("stockId") Long stockId, @Param("selectedType") Long selectedType);

    List<StockMySubSelected> selectAll();
    
    List<StockMySubSelected> selectByType(@Param("type") Long type);

    int updateByPrimaryKey(StockMySubSelected record);
}