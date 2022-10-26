package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bpoint,bdiv,bmulti,bplus,begal,bmoins,breset;
    TextView resultat;
    String chres;
    private double chif1 = 0;
    private String op = "";
    private boolean clickop = false;
    private boolean upchif = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b0=(Button) findViewById(R.id.b0);
        b1=(Button) findViewById(R.id.b1);
        b2=(Button) findViewById(R.id.b2);
        b3=(Button) findViewById(R.id.b3);
        b4=(Button) findViewById(R.id.b4);
        b5=(Button) findViewById(R.id.b5);
        b6=(Button) findViewById(R.id.b6);
        b7=(Button) findViewById(R.id.b7);
        b8=(Button) findViewById(R.id.b8);
        b9=(Button) findViewById(R.id.b9);
        bpoint=(Button) findViewById(R.id.bpoint);
        bdiv=(Button) findViewById(R.id.bdiv);
        bmulti=(Button) findViewById(R.id.bmulti);
        bplus=(Button) findViewById(R.id.bplus);
        begal=(Button) findViewById(R.id.begal);
        bmoins=(Button) findViewById(R.id.bmoins);
        breset=(Button) findViewById(R.id.breset);
        resultat=(TextView) findViewById(R.id.resultat);
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickchif("0");
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickchif("1");

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickchif("2");

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickchif("3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickchif("4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickchif("5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickchif("6");

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickchif("7");

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickchif("8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickchif("9");
            }
        });
        bpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickchif(".");

            }
        });
        bdiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickoperation("/");

            }
        });
        bmulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickoperation("*");

            }
        });
        bplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickoperation("+");

            }
        });
        bmoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickoperation("-");

            }
        });
        begal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double newchif;
                newchif=Double.valueOf(resultat.getText().toString()).doubleValue();
                calcul(newchif);
                upchif = false;
                clickop = false;
            }
        });
        breset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickop = false;
                upchif = false;
                chif1 = 0;
                op ="";
                resultat.setText("0");
            }
        });


    }
    public void clickchif(String newchif) {


        String ch=resultat.getText().toString();
        int count =ch.length()-ch.replace(".", "").length();

        if(upchif){
            upchif = false;
        }else{
                if(count==1 && newchif==".") {
                    Toast.makeText(MainActivity.this, "You can't put other . ", Toast.LENGTH_SHORT).show();
                    newchif = resultat.getText() +"";
                }
                else  {
                    if(resultat.getText().toString()!="0")
                    newchif = resultat.getText() + newchif;
                }
        }
        resultat.setText(newchif);
    }
    public void clickoperation(String ope) {
        double newchif;
        if(clickop){
            newchif=Double.valueOf(resultat.getText().toString()).doubleValue();
            calcul(newchif);
            resultat.setText(String.valueOf(chif1));
        }else{
            chif1 = Double.valueOf(resultat.getText().toString()).doubleValue();
            clickop = true;
        }
        op = ope;
        upchif = true;
    }
    private void calcul(Double newchif) {
        if (op.equals("+")) {
            chif1 = chif1 + newchif;
            resultat.setText(String.valueOf(chif1));
        }

        if(op.equals("-")){
            chif1 = chif1 - newchif;
            resultat.setText(String.valueOf(chif1));
        }

        if(op.equals("*")){
            chif1 = chif1 * newchif;
            resultat.setText(String.valueOf(chif1));
        }

        if(op.equals("/")){
            if(newchif != 0){
                chif1 = chif1 / newchif;
                resultat.setText(String.valueOf(chif1));
            }else{
                chif1 = 0;
                resultat.setText("Error Division sur 0 Press C");
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("result", resultat.getText().toString());
        myEdit.apply();
        Toast.makeText(MainActivity.this, "You Paused", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String ch = sh.getString("result", "");
        resultat.setText(ch);
        Toast.makeText(MainActivity.this, "You Back ", Toast.LENGTH_SHORT).show();
    }
}