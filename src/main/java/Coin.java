/**
 * Created by jmohammed on 10/24/15.
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
/*
@Getter
@Setter
@EqualsAndHashCode*/
public class Coin {
    private CoinType coinType;

    public CoinType getCoinType() {
        return coinType;
    }

    public void setCoinType(CoinType coinType) {
        this.coinType = coinType;
    }
}
