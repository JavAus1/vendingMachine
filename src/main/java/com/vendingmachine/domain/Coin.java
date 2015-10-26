package com.vendingmachine.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
public class Coin {
    private CoinType coinType;
}
