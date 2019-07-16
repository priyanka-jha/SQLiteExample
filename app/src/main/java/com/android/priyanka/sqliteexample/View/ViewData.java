package com.android.priyanka.sqliteexample.View;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.priyanka.sqliteexample.Database.DatabaseHelper;
import com.android.priyanka.sqliteexample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewData extends AppCompatActivity {

    @BindView(R.id.edName)
    EditText edName;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edPhone)
    EditText edPhone;
    @BindView(R.id.chkDance)
    CheckBox chkDance;
    @BindView(R.id.chkPaint)
    CheckBox chkPaint;
    @BindView(R.id.chkRead)
    CheckBox chkRead;
    @BindView(R.id.male)
    RadioButton male;
    @BindView(R.id.female)
    RadioButton female;
    @BindView(R.id.other)
    RadioButton other;
    @BindView(R.id.rgGender)
    RadioGroup rgGender;
    @BindView(R.id.spQualification)
    TextView spQualification;
    @BindView(R.id.textDob)
    TextView textDob;
    @BindView(R.id.btnEdit)
    Button btnEdit;

    String name, email, phone, hobbies, gender, qualification, dob;
    int quaId = 0,id = 0;

    private List<CheckBox> CheckBoxHobbies = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);
        ButterKnife.bind(this);

        Intent i = getIntent();
        id = i.getIntExtra("max_id",0);
        quaId = i.getIntExtra("qua_id",0);

        System.out.println("id"+id+"quaId"+quaId);

        CheckBoxHobbies.add(chkDance);
        CheckBoxHobbies.add(chkPaint);
        CheckBoxHobbies.add(chkRead);

        DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dh.getWritableDatabase();

        DatabaseHelper dbh1 = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db1 = dbh1.getWritableDatabase();
        String query = "Select * from details where id=" + id + " ;";
        System.out.println("query"+query);
        Cursor cursor1 = db1.rawQuery(query, null);


        if (cursor1.moveToFirst()) {
            do {
          name = cursor1.getString(1);
          email = cursor1.getString(2);
          phone = cursor1.getString(3);
          qualification = cursor1.getString(4);
          gender = cursor1.getString(5);
          hobbies = cursor1.getString(6);
          dob = cursor1.getString(7);




            } while (cursor1.moveToNext());
            db1.close();
        }
        System.out.println("view.."+name + email + phone + gender + hobbies + qualification + dob);

        edName.setText(name);
        edEmail.setText(email);
        edPhone.setText(phone);
        textDob.setText(dob);
        spQualification.setText(qualification);
        setCheckBox(CheckBoxHobbies, hobbies);
        setRadioGroupValue(rgGender,gender);


    }

    public void setCheckBox(List<CheckBox> textCheckBoxList, String cktext) {
        try {
            String scheckbox[] = cktext.split("!");
            int i = 0;
            for (CheckBox textCheckBox : textCheckBoxList) {
                if (scheckbox[i].equals("null")) {
                    textCheckBox.setChecked(false);
                } else {
                    textCheckBox.setChecked(true);
                }
                textCheckBox.setClickable(false);
                textCheckBox.setEnabled(false);
                i++;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error code: f1428", Toast.LENGTH_SHORT).show();
            System.out.println("Food EXCEPTION f1428==" + e);
        }

    }

    public void setRadioGroupValue(RadioGroup rg, String value) {
        //System.out.println("setRadioGroupValue :"+value);
        for (int i = 0; i < rg.getChildCount(); i++) {
            if (rg.getChildAt(i) instanceof RadioButton) {
                try {
                    RadioButton radio_button = (RadioButton) rg.getChildAt(i);
                    int id = radio_button.getId();
                    if (radio_button.getText().toString().equals(value)) {
                        radio_button.setChecked(true);

                    } else {
                        radio_button.setChecked(false);
                    }
                    radio_button.setClickable(false);
                } catch (ClassCastException e) {
                    System.out.println("err bv647" + e.toString());
                }
            }

        }
    }

    @OnClick(R.id.btnEdit)
    public void onViewClicked() {

        Intent i =new Intent(ViewData.this, MainActivity.class);
        i.putExtra("max_id",id);
        i.putExtra("qua_id",quaId);
        startActivity(i);
    }
}
