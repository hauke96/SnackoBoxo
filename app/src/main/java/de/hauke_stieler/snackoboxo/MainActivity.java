package de.hauke_stieler.snackoboxo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    IPaymentService _paymentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreference = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

        _paymentService = new PaymentServiceImpl(sharedPreference);
        _paymentService.DepositChangedEvent.add(value -> updateView(value));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.<Button>findViewById(R.id.Deposit10CentButton).setOnClickListener(v -> deposit(10));
        this.<Button>findViewById(R.id.Deposit20CentButton).setOnClickListener(v -> deposit(20));
        this.<Button>findViewById(R.id.Deposit50CentButton).setOnClickListener(v -> deposit(50));
        this.<Button>findViewById(R.id.Deposit1EuroButton).setOnClickListener(v -> deposit(100));
        this.<Button>findViewById(R.id.Deposit2EuroButton).setOnClickListener(v -> deposit(200));

        this.<Button>findViewById(R.id.BuyFor10CentButton).setOnClickListener(v -> buy(10));
        this.<Button>findViewById(R.id.BuyFor20CentButton).setOnClickListener(v -> buy(20));
        this.<Button>findViewById(R.id.BuyFor50CentButton).setOnClickListener(v -> buy(50));
        this.<Button>findViewById(R.id.BuyFor1EuroButton).setOnClickListener(v -> buy(100));
        this.<Button>findViewById(R.id.BuyFor2EuroButton).setOnClickListener(v -> buy(200));
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
