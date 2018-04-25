package de.hauke_stieler.snackoboxo;

import android.content.SharedPreferences;

import juard.event.DataEvent;

/**
 * Created by hauke on 25.04.18.
 */

public interface IPaymentService {
    DataEvent<Integer> ValueChangedEvent = new DataEvent<>();

    void deposit(int cent);

    void buy(int cent);
}
