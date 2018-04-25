package de.hauke_stieler.snackoboxo;

import android.content.SharedPreferences;
import android.util.Log;

import juard.contract.Contract;

/**
 * Created by hauke on 25.04.18.
 */

public class PaymentServiceImpl implements IPaymentService {
    private SharedPreferences _sharedPreferences;

    public PaymentServiceImpl(SharedPreferences preferences) {
        Contract.NotNull(preferences);

        _sharedPreferences = preferences;
    }

    @Override
    public void deposit(int cent) {
        updateValue(getCents() + cent);
    }

    @Override
    public void buy(int cent) {
        updateValue(getCents() - cent);
    }

    private int getCents() {
        return _sharedPreferences.getInt("cents", 0);
    }

    private void updateValue(int newCents) {
        Log.d("UPDATE VALUE", "new value: " + newCents);

        _sharedPreferences.edit().putInt("cents", newCents).commit();

        DepositChangedEvent.fireEvent(newCents);
    }
}
