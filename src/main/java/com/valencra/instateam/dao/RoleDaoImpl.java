package com.valencra.instateam.dao;

import com.valencra.instateam.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Role> findAll() {
    return null;
  }

  @Override
  public Role findById(Long id) {
    return null;
  }
}
