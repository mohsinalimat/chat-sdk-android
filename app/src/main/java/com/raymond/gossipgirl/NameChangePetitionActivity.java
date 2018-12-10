package com.raymond.gossipgirl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import co.chatsdk.ui.utils.ToastHelper;

public class NameChangePetitionActivity extends AppCompatActivity {

    private TextView userName;
    private EditText name;
    private EditText email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namechangepetition);
        getSupportActionBar().hide();

        // This message is telling people that they need to upload their enrollment form for the
        //petition to work
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.name_change_petition))
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        //Retrieve the data from the last activity.
        Intent i = getIntent();
        String stageNameString = (String) i.getSerializableExtra("stageNameTransfer");

        //Interfacing with the screen
        userName = findViewById(R.id.textViewUserName);
        String desiredStageName = getString(R.string.desired_stagename);
        userName.setText(desiredStageName + "\n" + stageNameString);
        name = findViewById(R.id.full_name);
        email = findViewById(R.id.email_address);
    }

    public void back_click(View v) {
        Intent i = new Intent (NameChangePetitionActivity.this, GossipGirlUsernameActivity.class);
        startActivity(i);
    }

    public void submit_click(View v) {

        //Check to see if what was entered in the email box was an actual email address, and if it is also an actual name.
        String emailString = email.getText().toString();
        String nameString = name.getText().toString();

        if (nameString.isEmpty() || !nameString.contains(" ")) {
            ToastHelper.show(NameChangePetitionActivity.this, "Please enter your first and last name");
            return;
        }
        if (emailString.isEmpty() || !emailString.contains("@")) {
            ToastHelper.show(NameChangePetitionActivity.this, "Please enter a valid email address.");
            return;
        } else {
            //If task of submitting is successful:
            Intent i = new Intent (NameChangePetitionActivity.this, RequestReceivedActivity.class);
            startActivity(i);
        }
        //Otherwise I don't know what we are going to do.
    }
}
