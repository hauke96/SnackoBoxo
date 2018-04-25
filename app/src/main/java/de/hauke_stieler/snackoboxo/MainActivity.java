package de.hauke_stieler.snackoboxo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    IPaymentService _paymentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreference = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

        _paymentService = new PaymentServiceImpl(sharedPreference);
        _paymentService.ValueChangedEvent.add(value -> updateView(value));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.<Button>findViewById(R.id.Pay10CentButton).setOnClickListener(v -> pay(10));
        this.<Button>findViewById(R.id.Pay20CentButton).setOnClickListener(v -> pay(20));
        this.<Button>findViewById(R.id.Pay50CentButton).setOnClickListener(v -> pay(50));
        this.<Button>findViewById(R.id.Pay1EuroButton).setOnClickListener(v -> pay(100));
        this.<Button>findViewById(R.id.Pay2EuroButton).setOnClickListener(v -> pay(200));

        this.<Button>findViewById(R.id.BuyFor10CentButton).setOnClickListener(v -> buy(10));
        this.<Button>findViewById(R.id.BuyFor20CentButton).setOnClickListener(v -> buy(20));
        this.<Button>findViewById(R.id.BuyFor50CentButton).setOnClickListener(v -> buy(50));
        this.<Button>findViewById(R.id.BuyFor1EuroButton).setOnClickListener(v -> buy(100));
        this.<Button>findViewById(R.id.BuyFor2EuroButton).setOnClickListener(v -> buy(200));
    }

    private void pay(int cent) {
        _paymentService.deposit(cent);
    }

    private void buy(int cent) {
        _paymentService.buy(cent);
    }

    private void updateView(int cent) {
        this.<TextView>findViewById(R.id.CurrentValueLabel).setText(String.format("%.2f €", ((float) cent / 100.0f)));
    }
}
