/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.classifier;

// Imports ...

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;


@Transactional
public interface AdministradorDAO extends CrudRepository<Administrador, Long> {

  /**
   * This method will find an User instance in the database by its email.
   * Note that this method is not implemented and its working code will be
   * automagically generated from its signature by Spring Data JPA.
   */
  public Administrador findByEmail(String email);

}