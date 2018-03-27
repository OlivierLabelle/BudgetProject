package com.example.android.budgetproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by olivier on 2018-03-13.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    public List<Transaction> mData;
    public ItemClickListener mClickListener;

    // data is passed into the constructor
    public TransactionAdapter(List<Transaction> transactionList) {
        this.mData = transactionList;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_depense_fragment, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction transaction = mData.get(position);
        holder.depense.setText(""+transaction.getDepense());
        holder.description.setText(transaction.getDescription());
        holder.date.setText(transaction.getDate()+"");
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView depense, description, date;

        ViewHolder(View itemView) {
            super(itemView);
            depense = itemView.findViewById(R.id.tv_depense_montant);
            itemView.setOnClickListener(this);

            description = itemView.findViewById(R.id.tv_depense_description);
            itemView.setOnClickListener(this);

            date = itemView.findViewById(R.id.tv_depense_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
  // String getItem(int id) {
  //     //return mData.get(id);
  // }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void notfier(){
        notifyDataSetChanged();
    }
}


