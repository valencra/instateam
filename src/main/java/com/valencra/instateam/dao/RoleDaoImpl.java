package com.valencra.instateam.dao;

import com.valencra.instateam.model.Role;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Role> findAll() {
    Session session = sessionFactory.openSession();
    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
    criteriaQuery.from(Role.class);
    List<Role> roles = session.createQuery(criteriaQuery).getResultList();
    session.close();
    return roles;
  }

  @Override
  public Role findById(Long id) {
    Session session = sessionFactory.openSession();
    Role role = session.get(Role.class, id);
    Hibernate.initialize(role.getCollaborators());
    Hibernate.initialize(role.getProjects());
    session.close();
    return role;
  }
}
