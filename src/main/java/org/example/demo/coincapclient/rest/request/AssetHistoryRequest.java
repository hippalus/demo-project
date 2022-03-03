package org.example.demo.coincapclient.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetHistoryRequest {

  //m1, m5, m15, m30, h1, h2, h6, h12, d1
  private String interval;
  private Long start;
  private Long end;
}
