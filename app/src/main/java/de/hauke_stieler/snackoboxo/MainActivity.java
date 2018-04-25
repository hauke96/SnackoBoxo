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

        final Button button = findViewById(R.id.Pay10CentButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pay(10);
            }
        });
    }

    private void pay(int cent){

    }
}
