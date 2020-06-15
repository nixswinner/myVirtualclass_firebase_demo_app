package com.nix.myvirtualclassapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nix.myvirtualclassapp.adapters.StudentListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StudentsList extends AppCompatActivity {
    /*
    * Get our reference - firebase database
    * DatabaseReference
    * */

    private FirebaseDatabase database;
    private DatabaseReference dbReference;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference("students");
        /*CTLt + B*/

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        /*
        * How to retrieve / read records stored in a firebaseDatabase
        * How we now render in our activity - various ui components
        *       - new marshal - we need to convert the data being returned in the datasnapshot to
        *            java objects -
        *
        *
        * RecyclerViews
        *  -Adapter
        *  - View
        * */

        //Retrieve

        getData();


    }

    private void getData(){
        Log.e("FireBaseData", "getData: " );
        final List<Student> studentList = new ArrayList<>();
        dbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //get our values in our data
                //Log.e("Results","==="+dataSnapshot.toString());
                // Get Student object and use the values to update the UI
                Student student = dataSnapshot.getValue(Student.class);
                studentList.add(student);

                /*for (Student std:studentList
                ) {
                    Log.e("Data",""+std.getFirst_name());
                }

                Log.e("Result Values ","First Name :"+student.getFirst_name()+
                        "\nSecond Name: "+student.getSecond_name()+"\nCourse: "+student.getCourse());

*/
                showDataOnRecyclerView(studentList);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("DatabaseError ",databaseError.getMessage());
            }
        });



        //Get value from firebase Once - this is important does not frequently
       /* dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //logs
                Log.e("FirebaseDatabase", "onCancelled: "+databaseError.getMessage() );
            }
        });*/
    }

    private void showDataOnRecyclerView(List<Student> studentList){

        StudentListRecyclerViewAdapter adapter = new StudentListRecyclerViewAdapter(
                studentList,this
        );
        recyclerView.setAdapter(adapter);
    }
}