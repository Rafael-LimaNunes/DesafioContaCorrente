package com.example.desafiocontacorrente.extras;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.model.Statement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter {

    private final Activity activity;
    private List<Statement> statements;

    public CustomAdapter(List<Statement> statements, Activity activity) {
        this.statements = statements;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        View view = LayoutInflater.from(activity).inflate(R.layout.custom_list_statement, parent, false);
        return new MyViewHolder(view);
    }


    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Statement statement = statements.get(position);

        //Convert string date
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormatInput = new SimpleDateFormat("yyyy-MM-dd");
        Date inputDate = null;//Este método lança ParseException, colocar dentro de try/catch
        try {
            inputDate = simpleDateFormatInput.parse(statement.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String userNow = statement.getIdFrom();
        String idUserFrom = MySharedPreferences.getPreferences(activity, "id");
        @SuppressLint("SimpleDateFormat") String formattedDate = null;
        if (inputDate != null) {
            formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(inputDate);
        }
        String type;
        if(userNow.equals(idUserFrom)){
            type = "Transfer Sent";
        }else{
            type = "Transfer received";
        }
        myViewHolder.date.setText(formattedDate);
        myViewHolder.value.setText(statement.getValue());
        myViewHolder.type.setText(type);


    }

    @Override
    public int getItemCount() {
        return statements.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView value;
        TextView type;

        MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.custom_list_statement_date);
            value = view.findViewById(R.id.custom_list_statement_id_from_value);
            type =  view.findViewById(R.id.custom_list_statement_type);

        }

    }

}
