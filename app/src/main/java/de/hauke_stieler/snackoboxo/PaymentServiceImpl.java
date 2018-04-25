package de.hauke_stieler.snackoboxo;

import android.content.SharedPreferences;
import android.util.Log;

import juard.contract.Contract;

/**
 * Created by hauke on 25.04.18.
 *
 * This implementation of the payment service uses the shared preferences of the phone.
 */

public class PaymentServiceImpl implements IPaymentService {
    public static final String DEPOSIT_KEY = "cents";

    private SharedPreferences _sharedPreferences;

    public PaymentServiceImpl(SharedPreferences preferences) {
        Contract.NotNull(preferences);

        _sharedPreferences = preferences;
    }

    @Override
    public int getDeposit() {
        return _sharedPreferences.getInt(DEPOSIT_KEY, 0);
    }

    @Override
    public void deposit(int cent) {
        updateValue(getDeposit() + cent);
    }

    @Override
    public void buy(int cent) {
        updateValue(getDeposit() - cent);
    }

    private void updateValue(int newCents) {
        Log.d("UPDATE VALUE", "new value: " + newCents);

        _sharedPreferences.edit().putInt(DEPOSIT_KEY, newCents).commit();

        DepositChangedEvent.fireEvent(newCents);
    }
}
