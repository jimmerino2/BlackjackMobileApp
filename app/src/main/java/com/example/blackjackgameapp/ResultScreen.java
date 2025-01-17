package com.example.blackjackgameapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class ResultScreen extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnReplay), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Display Logo Bar Code
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);  // Enable the title display
        getSupportActionBar().setTitle("Blackjack Game");   // Set the title

        // Get the Object and pass values
        PlayerRoundInformation round1 = (PlayerRoundInformation) getIntent().getSerializableExtra("roundInfo");
        TextView betAmountTextView = findViewById(R.id.postBetAmount);
        TextView remainingAmountTextView = findViewById(R.id.postAmount);
        TextView txtTitle = findViewById(R.id.txtTitle);

        if(round1.getDealerWin()){
            txtTitle.setText("You Lose...");
        }
        else if(round1.getPlayerWin()){
            txtTitle.setText("You Win");
        }
        else if(round1.getDraw()){
            txtTitle.setText("Draw");
        }


        int betAmount = round1.getBetAmount();
        betAmountTextView.setText("You bet: $" + betAmount);
        int remainingAmount = round1.getRemainingAmount();
        remainingAmountTextView.setText("You have: $" + remainingAmount + " left");

        Button btnNewGame = findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(v -> {
            Intent selectBetAmount = new Intent(ResultScreen.this, SelectBetAmount.class);
            selectBetAmount.putExtra("roundInfo", round1);
            startActivity(selectBetAmount);
        });
    }
}
