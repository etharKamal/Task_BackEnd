package com.firstpro.demo.Rebo;

import com.firstpro.demo.model.login;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface loginRebo extends CrudRepository<login, Integer> {

        @Query("select u from login u where u.UserName = ?1 and password = ?2 and isActive=true")
        Optional<login> findByUser (String UserName, String password);

       /* @Query("select u from login u where u.UserName like %?1%")
        List<login> findForPagination(String UserName, Pageable pageable);*/

        @Query(value = "SELECT COUNT(u) FROM login u where u.UserName like %?1% ")
        int findAllUsers(String UserName);
}