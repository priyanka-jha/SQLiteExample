package com.android.priyanka.sqliteexample.View;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.priyanka.sqliteexample.Adapter.DetailsAdapter;
import com.android.priyanka.sqliteexample.Database.DatabaseHelper;
import com.android.priyanka.sqliteexample.Model.Details;
import com.android.priyanka.sqliteexample.R;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private DetailsAdapter mAdapter;
    private List<Details> detailsList = new ArrayList<>();
    private RecyclerView recyclerView;


    private DatabaseHelper dh;
    SQLiteDatabase db;

    String name, email, phone, hobbies, gender, qualification, dob;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycler_view);
        dh = new DatabaseHelper(this);
        db = dh.getWritableDatabase();

        String query = "Select * from details ;";
        System.out.println("query"+query);
        Cursor cursor1 = db.rawQuery(query, null);


        if (cursor1.moveToFirst()) {
            do {
                id = cursor1.getInt(0);
                name = cursor1.getString(1);
                email = cursor1.getString(2);
                phone = cursor1.getString(3);
                qualification = cursor1.getString(4);

                if(qualification.equals("Select")){
                    qualification=" ";
                }
                gender = cursor1.getString(5);
                hobbies = cursor1.getString(6);

                String[] hobbiesArray = hobbies.split("!");
                StringBuilder strBuilder = new StringBuilder();
                String newString="";

                for(int i=0;i<hobbiesArray.length;i++){
                    if(hobbiesArray[i].equals("null")){
                        hobbiesArray[i]="no hobby";
                    }
                    strBuilder.append(hobbiesArray[i]);
                    strBuilder.append("!");
                    newString = strBuilder.toString();
                    newString = newString.substring(0, newString.length() - "!".length());

                    newString = newString.replace("no hobby!","");
                    System.out.println("new string1.."+newString);
                    newString = newString.replace("!no hobby","");
                    System.out.println("new string2.."+newString);
                    newString = newString.replace("no hobby","");

                    System.out.println("new string3.."+newString);
                }

                dob = cursor1.getString(7);

               // detailsList.add(new Details(id,name,email,phone,qualification,gender,hobbies,dob));

                Details details = new Details();
                details.setId(id);
                details.setName(name);
                details.setEmail(email);
                details.setPhone(phone);
                details.setHobbies(newString);
                details.setGender(gender);
                details.setQualification(qualification);
                details.setDob(dob);

                detailsList.add(details);

             System.out.println("list.."+name + email + phone + gender + newString + qualification + dob);


            } while (cursor1.moveToNext());
          //  db.close();

            mAdapter = new DetailsAdapter(this,detailsList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);

        }
        if (cursor1 != null && !cursor1.isClosed()) {
            cursor1.close();
        }
        db.close();



    }


}
