package org.stock.fetch.mapping;

import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import org.stock.fetch.api.dto.StockMyDataDto;
import org.stock.fetch.model.StockMyData;

import com.aeasycredit.commons.modelmapper.PropertyMapConfigurerSupport;

/**
 * 合同Model轉Dto類<br>
 * 
 * @author Weng.weng
 * @version [版本号, Jun 14, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class StockMyDataModel2DtoMapping extends PropertyMapConfigurerSupport<StockMyData, StockMyDataDto> {

    @Override
    public PropertyMap<StockMyData, StockMyDataDto> mapping() {
        return new PropertyMap<StockMyData, StockMyDataDto>() {
            @Override
            protected void configure() {

                map().setId(String.valueOf(source.getId()));
                map().setStockId(String.valueOf(source.getStockId()));
                using((MappingContext<String, String[]> context) -> {
                	if(context.getSource() == null) {
                		return new String[]{};
                	}
                    return context.getSource().split(",");
                }).map(source.getSelectedTypes(), destination.getSelectedTypes());
                /*map().setUserId(String.valueOf(source.getUserId()));
                map().setLoanRequestNo(String.valueOf(source.getLoanRequestNo()));
                map().setRequestId(String.valueOf(source.getRequestId()));
                using((MappingContext<Date, String> context) -> {
                    return DatesUtils.DDMMYYHHMMSS.toString(context.getSource());
                }).map(source.getApplyDate(), destination.getApplyDate());
                
                using((MappingContext<Date, String> context) -> {
                    return DatesUtils.DDMMYY.toString(context.getSource());
                }).map(source.getValidDate(), destination.getValidDate());
                
                using((MappingContext<Date, String> context) -> {
                    return DatesUtils.DDMMYY.toString(context.getSource());
                }).map(source.getStartDate(), destination.getStartDate());
                
                using((MappingContext<Date, String> context) -> {
                    return DatesUtils.DDMMYY.toString(context.getSource());
                }).map(source.getEndDate(), destination.getEndDate());*/
            }
        };
    }
    
    
    
}
