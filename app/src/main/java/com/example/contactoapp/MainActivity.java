
package com.example.contactoapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName, etPhone, etEmail, etDescription;
    EditText etDate;
    Button btnNext;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etDescription = findViewById(R.id.etDescription);
        btnNext = findViewById(R.id.btnNext);

        calendar = Calendar.getInstance();

        etDate.setOnClickListener(v -> {
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                etDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ConfirmActivity.class);
            intent.putExtra("name", etName.getText().toString());
            intent.putExtra("date", etDate.getText().toString());
            intent.putExtra("phone", etPhone.getText().toString());
            intent.putExtra("email", etEmail.getText().toString());
            intent.putExtra("description", etDescription.getText().toString());
            startActivity(intent);
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            etName.setText(extras.getString("name", ""));
            etDate.setText(extras.getString("date", ""));
            etPhone.setText(extras.getString("phone", ""));
            etEmail.setText(extras.getString("email", ""));
            etDescription.setText(extras.getString("description", ""));
        }
    }
}
