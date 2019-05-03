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
    
    StockMySubSelected select(@Param("stockId") Long stockId, @Param("subSelectedType") Long subSelectedType);

    int update(@Param("stockId") Long stockId, @Param("subSelectedType") Long subSelectedType);
    
    int delete(@Param("subSelectedType") Long subSelectedType);
    
    int deleteByStockId(@Param("stockId") Long stockId,@Param("subSelectedType") Long subSelectedType);
    
    int removeOneStockMySelected(@Param("stockId") Long stockId, @Param("subSelectedType") Long subSelectedType);

    List<StockMySubSelected> selectAll();
    
    List<StockMySubSelected> selectByType(@Param("type") Long type);

    int updateByPrimaryKey(StockMySubSelected record);
}