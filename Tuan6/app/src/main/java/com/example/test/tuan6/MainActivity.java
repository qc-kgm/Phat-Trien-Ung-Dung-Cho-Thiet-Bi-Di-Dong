package com.example.test.tuan6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText mssv;
    private EditText ns;
    private EditText diachi;
    private EditText email;
    private EditText sdt;
    private RadioGroup gt;
    private CheckBox thethao,dulich,amnhac;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.hoten);
        mssv =findViewById(R.id.mssv);
        ns=findViewById(R.id.ns);
        diachi=findViewById(R.id.diachi);
        email=findViewById(R.id.mail);
        sdt=findViewById(R.id.phone);
        gt=findViewById(R.id.gt);
        thethao=findViewById(R.id.sttt);
        dulich=findViewById(R.id.stdl);
        amnhac=findViewById(R.id.stan);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
            }
        });
    }
    void checkInput(){
        if (name.getText().toString().isEmpty() || mssv.getText().toString().isEmpty() || ns.getText().toString().isEmpty()
                ||sdt.getText().toString().isEmpty() || email.getText().toString().isEmpty()   ) {
            Toast.makeText(this,"Cần nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
        }
    }

}