package com.valencra.instateam.converter;

import com.valencra.instateam.dao.CollaboratorDao;
import com.valencra.instateam.model.Collaborator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CollaboratorConverter implements Converter<String, Collaborator> {
  @Autowired
  private CollaboratorDao collaboratorDao;

  @Override
  public Collaborator convert(String id) {
    return collaboratorDao.findById(new Long(id));
  }

  @Bean
  public ConversionService getConversionService() {
    ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
    Set<Converter> converters = new HashSet<>();
    converters.add(new CollaboratorConverter());
    conversionServiceFactoryBean.setConverters(converters);
    return conversionServiceFactoryBean.getObject();
  }
}
