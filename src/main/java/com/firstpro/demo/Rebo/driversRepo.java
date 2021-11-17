package com.firstpro.demo.Rebo;

import com.firstpro.demo.model.Drivers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface driversRepo extends CrudRepository<Drivers, Integer> {

   /* @Query("SELECT p FROM Drivers p WHERE p.name LIKE %?1%"
            + " OR p.brand LIKE %?1%"
            + " OR p.madein LIKE %?1%"
            + " OR CONCAT(p.price, '') LIKE %?1%")*/
    @Query("SELECT u FROM drivers u WHERE u.PhoneNum LIKE %?1%")
    public List<Drivers> search(String keyword);

    @Query("select u from drivers u where u.Driver_name like %?1%" +
            "OR u.PhoneNum like %?1%" +
            "OR u.SimNum like %?1%")
    List<Drivers> findForPagination(String Driver_name, Pageable pageable);

    @Query(value = "SELECT COUNT(u) FROM drivers u where u.Driver_name like %?1%")
    int findAllDriers(String Driver_name);

 @Query("select u from drivers u where u.Driver_name like %?1%" )
 List<Drivers> findForAdd(String Driver_name);

 @Query(value = "SELECT COUNT(u) FROM drivers u where u.SimNum like %?1%")
 List<Drivers> findForChart(int SimNum);

 @Query(value = "SELECT count(SimNum) as sim,dateOfInsertion as  dat FROM drivers group by dateOfInsertion\n ",nativeQuery = true)
 List<Object[]> findForChart1();


}
