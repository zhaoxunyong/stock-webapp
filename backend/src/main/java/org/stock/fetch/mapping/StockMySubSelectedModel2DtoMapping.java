package org.stock.fetch.mapping;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;
import org.stock.fetch.api.dto.StockMySelectedDto;
import org.stock.fetch.api.dto.StockMySubSelectedDto;
import org.stock.fetch.model.StockMySelected;
import org.stock.fetch.model.StockMySubSelected;

import com.aeasycredit.commons.lang.utils.ObjectsUtils;
import com.aeasycredit.commons.modelmapper.PropertyMapConfigurerSupport;

/**
 * 合同Dto轉Modle<br>
 * 
 * @author Evan.fang
 * @version [版本号, 2017年8月28日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class StockMySubSelectedModel2DtoMapping extends PropertyMapConfigurerSupport<StockMySubSelected, StockMySubSelectedDto>{

    @Override
    public PropertyMap<StockMySubSelected, StockMySubSelectedDto> mapping() {
        
        return new PropertyMap<StockMySubSelected, StockMySubSelectedDto>(){

            @Override
            protected void configure() {
            	map().setId(String.valueOf(source.getId()));
            	map().setSubSelectedType(String.valueOf(source.getSubSelectedType()));
                /*map().setUserId(LongUtils.string2Long(source.getUserId()));
                map().setLoanRequestNo(LongUtils.string2Long(source.getLoanRequestNo()));
                map().setRequestId(LongUtils.string2Long(source.getRequestId()));
                using((MappingContext<String, Date> context) -> {
                    return DatesUtils.DDMMYYHHMMSS.toDate(context.getSource());
                }).map(source.getApplyDate(), destination.getApplyDate());
                
                using((MappingContext<String, Date> context) -> {
                    return DatesUtils.DDMMYY.toDate(context.getSource());
                }).map(source.getValidDate(), destination.getValidDate());
                
                using((MappingContext<String, Date> context) -> {
                    return DatesUtils.DDMMYY.toDate(context.getSource());
                }).map(source.getStartDate(), destination.getStartDate());
                
                using((MappingContext<String, Date> context) -> {
                    return DatesUtils.DDMMYY.toDate(context.getSource());
                }).map(source.getEndDate(), destination.getEndDate());*/
                
            }
            
        };
    }
    
}
