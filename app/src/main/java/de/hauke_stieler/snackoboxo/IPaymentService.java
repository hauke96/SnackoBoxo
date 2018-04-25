package de.hauke_stieler.snackoboxo;

import juard.event.DataEvent;

/**
 * Created by hauke on 25.04.18.
 *
 * This will persist the deposit and offers an interface to change it.
 */

public interface IPaymentService {
    DataEvent<Integer> DepositChangedEvent = new DataEvent<>();

    int getDeposit();

    void deposit(int cent);

    void buy(int cent);
}
