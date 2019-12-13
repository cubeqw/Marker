package com.cubeqw.marker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {
    String s="", temp;
    EditText edit;
    Button clear;
    int click_count=0;
    TextView result_view, view1, view2, view3, view4, view5, viewn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit=findViewById(R.id.edit);
        result_view=findViewById(R.id.result);
        view1=findViewById(R.id.one_count);
        view2=findViewById(R.id.two_count);
        view3=findViewById(R.id.three_count);
        view4=findViewById(R.id.four_count);
        view5=findViewById(R.id.five_count);
        viewn=findViewById(R.id.empty_count);
        clear=findViewById(R.id.clear);
        clear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                s="";
                edit.setText("Marker");
                Toast.makeText(getApplicationContext(), "Оценки сброшены", Toast.LENGTH_SHORT).show();
                result_view.setText("↓");
                view1.setText("");
                view2.setText("");
                view3.setText("");
                view4.setText("");
                view5.setText("");
                viewn.setText("");
                return false;
            }
        });
    }
    public void onClickMark(View v){
        int id=v.getId();
        switch(id){
            case R.id.one:s=s+"1";break;
            case R.id.two:s=s+"2";break;
            case R.id.three:s=s+"3";break;
            case R.id.four:s=s+"4";break;
            case R.id.five:s=s+"5";break;
            case R.id.empty:s=s+"н";break;
        }
        edit.setText(s);
        edit.setSelection(s.length());
        math();
    }
    public void onClickClear(View v){
        if(s.length()>1){
            click_count++;
            if (click_count==3)Toast.makeText(getApplicationContext(), "Хотите стереть всё? Удержите кнопку", Toast.LENGTH_LONG).show();
        s=s.substring(0, s.length()-1);
        edit.setText(s);
        edit.setSelection(s.length());}
        else{
            s="";
            result_view.setText("↓");
            view1.setText("");
            view2.setText("");
            view3.setText("");
            view4.setText("");
            view5.setText("");
            viewn.setText("");
            edit.setText("Marker");
        }
        math();
    }
    public void math(){
        int count_n=0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='н')count_n++;
        }
        temp=s.replaceAll("н", "");
        double sum=0;
        int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
        int count5=0;
        for (int i = 0; i < temp.length(); i++) {
            if(1==Integer.parseInt(String.valueOf(temp.charAt(i)))) count1++;
            if(2==Integer.parseInt(String.valueOf(temp.charAt(i)))) count2++;
            if(3==Integer.parseInt(String.valueOf(temp.charAt(i)))) count3++;
            if(4==Integer.parseInt(String.valueOf(temp.charAt(i)))) count4++;
            if(5==Integer.parseInt(String.valueOf(temp.charAt(i)))) count5++;
            sum=sum+Integer.parseInt(String.valueOf(temp.charAt(i)));
        }
        if(count1+count2+count3+count4+count5+count_n!=0){
            view1.setText(count1+"");
            view2.setText(count2+"");
            view3.setText(count3+"");
            view4.setText(count4+"");
            view5.setText(count5+"");
            viewn.setText(count_n+"");
        }
        double result=sum/temp.length();
        String print= String.valueOf(result);
        if(!print.equals("NaN")){
            int color= getResources().getColor(R.color.colorPrimaryDark);
            if(result>=1) color=getResources().getColor(R.color.one);
            if(result>=2) color=getResources().getColor(R.color.two);
            if(result>=3) color=getResources().getColor(R.color.three);
            if(result>=4) color=getResources().getColor(R.color.four);
            if(result>=5) color=getResources().getColor(R.color.five);
            result_view.setTextColor(color);
            if(print.length()>5)
            result_view.setText(print.substring(0, 4));
        else result_view.setText(print);
    }else result_view.setText("↓");
    }
}
