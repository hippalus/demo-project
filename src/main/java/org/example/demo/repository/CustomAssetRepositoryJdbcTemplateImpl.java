package org.example.demo.repository;

import com.google.common.collect.Iterables;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.demo.model.Asset;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAssetRepositoryJdbcTemplateImpl implements CustomAssetRepository {

  private static final String SQL_ASSET_INSERT = """
      INSERT INTO "ASSET"  
      ("ASSET_ID", "CHANGE_PERCENT24_HR","CREATED_AT", "EXPLORER", "MARKET_CAP_USD", "MAX_SUPPLY",
      "NAME","PRICE_USD", "RANK", "SUPPLY", "SYMBOL","UPDATED_AT", "VOLUME_USD24_HR","VWAP24_HR")
      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
      """;
  private static final int INSERT_BATCH_SIZE = 25;
  private final JdbcTemplate jdbcTemplate;

  @Override
  public void saveAllBatch(List<Asset> assetList) {
    Iterable<List<Asset>> partitions = Iterables.partition(assetList, INSERT_BATCH_SIZE);
    partitions.forEach(this::save);
  }

  private void save(List<Asset> assets) {
    jdbcTemplate.batchUpdate(SQL_ASSET_INSERT, new BatchPreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps, int i) throws SQLException {
        Asset asset = assets.get(i);
        ps.setString(1, asset.getAssetId());
        ps.setBigDecimal(2, asset.getChangePercent24Hr());
        ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(4, asset.getExplorer());
        ps.setBigDecimal(5, asset.getMarketCapUsd());
        ps.setBigDecimal(6, asset.getMaxSupply());
        ps.setString(7, asset.getName());
        ps.setBigDecimal(8, asset.getPriceUsd());
        ps.setString(9, asset.getRank());
        ps.setBigDecimal(10, asset.getSupply());
        ps.setString(11, asset.getSymbol());
        ps.setTimestamp(12, null);
        ps.setBigDecimal(13, asset.getVolumeUsd24Hr());
        ps.setBigDecimal(14, asset.getVwap24Hr());
      }

      @Override
      public int getBatchSize() {
        return assets.size();
      }

    });
  }
}
