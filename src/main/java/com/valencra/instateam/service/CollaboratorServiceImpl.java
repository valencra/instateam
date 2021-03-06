package com.valencra.instateam.service;

import com.valencra.instateam.dao.CollaboratorDao;
import com.valencra.instateam.model.Collaborator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CollaboratorServiceImpl implements CollaboratorService {
  @Autowired
  private CollaboratorDao collaboratorDao;

  @Override
  public List<Collaborator> findAll() {
    return collaboratorDao.findAll();
  }

  @Override
  public Collaborator findById(Long id) {
    return collaboratorDao.findById(id);
  }

  @Override
  public void save(Collaborator collaborator) {
    collaboratorDao.save(collaborator);
  }

  @Override
  public void delete(Collaborator collaborator) {
    collaboratorDao.delete(collaborator);
  }
}
