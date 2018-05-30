package de.hauke_stieler.snackoboxo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hauke on 25.04.18.
 * <p>
 * The app has only this activity, that's why it's called "MainActivity" ;)
 */
public class MainActivity extends AppCompatActivity {

    IPaymentService _paymentService;

    List<Integer> _moneyValues;
    private LinearLayout.LayoutParams _buttonLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreference = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

        _paymentService = new PaymentServiceImpl(sharedPreference);
        _paymentService.DepositChangedEvent.add(value -> updateView(value));

        //TODO read these from shared preferences. Create a service to access these (which handles default values etc.)
        _moneyValues = new ArrayList<>();
        _moneyValues.add(10);
        _moneyValues.add(20);
        _moneyValues.add(40);
        _moneyValues.add(50);
        _moneyValues.add(80);
        _moneyValues.add(100);
        _moneyValues.add(200);

        _buttonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        _buttonLayoutParams.setMargins(0, toPx(5), 0, toPx(5));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int value : _moneyValues) {
            addDepositButton(value);
            addBuyForButton(value);
        }

        updateView(_paymentService.getDeposit());
    }

    private void addDepositButton(int cents) {
        Button button = new Button(this);
        button.setLayoutParams(_buttonLayoutParams);
        button.setText("+ " + toCurrencyString(cents));
        button.setOnClickListener(v -> deposit(cents));

        this.<LinearLayout>findViewById(R.id.DepositButtonLayout).addView(button);
    }

    private void addBuyForButton(int cents) {
        Button button = new Button(this);
        button.setLayoutParams(_buttonLayoutParams);
        button.setText("- " + toCurrencyString(cents));
        button.setOnClickListener(v -> buy(cents));

        this.<LinearLayout>findViewById(R.id.BuyForButtonLayout).addView(button);
    }

    private String toCurrencyString(int cents) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(cents / 100.0);
    }

    private int toPx(int dpValue) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                dpValue,
                getResources().getDisplayMetrics());
    }

    private void deposit(int cent) {
        _paymentService.deposit(cent);
    }

    private void buy(int cent) {
        _paymentService.buy(cent);
    }

    private void updateView(int cent) {
        this.<TextView>findViewById(R.id.CurrentValueLabel).setText(String.format("%.2f €", ((float) cent / 100.0f)));
    }
}
