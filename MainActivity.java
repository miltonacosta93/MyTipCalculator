package com.example.mytipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private double billAmount = 0.0;
    private double percent = .15;
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private EditText myEditTextView;
    private TextView myPercentTextView;
    private SeekBar mySeekBar;
    private TextView tipLabel;
    private TextView totalLabel;
    private TextView tipViewAmount;
    private TextView totalViewAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myEditTextView = (EditText) findViewById(R.id.editText_BillAmount);
        myPercentTextView = (TextView) findViewById(R.id.percentTextView);
        mySeekBar = (SeekBar) findViewById(R.id.my_seek_bar);
        tipLabel = (TextView) findViewById(R.id.myTipLabel);
        totalLabel = (TextView) findViewById(R.id.myTotalLabel);
        tipViewAmount = (TextView) findViewById(R.id.myTipViewAmount);
        totalViewAmount = (TextView) findViewById(R.id.myTotalViewAmount);

        tipViewAmount.setText(currencyFormat.format(0));
        totalViewAmount.setText(currencyFormat.format(0));



        mySeekBar.setOnSeekBarChangeListener(seekBarListener);

      myEditTextView.addTextChangedListener(myAmountEditTextWatcher);


    }

    private void calculate()
    {
        myPercentTextView.setText(percentFormat.format(percent));

        double tip = billAmount * percent;
        double total = billAmount + tip;
        tipViewAmount.setText(currencyFormat.format(tip));
        totalViewAmount.setText(currencyFormat.format(total));



    }


    private final TextWatcher myAmountEditTextWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {
            try
            {



                billAmount = Double.parseDouble(charSequence.toString());
                totalViewAmount.setText(currencyFormat.format(billAmount));
            }catch (NumberFormatException e)
            {
                totalViewAmount.setText("");
                billAmount = 0.0;

            }
            calculate();

        }

        @Override
        public void afterTextChanged(Editable editable)
        {

        }
    };






    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener()
    {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
        {

            percent = progress / 100.0;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {

        }
    };






}
