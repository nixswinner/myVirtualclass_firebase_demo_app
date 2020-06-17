package com.nix.myvirtualclassapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edtFirstName,edtSecondName,edtCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //binding
        edtFirstName = findViewById(R.id.edt_first_name);
        edtSecondName = findViewById(R.id.edt_second_name);
        edtCourse = findViewById(R.id.edt_course);

        Button btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                //get the values if any add on our editText
                validateInputs(
                        edtFirstName.getText().toString(),
                        edtSecondName.getText().toString(),
                        edtCourse.getText().toString()
                );
            }
        });

        Button btnfindMyLocation = findViewById(R.id.btnfindMyLocation);
        btnfindMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,FindMyLocationActivity.class));
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this,StudentsList.class));
               /*Intent intent = new Intent(MainActivity.this,StudentsList.class);
               startActivity(intent);*/
            }
        });
    }

    private void validateInputs(String first_name,String second_name,String course ){
        if (first_name.isEmpty()){
            edtFirstName.setError("Enter First Name");
        }else{
            if (second_name.isEmpty()){
                edtSecondName.setError("Enter Second Name");
            }else{
                if (course.isEmpty()){
                    edtCourse.setText("Enter Course");
                }else{
                    //everything if fine -
                    //insert our firebase
                    //--create a firebase database object
                    //- create a database reference object
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("students"); //nodes
                    Student student = new Student(first_name,second_name,course);
                    myRef.push().setValue(student);
                    /*
                    * .push().setValue()
                    * students[]
                    * 1,2,3
                    * array
                    * -
                    *
                    * */
                    /*
                    * CRUD - databases  - persistence -
                    * Create - Write -
                    * Read -
                    * Update -
                    * Delete -
                    * */

                   // myRef.setValue(student);
                    /*
                    * .setValue()
                    * name= Afifa
                     * name = Gess
                    *
                    * Gess
                    * Appending
                    *
                    * */

                }
            }
        }
    }

    /*
    * Adding firebase into our application
    * -create a firebase project - app in https://console.firebase.google.com
    *   -create a project- create an app - android
    *   - package name ; your app package name: com.nix.myvirtualclassapp
    *   - download a config file google-services.json file
    *
    *  - move into app director of  our app -
    *   -switch from android view to project view
    *   - add firebase dependencies  - into our build.gradle(module:app)
    *       build.gradle (project)
    *       build.gradle (module)
    *   - sync the project
    *
    * //firebase
    *   -real time databases
    * -dependency inside our build.gradle(app leb)
    *   - real time databases, firebase auth crashanlaystics, cloud storage , firebase messages
    *
    * */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}