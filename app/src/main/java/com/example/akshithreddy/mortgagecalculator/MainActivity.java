package com.example.akshithreddy.mortgagecalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText loanAmount, interestRate, monthlyAmount, totalAmount,loanDate;
    private SeekBar seekBar;
    private TextView percentage;
    Loan loan = new Loan();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeViews();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    public void initializeViews() {
        loanAmount = (EditText) findViewById(R.id.loanET);
        interestRate = (EditText) findViewById(R.id.interestET);
        seekBar = (SeekBar) findViewById(R.id.year_seekbar);
        percentage = (TextView) findViewById(R.id.percentageTV);
        monthlyAmount = (EditText) findViewById(R.id.monthET);
        totalAmount = (EditText) findViewById(R.id.totalET);
        loanDate= (EditText) findViewById(R.id.loandatET);
        loanDate.setText(loan.getLoanDate().toString());
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        loanAmount.addTextChangedListener(loanTextWatcher);
        interestRate.addTextChangedListener(interestTextWatcher);


    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            percentage.setText(seekBar.getProgress() + "yrs");
            loan.setNumberOfYears(seekBar.getProgress());
            update();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    TextWatcher loanTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                loan.setLoanAmount(Double.parseDouble(s.toString()));
            } catch (NumberFormatException e) {
                loan.setLoanAmount(1000);
            }
            update();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    TextWatcher interestTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                loan.setAnnualInterestRate(Double.parseDouble(s.toString()));
            } catch (NumberFormatException e) {
                loan.setAnnualInterestRate(2.5);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void update() {
        monthlyAmount.setText("$" + String.format("%.0f", loan.getMonthlyPayment()));
        totalAmount.setText("$" + String.format("%.0f", loan.getTotalPayment()));


    }


}
