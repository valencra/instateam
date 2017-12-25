package com.valencra.instateam.dao;

import com.valencra.instateam.model.Collaborator;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CollaboratorDaoImpl extends AbstractDao<Collaborator> implements CollaboratorDao {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Collaborator> findAll() {
    return null;
  }

  @Override
  public Collaborator findById(Long id) {
    return null;
  }
}
