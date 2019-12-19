package com.example.desafiocontacorrente.extras;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.desafiocontacorrente.R;
import com.example.desafiocontacorrente.model.Statement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private final Activity activity;
    private List<Statement> statements;
    public View view;
    private ViewHolder holder;
    private String idUserFrom;
    private String tipo;


    public CustomAdapter(List<Statement> statements, Activity activity) {
        this.statements = statements;
        this.activity = activity;

    }

    @Override
    public int getCount() {
        return statements.size();
    }

    @Override
    public Object getItem(int position) {
        return statements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if( convertView == null) {
            view = activity.getLayoutInflater().inflate(R.layout.custom_list_statement, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else { view = convertView; }
            holder = new ViewHolder(view);
            Statement statement = statements.get(position);
            SimpleDateFormat simpleDateFormatInput = new SimpleDateFormat("yyyy-MM-dd");
            Date inputDate = null;//Este método lança ParseException, colocar dentro de try/catch
            try {
                inputDate = simpleDateFormatInput.parse(statement.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String userNow = statement.getIdFrom();
            idUserFrom =  MySharedPreferences.getPreferences(view.getContext(),"id");
            String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(inputDate);
            if(userNow.equals(idUserFrom)){
                tipo = "Trasferência Enviada";
            }else{
                tipo = "Transferencia recebida";
            }

            holder.date.setText(formattedDate);
            holder.value.setText(statement.getValue());
            holder.type.setText(tipo);
        return view;
    }

    private class ViewHolder{
        TextView date;
        TextView value;
        TextView type;


        public ViewHolder(View view) {
            date = view.findViewById(R.id.custom_list_statement_date);
            value = view.findViewById(R.id.custom_list_statement_id_from_value);
            type =  view.findViewById(R.id.custom_list_statement_type);

        }





    }

}
