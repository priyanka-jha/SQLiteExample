package com.android.priyanka.sqliteexample.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.priyanka.sqliteexample.Database.DatabaseHelper;
import com.android.priyanka.sqliteexample.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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
    @BindView(R.id.spQualification)
    Spinner spQualification;
    @BindView(R.id.textDob)
    TextView textDob;
    @BindView(R.id.btnDob)
    ImageButton btnDob;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    String name, email, phone, hobbies, gender, qualification, dob;
    int quaId = 0;
    int count = 0, editId = 0;
    int cur = 0;
    String hobbies1, hobbies2, hobbies3, hobbies4;
    static final int START_DATE_DIALOG = 1;
    Calendar c;
    int year;
    int month;
    int day;
    @BindView(R.id.rgGender)


    RadioGroup rgGender;
    @BindView(R.id.btnList)
    Button btnList;
    private List<CheckBox> CheckBoxHobbies = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent i = getIntent();
        editId = i.getIntExtra("max_id", 0);
        quaId = i.getIntExtra("qua_id", 0);

        System.out.println("id" + editId + "quaId" + quaId);


        List<String> list = new ArrayList<String>();
        list.add("Select");
        list.add("B.Tech");
        list.add("M.Tech");
        list.add("PHD");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQualification.setAdapter(dataAdapter);


        CheckBoxHobbies.add(chkDance);
        CheckBoxHobbies.add(chkPaint);
        CheckBoxHobbies.add(chkRead);

        c = Calendar.getInstance();

        count = new DatabaseHelper(getApplicationContext()).countTable("details", "id=" + editId);
        if (count == 0) {


        } else {
            fillData();
        }


    }


    @OnClick({R.id.btnDob, R.id.btnSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnDob:
                /*    int mYear, mMonth, mDay, mHour, mMinute;
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    textDob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
    */
                cur = START_DATE_DIALOG;
                showDialog(START_DATE_DIALOG);

                break;
            case R.id.btnSubmit:
                submitData();
                break;
        }
    }


    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            if (cur == START_DATE_DIALOG) {
                textDob.setText(new StringBuilder()
                        .append(month + 1).append("/")
                        .append(day).append("/")
                        .append(year).append(""));

            }

        }
    };


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {

            case START_DATE_DIALOG:
                System.out.println("onCreateDialog  : " + id);
                cur = START_DATE_DIALOG;
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener, c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));


        }
        return null;
    }


    private void submitData() {

        DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dh.getWritableDatabase();

        name = edName.getText().toString();
        email = edEmail.getText().toString();
        phone = edPhone.getText().toString();
        qualification = spQualification.getSelectedItem().toString();
        quaId = spQualification.getSelectedItemPosition();
        // dob = "12/2/92";
        dob = textDob.getText().toString();

           /* if (chkDance.isChecked()) {
                hobbies1 = chkDance.getText().toString();
            }

            if (chkSing.isChecked()) {
                hobbies2 = chkSing.getText().toString();
            }

            if (chkPaint.isChecked()) {
                hobbies3 = chkPaint.getText().toString();
            }

            if (chkRead.isChecked()) {
                hobbies4 = chkRead.getText().toString();
            }

            hobbies = hobbies1 + hobbies2 + hobbies3 + hobbies4;*/

        hobbies = getCheckBoxText(CheckBoxHobbies);

           /* if (male.isChecked()) {
                gender = male.getText().toString();
            } else if (female.isChecked()) {
                gender = female.getText().toString();
            } else {
                gender = other.getText().toString();
            }
    */

        gender = getRadioGroupValue(rgGender);

        int max_id = 0;
        DatabaseHelper dbh1 = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db1 = dbh1.getWritableDatabase();
        Cursor cursor1 = db1.rawQuery("Select MAX(id) from details ;", null);
        if (cursor1.moveToFirst()) {
            do {
                max_id = cursor1.getInt(0);
            } while (cursor1.moveToNext());
            db1.close();
        }

        if (count == 0) {
            // if (max_id == 0) {
            max_id += 1;

            boolean cc = dh.insertData(name, email, phone, qualification, gender, hobbies, dob);

            if (cc == true) {
                System.out.println(name + email + phone + gender + hobbies + qualification + dob);


                Intent i = new Intent(MainActivity.this, ViewData.class);
                i.putExtra("max_id", max_id);
                i.putExtra("qua_id", quaId);

                startActivity(i);

                System.out.println("maxid" + max_id + "quaid" + quaId);


            } else {
                System.out.println("not submitted");


            }
            // }
        } else {

            boolean cc = dh.updateData(editId, name, email, phone, qualification, gender, hobbies, dob);
            if (cc == true) {
                Intent i = new Intent(MainActivity.this, ViewData.class);
                i.putExtra("max_id", editId);
                i.putExtra("qua_id", quaId);

                startActivity(i);
            }
        }


    }

    public void fillData() {

        DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dh.getWritableDatabase();

        DatabaseHelper dbh1 = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db1 = dbh1.getWritableDatabase();
        String query = "Select * from details where id=" + editId + " ;";
        System.out.println("query" + query);
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
        System.out.println("view.." + name + email + phone + gender + hobbies + qualification + dob);

        edName.setText(name);
        edEmail.setText(email);
        edPhone.setText(phone);
        textDob.setText(dob);
        spQualification.setSelection(quaId);
        setCheckBox(CheckBoxHobbies, hobbies);
        setRadioGroupValue(rgGender, gender);


    }

    public String getCheckBoxText(List<CheckBox> textCheckBoxList) {
        String scheckbox = "";
        try {
            for (CheckBox textCheckBox : textCheckBoxList) {
                String chktext = textCheckBox.getText().toString();
                if (scheckbox != "") {
                    if (textCheckBox.isChecked()) {
                        scheckbox = scheckbox + "!" + chktext;
                    } else {
                        scheckbox = scheckbox + "!" + "null";
                    }
                } else {
                    if (textCheckBox.isChecked()) {
                        scheckbox = "" + chktext;
                    } else {
                        scheckbox = "" + "null";
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error code: f1472", Toast.LENGTH_SHORT).show();
            System.out.println("Food  EXCEPTION f1472==" + e);
        }
        return scheckbox;
    }

    public String getRadioGroupValue(RadioGroup rg) {
        String value = "";
        try {
            value = ((RadioButton) this.findViewById(rg.getCheckedRadioButtonId())).getText().toString();
        } catch (NullPointerException e) {
            value = "";
        }
        return value;
    }


    public void setCheckBox(List<CheckBox> textCheckBoxList, String cktext) {
        try {
            String scheckbox[] = cktext.split("!");
            int i = 0;
            for (CheckBox textCheckBox : textCheckBoxList) {
                //System.out.println("i= "+i+" "+textCheckBox.getText().toString()+" scheckbox[i]= "+scheckbox[i]);
                if (scheckbox[i].equals("null")) {
                    textCheckBox.setChecked(false);
                } else {
                    textCheckBox.setChecked(true);
                }
                i++;
            }
        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), "Error code: f1428", Toast.LENGTH_SHORT).show();
            System.out.println("Food EXCEPTION f1428==" + e);
        }
    }


    public void setRadioGroupValue(RadioGroup rg, String value) {
        System.out.println("setRadioGroupValue :" + value);
        for (int i = 0; i < rg.getChildCount(); i++) {
            RadioButton radio_button = (RadioButton) rg.getChildAt(i);
            int id = radio_button.getId();
            if (radio_button.getText().toString().equals(value)) {
                radio_button.setChecked(true);
                return;
            } else {
                //System.out.println("setRadioGroupValue :"+value+"   X   "+radio_button.getText().toString());
            }
        }
    }


    public void openList(View view) {

        Intent i = new Intent(getApplicationContext(),ListActivity.class);
        startActivity(i);
    }
}