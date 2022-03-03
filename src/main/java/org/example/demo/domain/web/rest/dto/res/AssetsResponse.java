package org.example.demo.domain.web.rest.dto.res;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetsResponse {

  private List<AssetResponse> data;
}
