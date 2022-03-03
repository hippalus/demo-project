package org.example.demo.domain.repository;

import org.example.demo.domain.model.Asset;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends PagingAndSortingRepository<Asset, String> {

}
