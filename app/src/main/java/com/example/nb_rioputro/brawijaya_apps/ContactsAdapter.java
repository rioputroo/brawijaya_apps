package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nb-rioputro on 11/14/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<Contacts> contactsList;
    Context mContext;

    public ContactsAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        Contacts contacts = contactsList.get(position);
        holder.tvNamaKontak.setText(contacts.getNama_user());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamaKontak;

        public ViewHolder(View itemView) {
            super(itemView);


        }
    }
}
