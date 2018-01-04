package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
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

    public ContactsAdapter(List<Contacts> contactsList, Context mContext) {
        this.contactsList = contactsList;
        this.mContext = mContext;
    }

    public ContactsAdapter(List<Contacts> contactsList) {
        this.contactsList = contactsList;
    }

    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {
        final Contacts contacts = contactsList.get(position);

        final String contactsWith = contacts.getUsername_user();
        UserDetails.chatWith = contactsWith;

        final String usernameSender = UserDetails.username;
        final String usernameRecipient = UserDetails.chatWith;

        holder.tvTitle.setText(contacts.getNama_user());

        final String nama_user = contacts.getNama_user();

        holder.cvContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactsDetail = new Intent(view.getContext(), ContactsDetailActivity.class);
                contactsDetail.putExtra("callerID", usernameSender);
                contactsDetail.putExtra("recipientID", contactsWith);
                contactsDetail.putExtra("namaID", nama_user);
                view.getContext().startActivity(contactsDetail);
            }
        });

//        holder.call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent makeCall = new Intent(view.getContext(), CallActivity.class);
//                makeCall.putExtra("callerID", usernameSender);
//                makeCall.putExtra("recipientID", contactsWith);
//                view.getContext().startActivity(makeCall);
//            }
//    });
//        holder.videoCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent makeVideoCall = new Intent(view.getContext(), VideoCallActivity.class);
//                makeVideoCall.putExtra("caller", usernameSender);
//                makeVideoCall.putExtra("recipients", usernameRecipient);
//                view.getContext().startActivity(makeVideoCall);
//
//                //Toast.makeText(view.getContext(), usernameSender + " wants to call " + usernameRecipient, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        holder.message.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent makeChat = new Intent(view.getContext(), ChatsActivity.class);
//                makeChat.putExtra("recipient", contactsWith);
//                view.getContext().startActivity(makeChat);
//                //Toast.makeText(view.getContext(), usernameRecipient, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageButton call, message, videoCall;
        CardView cvContacts;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.title);
//            call = (ImageButton) itemView.findViewById(R.id.imbCall);
//            message = (ImageButton) itemView.findViewById(R.id.imbChat);
//            videoCall = (ImageButton) itemView.findViewById(R.id.imbVidCall);
            cvContacts = (CardView) itemView.findViewById(R.id.cvContacts);


//            videoCall.setEnabled(false);


        }
    }
}
