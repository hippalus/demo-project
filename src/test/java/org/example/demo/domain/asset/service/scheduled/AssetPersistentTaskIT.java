package org.example.demo.domain.asset.service.scheduled;

import org.assertj.core.api.Assertions;
import org.example.demo.IT;
import org.example.demo.domain.asset.repository.AssetJdbcRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

@IT
@TestPropertySource(locations = "classpath:application.yml", properties = "app.scheduled.asset.enabled=true")
class AssetPersistentTaskIT {

  @Autowired
  AssetPersistentTask assetPersistentTask;

  @Autowired
  AssetJdbcRepository assetJdbcRepository;

  @Test
  void taskRun() {
    Assertions.assertThat(assetJdbcRepository.count()).isZero();
    assetPersistentTask.run();
    Assertions.assertThat(assetJdbcRepository.count()).isNotZero();
  }
}