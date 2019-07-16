package com.android.priyanka.sqliteexample.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.priyanka.sqliteexample.Model.Details;
import com.android.priyanka.sqliteexample.R;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>  {

    private Context context;
    private List<Details> detailsList;

    public DetailsAdapter(Context context, List<Details> detailsList) {
        this.context = context;
        this.detailsList = detailsList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Details> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<Details> detailsList) {
        this.detailsList = detailsList;
    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.details_items, viewGroup, false);

        return new DetailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder detailsViewHolder, int i) {
        Details details = detailsList.get(i);

        detailsViewHolder.tvid.setText(Integer.toString(details.getId()));
        detailsViewHolder.tvname.setText(details.getName());
        detailsViewHolder.tvemail.setText(details.getEmail());
        detailsViewHolder.tvphone.setText(details.getPhone());
        detailsViewHolder.tvhobbies.setText(details.getHobbies());
        detailsViewHolder.tvgender.setText(details.getGender());
        detailsViewHolder.tvqualification.setText(details.getQualification());
        detailsViewHolder.tvdob.setText(details.getDob());


    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }

    public class DetailsViewHolder extends RecyclerView.ViewHolder {

    public TextView tvid,tvname,tvemail,tvphone,tvhobbies,tvgender,tvqualification,tvdob;


    public DetailsViewHolder(@NonNull View itemView) {
        super(itemView);

        tvid = itemView.findViewById(R.id.txtid);
        tvname = itemView.findViewById(R.id.name);
        tvemail = itemView.findViewById(R.id.email);
        tvphone = itemView.findViewById(R.id.phone);
        tvhobbies = itemView.findViewById(R.id.hobbies);
        tvgender = itemView.findViewById(R.id.gender);
        tvqualification = itemView.findViewById(R.id.qualification);
        tvdob = itemView.findViewById(R.id.dob);
    }










}



















}
