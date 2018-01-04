package com.valencra.instateam.service;

import com.valencra.instateam.dao.RoleDao;
import com.valencra.instateam.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
  @Autowired
  private RoleDao roleDao;

  @Override
  public List<Role> findAll() {
    return roleDao.findAll();
  }

  @Override
  public Role findById(Long id) {
    return roleDao.findById(id);
  }

  @Override
  public void save(Role role) {
    roleDao.save(role);
  }

  @Override
  public void delete(Role role) {
    roleDao.delete(role);
  }
}
