package com.wordpress.milindkrohit.tttypingtest;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class log extends AppCompatActivity {
    private DBHelper mydb;
    private Button button;
    int id_To_Update;
    Cursor rs;
    private EditText editText1,editText2,editText3,editText4,editText5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Bundle extras = getIntent().getExtras();
        id_To_Update = extras.getInt("id");
        init_val();

        if(id_To_Update == 0){

            editText2.setHint("Phone");
            editText1.setHint("Name");
            editText3.setHint("email");
            editText4.setHint("Password(optional)");
            editText5.setHint("Place(optional)");


        }else if(id_To_Update == -1){

            editText1.setHint("");
            editText1.setVisibility(View.INVISIBLE);

            editText2.setHint("current password");
            editText2.setImeActionLabel("current password", R.id.login);
            editText2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText3.setHint("new password");
            editText3.setImeActionLabel("new password", R.id.login);
            editText3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            editText3.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText4.setHint("confirm password");
            editText2.setImeActionLabel("confirm password", R.id.login);

            editText5.setVisibility(View.INVISIBLE);
            button.setText("Change Password");
        }else{
            rs = mydb.getData(id_To_Update);
            rs.moveToFirst();
            editText4.setVisibility(View.INVISIBLE);
            editText2.setHint(rs.getString(rs.getColumnIndex(DBHelper.COLUMN_EMAIL)));
            editText3.setHint(rs.getString(rs.getColumnIndex(DBHelper.COLUMN_PHONE)));
            editText1.setHint(rs.getString(rs.getColumnIndex(DBHelper.COLUMN_NAME)));
            editText5.setHint(rs.getString(rs.getColumnIndex(DBHelper.COLUMN_CITY)));
            button.setText("Save Changes");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_action();
            }
        });
    }
    public void button_action(){
        String name,phone,email,password,place,current_password,new_password;
        if(id_To_Update == 0){
            name = editText1.getText().toString();
            phone = editText2.getText().toString();
            email = editText3.getText().toString();
            password = editText4.getText().toString();
            place = editText5.getText().toString();
            if(mydb.insertContact(name, phone, email, password, place)){
                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
            }

            else{
                Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getApplicationContext(),Start_Activity.class);
            startActivity(intent);
            finish();
        }else if(id_To_Update == -1){
            rs = mydb.getData(1);
            rs.moveToFirst();
            current_password = editText2.getText().toString();
            new_password = editText3.getText().toString();
            password = editText4.getText().toString();
            if(current_password.equals(rs.getString(rs.getColumnIndex(DBHelper.COLUMN_PASS)))){
                if(new_password.equals(password)){
                    if(mydb.updatepass(1,password)){
                        Toast.makeText(getApplicationContext(), "Password Succfully changed ", Toast.LENGTH_SHORT).show();
                        account();

                    }else{
                        Toast.makeText(getApplicationContext(), "not Updated \n try again", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "password not matched", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Current password is incorrect ", Toast.LENGTH_SHORT).show();
            }


        }else{
            name = editText1.getText().toString();
            phone = editText2.getText().toString();
            email = editText3.getText().toString();
            place = editText5.getText().toString();
            if(mydb.updateContact(id_To_Update,name, phone, email, place)){
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Start_Activity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void init_val(){
        mydb = new DBHelper(this);
        editText1 = (EditText)findViewById(R.id.edit1);
        editText2 = (EditText)findViewById(R.id.edit2);
        editText3 = (EditText)findViewById(R.id.edit3);
        editText4 = (EditText)findViewById(R.id.edit4);
        editText5 = (EditText)findViewById(R.id.edit5);
        button = (Button)findViewById(R.id.button_reg_user);
    }
    public void account(){
        Intent login = new Intent(this, Account.class);
        startActivity(login);
    }

}
