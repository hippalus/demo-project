package org.example.demo.domain.asset.web.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

import java.util.List;
import org.example.demo.AbstractRestIT;
import org.example.demo.IT;
import org.example.demo.common.rest.ErrorResponse;
import org.example.demo.common.rest.Response;
import org.example.demo.domain.asset.web.rest.dto.response.AssetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@IT
@Sql(scripts = "classpath:sql/assets.sql", executionPhase = BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
@Sql(scripts = "classpath:sql/cleanup.sql", executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
class AssetControllerIT extends AbstractRestIT {

  private final ParameterizedTypeReference<Response<List<AssetResponse>>> assetsResponseType = new ParameterizedTypeReference<>() {
  };

  @Test
  void shouldRetrieveLast_N_asset() {
    //given
    final String path = "/api/v1/assets?lastN=2";

    //when
    ResponseEntity<Response<List<AssetResponse>>> responseEntity = testRestTemplate.exchange(path, HttpMethod.GET,
        new HttpEntity<>(createHttpHeaders()), assetsResponseType);

    //then - assert response
    assertThat(responseEntity).isNotNull().returns(HttpStatus.OK, from(ResponseEntity::getStatusCode));

    //then - assert data
    assertThat(responseEntity.getBody()).isNotNull();
    assertThat(responseEntity.getBody().data()).isNotNull();
    assertThat(responseEntity.getBody().data()).hasSize(2);
  }


  @SuppressWarnings("rawtypes")
  @Test
  void shouldHandleWithErrorResponseWhenRetrieveLast_N_assetByInvalidParams() {
    //given
    final String path = "/api/v1/assets?lastN";

    //when
    ResponseEntity<Response> responseEntity = testRestTemplate.exchange(path, HttpMethod.GET,
        new HttpEntity<>(createHttpHeaders()),
        Response.class);

    //then - assert response
    assertThat(responseEntity).isNotNull().returns(HttpStatus.BAD_REQUEST, from(ResponseEntity::getStatusCode));

    //then - assert data
    assertThat(responseEntity.getBody()).isNotNull();
    assertThat(responseEntity.getBody().errors()).isNotNull()
        .returns("Required request parameter 'lastN' for method parameter type Integer is present but converted to null",
            from(ErrorResponse::errorDescription))
        .returns("23", from(ErrorResponse::errorCode));
  }
  
  @SuppressWarnings("rawtypes")
  @Test
  void shouldHandleWithErrorResponseWhenRetrieveLast_N_assetByInvalidParams2() {
    //given
    final String path = "/api/v1/assets?lastN=-1";

    //when
    ResponseEntity<Response> responseEntity = testRestTemplate.exchange(path, HttpMethod.GET,
        new HttpEntity<>(createHttpHeaders()),
        Response.class);

    //then - assert response
    assertThat(responseEntity).isNotNull().returns(HttpStatus.BAD_REQUEST, from(ResponseEntity::getStatusCode));

    //then - assert data
    assertThat(responseEntity.getBody()).isNotNull();
    assertThat(responseEntity.getBody().errors()).isNotNull()
        .returns(
            "org.example.demo.domain.asset.web.rest.controller.AssetController retrieveLastNAssets.size: must be greater than 0",
            from(ErrorResponse::errorDescription))
        .returns("22", from(ErrorResponse::errorCode));
  }

  @Test
  void shouldDeleteAssetByName() {
    //given
    final String path = "/api/v1/assets/Ethereum";

    //when
    ResponseEntity<Void> responseEntity = testRestTemplate.exchange(path, HttpMethod.DELETE,
        new HttpEntity<>(createHttpHeaders()), Void.class);

    //then - assert response
    assertThat(responseEntity).isNotNull().returns(HttpStatus.OK, from(ResponseEntity::getStatusCode));
  }

  @Test
  void shouldHandleNotFoundDataByDoesntExistName() {
    //given
    final String path = "/api/v1/assets/HabipHakan";

    //when
    var responseEntity = testRestTemplate.exchange(path, HttpMethod.DELETE,
        new HttpEntity<>(createHttpHeaders()), Response.class);

    //then - assert response
    assertThat(responseEntity).isNotNull()
        .returns(HttpStatus.UNPROCESSABLE_ENTITY, from(ResponseEntity::getStatusCode));

    assertThat(responseEntity.getBody()).isNotNull();
    assertThat(responseEntity.getBody().errors()).isNotNull()
        .returns("HabipHakan could not be found.", from(ErrorResponse::errorDescription))
        .returns("15", from(ErrorResponse::errorCode));
  }
}