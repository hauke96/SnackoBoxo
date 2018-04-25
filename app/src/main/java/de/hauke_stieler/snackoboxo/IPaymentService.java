package de.hauke_stieler.snackoboxo;

import juard.event.DataEvent;

/**
 * Created by hauke on 25.04.18.
 */

public interface IPaymentService {
    DataEvent<Integer> DepositChangedEvent = new DataEvent<>();

    void deposit(int cent);

    void buy(int cent);
}
