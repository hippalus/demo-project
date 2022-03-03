package org.example.demo.coincapclient.rest.request;

import javax.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetMarketsRequest {

  @Max(2000)
  private Integer limit;
  private Integer offset;
}