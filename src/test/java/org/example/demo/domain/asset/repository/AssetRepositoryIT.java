package org.example.demo.domain.asset.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.Assertions;
import org.example.demo.IT;
import org.example.demo.domain.asset.model.Asset;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@IT
@Sql(scripts = "classpath:sql/assets.sql", executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
@Sql(scripts = "classpath:sql/cleanup.sql", executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
class AssetRepositoryIT {

  @Autowired
  private AssetJdbcRepository assetJdbcRepository;

  @Autowired
  private CustomAssetRepository customAssetRepository;

  @Test
  void deleteByName() {
    List<Asset> assets = assetJdbcRepository.findByName("BNB");
    assertThat(assets).isNotEmpty();

    assetJdbcRepository.deleteByName("BNB");

    List<Asset> afterDelete = assetJdbcRepository.findByName("BNB");
    assertThat(afterDelete).isEmpty();
  }

  @Test
  void findLastN() {
    assertThat(assetJdbcRepository.findLastN(2)).hasSize(2);
  }

  @Test
  void existsByName() {
    assertThat(assetJdbcRepository.existsByName("Tether")).isTrue();
  }

  @Test
  void batchInsert() {
    List<Asset> assets = new ArrayList<>();
    for (int i = 0; i < 100000; i++) {
      assets.add(createRandomRecord());
    }
    customAssetRepository.batchInsert(assets);

    Assertions.assertThat(assetJdbcRepository.count()).isGreaterThanOrEqualTo(10000);
  }

  private Asset createRandomRecord() {
    return Asset.builder()
        .assetId(RandomString.make())
        .changePercent24Hr(randomDecimal())
        .explorer(RandomString.make())
        .marketCapUsd(randomDecimal())
        .maxSupply(randomDecimal())
        .name(RandomString.make())
        .priceUsd(randomDecimal())
        .supply(randomDecimal())
        .volumeUsd24Hr(randomDecimal())
        .vwap24Hr(randomDecimal())
        .symbol(RandomString.make())
        .build();
  }

  @NotNull
  private BigDecimal randomDecimal() {
    return BigDecimal.valueOf(RandomGenerator.getDefault().nextDouble());
  }
}