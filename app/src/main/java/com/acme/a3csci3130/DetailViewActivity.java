package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText nameField, numberField, businessField, addressField, provinceField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.name);
        numberField = (EditText) findViewById(R.id.number);
        businessField = (EditText) findViewById(R.id.business);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            numberField.setText(receivedPersonInfo.number);
            businessField.setText(receivedPersonInfo.business);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }

    public void updateContact(View v){
        String name = nameField.getText().toString();
        String number = numberField.getText().toString();
        String business = businessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();

        appState.firebaseReference.child(receivedPersonInfo.bid).child("name").setValue(name);
        appState.firebaseReference.child(receivedPersonInfo.bid).child("number").setValue(number);
        appState.firebaseReference.child(receivedPersonInfo.bid).child("business").setValue(business);
        appState.firebaseReference.child(receivedPersonInfo.bid).child("address").setValue(address);
        appState.firebaseReference.child(receivedPersonInfo.bid).child("province").setValue(province);

        finish();
    }

    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedPersonInfo.bid).setValue(null);

        finish();
    }
}
