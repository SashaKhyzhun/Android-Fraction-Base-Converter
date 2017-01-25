package com.sashakhyzhun.fractionbaseconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author sasha.khyzhun@gmail.com
 * Created by Sasha Khyzhun on 12/21/16.
 * Copyright © 2016. All rights reserved.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etValue = (EditText)findViewById(R.id.etValue);
        final EditText etCount = (EditText)findViewById(R.id.countAfterComma);

        Button buttonOk    = (Button)findViewById(R.id.buttonOk);
        Button buttonClear = (Button)findViewById(R.id.buttonClear);

        final TextView tvBinary  = (TextView)findViewById(R.id.answerBinary);
        final TextView tvOctal   = (TextView)findViewById(R.id.answerOctal);
        final TextView tvDecimal = (TextView)findViewById(R.id.answerDecimal);
        final TextView tvHex     = (TextView)findViewById(R.id.answerHex);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etValue.setText("");
                etCount.setText("");
                tvBinary.setText("0 1");
                tvOctal.setText("0 1 2 3 4 5 6 7");
                tvDecimal.setText("0 1 2 3 4 5 6 7 8 9");
                tvHex.setText("0 1 2 3 4 5 6 7 8 9 A B C D E F");
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double number;
                if (!etValue.getText().toString().equals("")) {
                    number = Double.parseDouble(etValue.getText().toString());
                } else {
                    number = 0;
                }

                int count;
                if (!etCount.getText().toString().equals("")) {
                    count = Integer.parseInt(etCount.getText().toString());
                } else {
                    count = 0;
                }

                tvBinary  .setText(convertToBinary  (number, count));
                tvOctal   .setText(convertToOctal   (number, count));
                tvDecimal .setText(convertToDecimal (number, count));
                tvHex     .setText(convertToHex     (number, count));

            }
        });

    }

    private String convertToBinary(double number, int countAfterComma) {
        BigDecimal bd = new BigDecimal(number);
        BigDecimal mult = new BigDecimal(2).pow(countAfterComma);
        bd = bd.multiply(mult);
        BigInteger bi = bd.toBigInteger();
        StringBuilder str = new StringBuilder(bi.toString(2));
        while (str.length() < countAfterComma+1) {  // +1 for leading zero
            str.insert(0, "0");
        }
        str.insert(str.length()-countAfterComma, ".");
        return str.toString();
    }

    private String convertToOctal(double number, int countAfterComma) {
        BigDecimal bd = new BigDecimal(number);
        BigDecimal mult = new BigDecimal(8).pow(countAfterComma);
        bd = bd.multiply(mult);
        BigInteger bi = bd.toBigInteger();
        StringBuilder str = new StringBuilder(bi.toString(8));
        while (str.length() < countAfterComma+1) {  // +1 for leading zero
            str.insert(0, "0");
        }
        str.insert(str.length()-countAfterComma, ".");
        return str.toString();
    }

    private String convertToHex(double number, int countAfterComma) {
        BigDecimal bd = new BigDecimal(number);
        BigDecimal mult = new BigDecimal(16).pow(countAfterComma);
        bd = bd.multiply(mult);
        BigInteger bi = bd.toBigInteger();
        StringBuilder str = new StringBuilder(bi.toString(16));
        while (str.length() < countAfterComma + 1) {  // +1 for leading zero
            str.insert(0, "0");
        }
        str.insert(str.length()-countAfterComma, ".");
        return str.toString();
    }

    private String convertToDecimal(double number, int countAfterComma) {
        BigDecimal decimal = new BigDecimal(number);
        BigDecimal multiply = new BigDecimal(10).pow(countAfterComma);

        decimal = decimal.multiply(multiply);
        BigInteger bigInteger = decimal.toBigInteger();

        StringBuilder sb = new StringBuilder(bigInteger.toString(10));

        while (sb.length() < countAfterComma + 1) {
            sb.insert(0, "0");
        }
        sb.insert(sb.length() - countAfterComma, ".");

        return sb.toString();

    }


}
