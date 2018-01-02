package com.valencra.instateam.converter;

import com.valencra.instateam.dao.RoleDao;
import com.valencra.instateam.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RoleConverter implements Converter<String, Role> {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role convert(String id) {
        return roleDao.findById(new Long(id));
    }

    @Bean
    public ConversionService getConversionService() {
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new RoleConverter());
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean.getObject();
    }
}
