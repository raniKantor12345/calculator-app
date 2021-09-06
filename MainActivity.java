package com.example.test1app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });

    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else{
            display.setText(String.format("%S%S%S", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }



    }

    public void zeroBTN(View view){
        updateText("0");

    }

    public void oneBTN(View view){
        updateText("1");

    }

    public void twoBTN(View view){
        updateText("2");

    }

    public void threeBTN(View view){
        updateText("3");

    }

    public void fourBTN(View view){
        updateText("4");

    }

    public void fiveBTN(View view){
        updateText("5");

    }

    public void sixBTN(View view){
        updateText("6");

    }

    public void sevenBTN(View view){
        updateText("7");

    }

    public void eightBTN(View view){
        updateText("8");

    }

    public void nineBTN(View view){
        updateText("9");

    }

    public void addBTN(View view){
        updateText("+");

    }

    public void subtractBTN(View view){
        updateText("-");

    }

    public void multiplyBTN(View view){
        updateText("*");

    }

    public void divideBTN(View view){
        updateText("/");

    }

    public void clearBTN(View view){
        display.setText("");

    }

    public void equalsBTN(View view){
        String userExp = display.getText().toString();
        String result = doExe(userExp);
        display.setText(result);
        display.setSelection(result.length());
    }

    public static String doExe(String expression){
        return creatList(expression);
    }


    public static String creatList(String expression)
    {
        boolean moreThanOne = false;
        if(expression.contains("+") || expression.contains("-")
                || expression.contains("*") ||expression.contains("/"))
        {
            moreThanOne = true;
        }

        ArrayList<String> expressionList = new ArrayList<String>();
        if(moreThanOne)
        {
            String chunck;
            int i, start = 0, end = 0;

            for(i = 0 ; i < expression.length(); i++)
            {
                if(Character.isDigit(expression.charAt(i)))
                {
                    end = i + 1;
                }
                else
                {
                    chunck = expression.substring(start, end);
                    expressionList.add(chunck);
                    expressionList.add(Character.toString(expression.charAt(end)));
                    start = end + 1;
                    end = end + 2;
                }
            }
            chunck = expression.substring(start, end);
            expressionList.add(chunck);
        }
        else
        {
            expressionList.add(expression);
        }

        return result(expressionList);

    }




    public static String result(ArrayList<String> expressionList)
    {
        try{
            while(expressionList.contains("*") || expressionList.contains("/"))
            {
                int operationPlace;
                int multiIndex, diviIndex;
                String num1, num2;
                multiIndex = expressionList.indexOf("*");
                diviIndex = expressionList.indexOf("/");

                if(multiIndex == -1)
                {
                    num1 = expressionList.get(diviIndex - 1);
                    num2 = expressionList.get(diviIndex + 1);
                    String result = division(num1, num2);

                    expressionList.remove(diviIndex - 1);
                    expressionList.remove(diviIndex);
                    expressionList.remove(diviIndex - 1);
                    expressionList.add(diviIndex - 1, result);
                }
                else
                {
                    if(diviIndex == -1)
                    {
                        num1 = expressionList.get(multiIndex - 1);
                        num2 = expressionList.get(multiIndex + 1);
                        String result = multiplication(num1, num2);

                        expressionList.remove(multiIndex - 1);
                        expressionList.remove(multiIndex);
                        expressionList.remove(multiIndex - 1);
                        expressionList.add(multiIndex - 1, result);


                    }
                    else
                    {
                        if(multiIndex < diviIndex)
                        {
                            num1 = expressionList.get(multiIndex - 1);
                            num2 = expressionList.get(multiIndex + 1);
                            String result = multiplication(num1, num2);

                            expressionList.remove(multiIndex - 1);
                            expressionList.remove(multiIndex);
                            expressionList.remove(multiIndex - 1);
                            expressionList.add(multiIndex - 1, result);

                        }
                        else
                        {
                            num1 = expressionList.get(diviIndex - 1);
                            num2 = expressionList.get(diviIndex + 1);
                            String result = division(num1, num2);

                            expressionList.remove(diviIndex - 1);
                            expressionList.remove(diviIndex);
                            expressionList.remove(diviIndex - 1);
                            expressionList.add(diviIndex - 1, result);
                        }
                    }
                }
            }


            while(expressionList.contains("+") || expressionList.contains("-"))
            {
                int operationPlace;
                int addIndex, subIndex;
                String num1, num2;
                addIndex = expressionList.indexOf("+");
                subIndex = expressionList.indexOf("-");

                if(addIndex == -1)
                {
                    num1 = expressionList.get(subIndex - 1);
                    num2 = expressionList.get(subIndex + 1);
                    String result = subtraction(num1, num2);

                    expressionList.remove(subIndex - 1);
                    expressionList.remove(subIndex);
                    expressionList.remove(subIndex - 1);
                    expressionList.add(subIndex - 1, result);
                }
                else
                {
                    if(subIndex == -1)
                    {
                        num1 = expressionList.get(addIndex - 1);
                        num2 = expressionList.get(addIndex + 1);
                        String result = addition(num1, num2);

                        expressionList.remove(addIndex - 1);
                        expressionList.remove(addIndex);
                        expressionList.remove(addIndex - 1);
                        expressionList.add(addIndex - 1, result);


                    }
                    else
                    {
                        if(addIndex < subIndex)
                        {
                            num1 = expressionList.get(addIndex - 1);
                            num2 = expressionList.get(addIndex + 1);
                            String result = addition(num1, num2);

                            expressionList.remove(addIndex - 1);
                            expressionList.remove(addIndex);
                            expressionList.remove(addIndex - 1);
                            expressionList.add(addIndex - 1, result);

                        }
                        else
                        {
                            num1 = expressionList.get(subIndex - 1);
                            num2 = expressionList.get(subIndex + 1);
                            String result = subtraction(num1, num2);

                            expressionList.remove(subIndex - 1);
                            expressionList.remove(subIndex);
                            expressionList.remove(subIndex - 1);
                            expressionList.add(subIndex - 1, result);
                        }
                    }
                }
            }

            return expressionList.get(0);
        }
        catch(Exception e){
            return "Error";
        }
    }

    public static String multiplication(String num1, String num2)
    {
        double num11, num22;
        num11 = Double.parseDouble(num1);
        num22 = Double.parseDouble(num2);
        double result = num11 * num22;

        return String.valueOf(result);

    }
    public static String division(String num1, String num2)
    {
        double num11, num22;
        num11 = Double.parseDouble(num1);
        num22 = Double.parseDouble(num2);
        double result = num11 / num22;

        return String.valueOf(result);

    }
    public static String addition(String num1, String num2)
    {
        double num11, num22;
        num11 = Double.parseDouble(num1);
        num22 = Double.parseDouble(num2);
        double result = num11 + num22;
        return String.valueOf(result);

    }
    public static String subtraction(String num1, String num2)
    {
        double num11, num22;
        num11 = Double.parseDouble(num1);
        num22 = Double.parseDouble(num2);
        double result = num11 - num22;
        return String.valueOf(result);

    }


}