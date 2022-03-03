package org.example.demo.coincapclient.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonoAssetResponse {

  private AssetResponse data;
  private Long timestamp;
}
