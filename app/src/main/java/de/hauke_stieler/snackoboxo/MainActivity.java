package de.hauke_stieler.snackoboxo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    private void pay(int cent){

    }

    private void buy(int cent){

    }
}
