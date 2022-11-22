package com.karlaru.seccrm;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    @Query(value="SELECT c FROM Contact c WHERE  LOWER(c.name)      LIKE LOWER(CONCAT('%', :search,'%')) OR " +
                                                "LOWER(c.codeName)  LIKE LOWER(CONCAT('%', :search,'%')) OR " +
                                                "LOWER(c.phone)     LIKE LOWER(CONCAT('%', :search,'%'))")
    List<Contact> findByAnything(@Param("search") String search);
}
