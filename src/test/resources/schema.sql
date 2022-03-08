drop table if exists asset CASCADE;
create table asset
(
    id                  bigint generated by default as identity,
    asset_id            varchar(255),
    rank                varchar(255),
    symbol              varchar(255),
    name                varchar(255),
    supply              numeric ,
    max_supply          numeric,
    market_cap_usd      numeric,
    volume_usd24_hr     numeric,
    price_usd           numeric,
    change_percent24_hr numeric,
    vwap24_hr           numeric,
    explorer            varchar(255),
    created_at          TIMESTAMP WITHOUT TIME ZONE not null,
    updated_at          TIMESTAMP WITHOUT TIME ZONE,
    primary key (id)
);

DROP INDEX if exists idx_asset_name ON asset;
CREATE INDEX idx_asset_name ON asset (name);