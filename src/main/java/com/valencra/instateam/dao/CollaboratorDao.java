package com.valencra.instateam.dao;

import com.valencra.instateam.model.Collaborator;

import java.util.List;

public interface CollaboratorDao {
  List<Collaborator> findAll();
  Collaborator findById(Long id);
  void save(Collaborator collaborator);
  void delete(Collaborator collaborator);
}
