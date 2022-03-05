package org.example.demo.repository;

import java.util.List;
import org.example.demo.model.Asset;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetJdbcRepository extends PagingAndSortingRepository<Asset, Long> {

  @Modifying
  @Query("DELETE FROM ASSET WHERE NAME=:name")
  void deleteByName(@Param("name") String name);

  @Query("SELECT *  FROM ASSET ORDER BY ID DESC LIMIT :n")
  List<Asset> findLastN(@Param("n") Integer size);

  boolean existsByName(String name);


}
