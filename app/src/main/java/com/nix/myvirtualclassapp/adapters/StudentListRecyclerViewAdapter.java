package com.nix.myvirtualclassapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nix.myvirtualclassapp.R;
import com.nix.myvirtualclassapp.Student;

import java.util.List;

public class StudentListRecyclerViewAdapter extends
        RecyclerView.Adapter<StudentListRecyclerViewAdapter.ViewHolder> {

    private List<Student> studentsList;
    private Context context;

    public StudentListRecyclerViewAdapter(List<Student> studentsListList, Context context) {
        this.studentsList = studentsListList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //
        holder.txt_first_name.setText(studentsList.get(position).getFirst_name());
        holder.txt_second_name.setText(studentsList.get(position).getSecond_name());
        holder.txt_course.setText(studentsList.get(position).getCourse());
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_first_name,txt_second_name,txt_course;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_first_name = itemView.findViewById(R.id.txt_first_name);
            txt_second_name = itemView.findViewById(R.id.txt_second_name);
            txt_course= itemView.findViewById(R.id.txt_course);
        }
    }
}
