package com.example.shrutej.calculator;

import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,badd,bsubtract,bmultiply,bdivide,bsin,bcos,btan,blog,bopenbracket,bclosebracket,bsquareroot,bpower,bdot,bequal,brecall,bsave,bclear,ballclear,bhistory;
    private TextView t1;
    private String toParse;
    private double result;
    private String memory;
    private List<String> hisss = new ArrayList<>();
    private String hisss_store = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView) findViewById(R.id.textView);

        b0 = (Button) findViewById(R.id.zero);
        b1 = (Button) findViewById(R.id.one);
        b2 = (Button) findViewById(R.id.two);
        b3 = (Button) findViewById(R.id.three);
        b4 = (Button) findViewById(R.id.four);
        b5 = (Button) findViewById(R.id.five);
        b6 = (Button) findViewById(R.id.six);
        b7 = (Button) findViewById(R.id.seven);
        b8 = (Button) findViewById(R.id.eight);
        b9 = (Button) findViewById(R.id.nine);
        bdot = (Button) findViewById(R.id.dot);
        bequal = (Button) findViewById(R.id.equal);

        badd = (Button) findViewById(R.id.add);
        bsubtract = (Button) findViewById(R.id.subtract);
        bmultiply = (Button) findViewById(R.id.multiply);
        bdivide = (Button) findViewById(R.id.divide);

        bsin = (Button) findViewById(R.id.sin);
        bcos = (Button) findViewById(R.id.cos);
        btan = (Button) findViewById(R.id.tan);
        blog = (Button) findViewById(R.id.log);

        brecall = (Button) findViewById(R.id.recall);
        bsave = (Button) findViewById(R.id.save);
        bhistory = (Button) findViewById(R.id.history);
        bclear = (Button) findViewById(R.id.clear);
        ballclear = (Button) findViewById(R.id.allclear);

        bopenbracket = (Button) findViewById(R.id.openbracket);
        bclosebracket = (Button) findViewById(R.id.closebracket);
        bsquareroot = (Button) findViewById(R.id.squareroot);
        bpower = (Button) findViewById(R.id.power);

        toParse = "";
        result = 0;
        memory = "";

    }

    public void inputNumber(View view) {
        int id = view.getId();
        switch (id){
            case R.id.zero:
                toParse+= "0";
                t1.setText(toParse);
                break;

            case R.id.one:
                toParse+= "1";
                t1.setText(toParse);
                break;

            case R.id.two:
                toParse+= "2";
                t1.setText(toParse);
                break;

            case R.id.three:
                toParse+= "3";
                t1.setText(toParse);
                break;

            case R.id.four:
                toParse+= "4";
                t1.setText(toParse);
                break;

            case R.id.five:
                toParse+= "5";
                t1.setText(toParse);
                break;

            case R.id.six:
                toParse+= "6";
                t1.setText(toParse);
                break;

            case R.id.seven:
                toParse+= "7";
                t1.setText(toParse);
                break;

            case R.id.eight:
                toParse+= "8";
                t1.setText(toParse);
                break;

            case R.id.nine:
                toParse+= "9";
                t1.setText(toParse);
                break;

            case R.id.dot:
                toParse+= ".";
                t1.setText(toParse);
                break;

            case R.id.equal:
                result = eval(toParse);
                t1.setText(Double.toString(result));
                hisss.add(toParse+"="+result);
                toParse = "";
                break;

            case R.id.add:
                toParse+= "+";
                t1.setText(toParse);
                break;

            case R.id.subtract:
                toParse+= "-";
                t1.setText(toParse);
                break;

            case R.id.multiply:
                toParse+= "*";
                t1.setText(toParse);
                break;

            case R.id.divide:
                toParse+= "/";
                t1.setText(toParse);
                break;

            case R.id.sin:
                toParse+= "sin";
                t1.setText(toParse);
                break;

            case R.id.cos:
                toParse+= "cos";
                t1.setText(toParse);
                break;

            case R.id.tan:
                toParse+= "tan";
                t1.setText(toParse);
                break;

            case R.id.log:
                toParse+= "log";
                t1.setText(toParse);
                break;

            case R.id.openbracket:
                toParse+= "(";
                t1.setText(toParse);
                break;

            case R.id.closebracket:
                toParse+= ")";
                t1.setText(toParse);
                break;

            case R.id.squareroot:
                toParse+= "√";
                t1.setText(toParse);
                break;

            case R.id.power:
                toParse+= "^";
                t1.setText(toParse);
                break;

            case R.id.recall:
                toParse += memory ;
                t1.setText(toParse);
                break;

            case R.id.save:
                memory = Double.toString(result);
                Log.d("Error", memory);
                break;

            case R.id.clear:
                toParse = " ";
                t1.setText(toParse);
                break;

            case R.id.allclear:
                toParse = " ";
                t1.setText(toParse);
                hisss.clear();
                break;

            case R.id.history:
                for (int i = 0; i < hisss.size(); i++) {
                        hisss_store += hisss.get(i) + "\n";
                    }
                    t1.setText(hisss_store);
                hisss_store = "";
                }

        }



    private double eval(final String toParse) {
        return  new Object(){
            int pos=-1, ch;
            void nextChar(){
                ch = (++pos < toParse.length()) ? toParse.charAt(pos) : -1;
            }

            double parse(){
                nextChar();
                double x = parseExpression();
                if (pos < toParse.length()) t1.setText("Error");
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for( ; ; ){
                    if (eat('+')) x+=parseTerm();
                    else if (eat('-')) x-=parseTerm();
                    else return x;
                }
            }

            boolean eat(char c) {
                while (ch == ' ') nextChar();
                if (ch == c)
                {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parseTerm() {
                double x = parseFactor();
                for ( ; ; ){
                    if (eat('*')) x*= parseFactor();
                    else if (eat('/')) x/= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();
                double x = 0;
                double t = 0;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                }
                else if ((ch >= '0' && ch <='9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch=='.') nextChar();
                    x = Double.parseDouble(toParse.substring(startPos, this.pos));
                }

                else if (ch >= 'a' && ch <='z') {
                    while (ch>= 'a' && ch<='z') nextChar();

                    String func = toParse.substring(startPos, this.pos);
                    x = parseFactor();
                    t = parseFactor();
                    if (func.equals("√")) x = Math.sqrt(x);
                    if (func.equals("sin")) x=Math.sin(Math.toRadians(x));
                    if (func.equals("cos")) x=Math.cos(Math.toRadians(x));
                    if (func.equals("tan")) x=Math.tan(Math.toRadians(x));
                    if (func.equals("log")) x=Math.log10(x);
                }
                if (eat('^')) x=Math.pow(x,parseFactor());
                return x;
            }
        }.parse();
    }
}
