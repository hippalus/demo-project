package org.example.demo.coincapclient;

import lombok.RequiredArgsConstructor;
import org.example.demo.coincapclient.rest.adapter.AssetsAdapter;
import org.example.demo.coincapclient.rest.adapter.CandlesAdapter;
import org.example.demo.coincapclient.rest.adapter.ExchangesAdapter;
import org.example.demo.coincapclient.rest.adapter.MarketsAdapter;
import org.example.demo.coincapclient.rest.adapter.RatesAdapter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoinCap {

  private final AssetsAdapter assetsAdapter;
  private final RatesAdapter ratesAdapter;
  private final ExchangesAdapter exchangesAdapter;
  private final MarketsAdapter marketsAdapter;
  private final CandlesAdapter candlesAdapter;


  public AssetsAdapter assets() {
    return assetsAdapter;
  }

  public RatesAdapter rates() {
    return ratesAdapter;
  }

  public ExchangesAdapter exchanges() {
    return exchangesAdapter;
  }

  public MarketsAdapter markets() {
    return marketsAdapter;
  }

  public CandlesAdapter candles() {
    return candlesAdapter;
  }
}
