package org.example.demo.coincapclient.adapter;

import java.util.Set;
import org.assertj.core.api.Assertions;
import org.example.demo.coincapclient.rest.adapter.AssetsAdapter;
import org.example.demo.coincapclient.rest.request.AssetHistoryRequest;
import org.example.demo.coincapclient.rest.request.AssetMarketsRequest;
import org.example.demo.coincapclient.rest.request.AssetsRetrieveRequest;
import org.example.demo.coincapclient.rest.response.AssetHistoryResponse;
import org.example.demo.coincapclient.rest.response.AssetMarketsResponse;
import org.example.demo.coincapclient.rest.response.AssetsResponse;
import org.example.demo.coincapclient.rest.response.MonoAssetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AssetsResponseAdapterTest extends BaseIntegrationTest {


  @Autowired
  private AssetsAdapter assetsAdapter;

  @Test
  void shouldRetrieveAssets() {
    //given:
    final var retrieveRequest = new AssetsRetrieveRequest();
    //when:
    final AssetsResponse assets = assetsAdapter.retrieve(retrieveRequest);
    //then:
    Assertions.assertThat(assets).isNotNull();
    Assertions.assertThat(assets.getData()).isNotNull();
    Assertions.assertThat(assets.getData()).isNotEmpty();
  }

  @Test
  void shouldRetrieveAssetsByQueryParameters() {
    //given:
    final AssetsRetrieveRequest request = AssetsRetrieveRequest.builder()
        .offset(1)
        .limit(2000)
        .build();
    //when:
    final AssetsResponse assets = assetsAdapter.retrieve(request);
    //then:
    Assertions.assertThat(assets).isNotNull();
    Assertions.assertThat(assets.getData()).isNotNull();
    Assertions.assertThat(assets.getData().size()).isEqualTo(2000);
  }

  @Test
  void shouldRetrieveAssetsByQueryParameters2() {
    //given:
    final AssetsRetrieveRequest request = AssetsRetrieveRequest.builder()
        .offset(2000)
        .limit(2000)
        .build();
    //when:
    final AssetsResponse assets = assetsAdapter.retrieve(request);
    //then:
    Assertions.assertThat(assets).isNotNull();
    Assertions.assertThat(assets.getData()).isNotNull();
    Assertions.assertThat(assets.getData()).isNotEmpty();
  }

  @Test
  void shouldRetrieveAssetsByQueryParameters3() {
    //given:
    final AssetsRetrieveRequest request = AssetsRetrieveRequest.builder()
        .ids(Set.of("bitcoin", "ethereum"))
        .build();
    //when:
    final AssetsResponse assets = assetsAdapter.retrieve(request);
    //then:
    Assertions.assertThat(assets).isNotNull();
    Assertions.assertThat(assets.getData()).isNotNull();
    Assertions.assertThat(assets.getData()).isNotEmpty();
    Assertions.assertThat(assets.getData().size()).isEqualTo(2);
  }

  @Test
  void shouldRetrieveAssetsByQueryParameters4() {
    //given:
    final AssetsRetrieveRequest request = AssetsRetrieveRequest.builder()
        .offset(4000)
        .limit(2000)
        .build();
    //when:
    final AssetsResponse assets = assetsAdapter.retrieve(request);
    //then:
    Assertions.assertThat(assets).isNotNull();
    Assertions.assertThat(assets.getData()).isEmpty();
  }

  @Test
  void shouldRetrieveAssetsByQueryParameters5() {
    //given:
    final AssetsRetrieveRequest request = AssetsRetrieveRequest.builder()
        .search("bitcoin")
        .build();
    //when:
    final AssetsResponse assets = assetsAdapter.retrieve(request);
    //then:
    Assertions.assertThat(assets).isNotNull();
    Assertions.assertThat(assets.getData()).isNotNull();
    Assertions.assertThat(assets.getData()).isNotEmpty();
  }

  @Test
  void shouldRetrieveAssetById() {
    //given:
    final var id = "bitcoin";
    //when:
    final MonoAssetResponse asset = assetsAdapter.retrieveById(id);
    //then:
    Assertions.assertThat(asset).isNotNull();
    Assertions.assertThat(asset.getData()).isNotNull();
    Assertions.assertThat(asset.getData()).hasNoNullFieldsOrProperties();
  }

  @Test
  void shouldRetrieveAssetsHistory() {
    //given:
    final var id = "bitcoin";
    final AssetHistoryRequest historyRequest = AssetHistoryRequest.builder()
        .interval("m1")
        .build();
    //when:
    final AssetHistoryResponse history = assetsAdapter.retrieveHistory(id, historyRequest);
    //then:
    Assertions.assertThat(history).isNotNull();
    Assertions.assertThat(history.getData()).isNotNull();
    Assertions.assertThat(history.getData()).isNotEmpty();
  }

  @Test
  void shouldRetrieveAssetMarkets() {
    //given:
    final var id = "bitcoin";
    final AssetMarketsRequest marketsRequest = AssetMarketsRequest.builder().build();
    //when:
    final AssetMarketsResponse markets = assetsAdapter.retrieveAssetMarkets(id, marketsRequest);
    //then:
    Assertions.assertThat(markets).isNotNull();
    Assertions.assertThat(markets.getData()).isNotNull();
    Assertions.assertThat(markets.getData()).isNotEmpty();
  }
}
