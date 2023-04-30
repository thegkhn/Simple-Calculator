package com.dugo.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {

    private Button num0, num1, num2, num3, num4, num5, num6
            , num7, num8, num9;
    private Button clear, divide, multiply, minus, plus,
            equal, dot, percent;
    private TextView display;
    private String currentInput = "";
    private String lastOperation = "";
    double operand1;
    double operand2;
    double result;
    private boolean isOperationClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        num0 = findViewById(R.id.num0);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);
        clear = findViewById(R.id.clear);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        equal = findViewById(R.id.equal);
        dot = findViewById(R.id.dot);
        percent = findViewById(R.id.percent);
        display = findViewById(R.id.display);

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOperationClicked) {
                    currentInput = "";
                    isOperationClicked = false;
                }
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                currentInput += buttonText;
                display.setText(currentInput);
            }
        };

        View.OnClickListener operationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                if (lastOperation.isEmpty()) {
                    operand1 = Float.parseFloat(display.getText().toString());
                    lastOperation = buttonText;
                    isOperationClicked = true;

                }else{
                    operand2 = Float.parseFloat(display.getText().toString());
                    result = calculate(operand1, operand2, lastOperation);
                    display.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);
                }
            }
        };

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = "";
                lastOperation = "";
                isOperationClicked = false;
                display.setText("0");
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!lastOperation.isEmpty()) {
                    operand2 = Float.parseFloat(display.getText().toString());
                    result = calculate(operand1, operand2, lastOperation);
                    display.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);
                    lastOperation = "";
                    isOperationClicked = true;
                }
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.contains(".")) {
                    currentInput += ".";
                    display.setText(currentInput);
                }
            }
        });

        num0.setOnClickListener(numberClickListener);
        num1.setOnClickListener(numberClickListener);
        num2.setOnClickListener(numberClickListener);
        num3.setOnClickListener(numberClickListener);
        num4.setOnClickListener(numberClickListener);
        num5.setOnClickListener(numberClickListener);
        num6.setOnClickListener(numberClickListener);
        num7.setOnClickListener(numberClickListener);
        num8.setOnClickListener(numberClickListener);
        num9.setOnClickListener(numberClickListener);

        divide.setOnClickListener(operationClickListener);
        multiply.setOnClickListener(operationClickListener);
        minus.setOnClickListener(operationClickListener);
        plus.setOnClickListener(operationClickListener);
        percent.setOnClickListener(operationClickListener);
    }
    private double calculate(double operand1, double operand2, String operation) {
        result = 0;
        switch (operation) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 != 0) {
                    result = operand1 / operand2;
                } else {
                    result = Float.NaN;
                    Toast.makeText(getApplicationContext(), "Divide by zero error!", Toast.LENGTH_SHORT).show();
                }
                break;
            case "%":
                result = operand1 * operand2 / 100;
                break;
        }
        return result;
    }
}
