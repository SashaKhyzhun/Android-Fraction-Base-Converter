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
                tvBinary.setText("");
                tvOctal.setText("");
                tvDecimal.setText("");
                tvHex.setText("");
            }
        });

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double number = Double.parseDouble (etValue.getText().toString());
                int    count  = Integer.parseInt   (etCount.getText().toString());

                tvBinary  .setText(convertToBinary  (number, count));
                tvOctal   .setText(convertToOctal   (number, count));
                tvDecimal .setText(convertToDecimal (number, count));
                tvHex     .setText(convertToHex     (number, count));

            }
        });
//        int value = 228;

//        String intToHex = Integer.toHexString(value);
//        String intToBinary = Integer.toBinaryString(value);
//        String intToOctal = Integer.toOctalString(value);
//        String intToDecimal = String.valueOf(value);


//        System.out.println("228 in binary  == " + intToBinary);   // 2
//        System.out.println("228 in octal   == " + intToOctal);    // 8
//        System.out.println("228 in decimal == " + intToDecimal);  // 10
//        System.out.println("228 in hex     == " + intToHex);      // 16
//        System.out.println("");

//        System.out.println("base2:  convertToBinary   2.28 == " + convertToBinary  (12.3125, 60));
//        System.out.println("base8:  convertToOctal    2.28 == " + convertToOctal   (12.3125, 60));
//        System.out.println("base10: convertToDecimal  2.28 == " + convertToDecimal (12.3125, 60));
//        System.out.println("base16: convertToHex      2.28 == " + convertToHex     (12.3125, 60));

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
