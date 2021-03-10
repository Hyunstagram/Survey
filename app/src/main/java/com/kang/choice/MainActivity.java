package com.kang.choice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Member Variable
    private final boolean D  = true;
    private final String TAG = "MainActivity";
    private InputMethodManager imm;

    // View Object
    private TextView  title_user;
    private TextView  title_thema;
    private TextView  title_select;

    private EditText edit_id;
    private Button button_id;

    private RadioGroup group_thema;
    private RadioButton radio_white;
    private RadioButton radio_dark;
    private RadioButton radio_blue;

    private CheckBox check_save;
    private CheckBox check_wifi;

    private Button button_save;
    private Button button_cancel;

    // data
    String id = "";
    String thema = "";
    String select_save = "";
    String select_wifi = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        if(D) Log.i(TAG, "onCreate()");
    }

    // Member Method - Custom
    public void init() {

        title_user = findViewById(R.id.title_user);
        title_thema = findViewById(R.id.title_thema);
        title_select = findViewById(R.id.title_select);

        edit_id = findViewById(R.id.edit_id);
        button_id = findViewById(R.id.button_id);
        edit_id.setTag(edit_id.getKeyListener());
        edit_id.setKeyListener(null);

        group_thema = findViewById(R.id.group_thema);
        radio_white = findViewById(R.id.radio_white);
        radio_dark = findViewById(R.id.radio_dark);
        radio_blue = findViewById(R.id.radio_blue);

        check_save = findViewById(R.id.check_save);
        check_wifi = findViewById(R.id.check_wifi);

        button_save = findViewById(R.id.button_save);
        button_cancel = findViewById(R.id.button_cancel);

        imm = (InputMethodManager)this.getSystemService(INPUT_METHOD_SERVICE);

        button_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(edit_id.getWindowToken(), 0);
                edit_id.setKeyListener((KeyListener)edit_id.getTag());
            }
        });

        group_thema.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == -1)
                    return;
                thema = "Thema : " + String.valueOf(((RadioButton)findViewById(checkedId)).getText()) + "\n";
                title_thema.setTextColor(Color.BLACK);
            }
        });

        check_save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                title_select.setTextColor(Color.BLACK);
            }
        });

        check_wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                title_select.setTextColor(Color.BLACK);
            }
        });

        edit_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                title_user.setTextColor(Color.BLACK);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(edit_id.getText()).equals("") || thema == "" || (check_save.isChecked() == false && check_wifi.isChecked() == false)) {
                    if (String.valueOf(edit_id.getText()).equals("")) {
                        title_user.setTextColor(Color.RED);
                    }
                    if (thema == "") {
                        title_thema.setTextColor(Color.RED);
                    }
                    if (check_save.isChecked() == false && check_wifi.isChecked() == false) {
                        title_select.setTextColor(Color.RED);
                    }
                    return;
                }

                id = "ID : " + edit_id.getText() + "\n";
                select_save = "Auto save : " + check_save.isChecked() + "\n";
                select_wifi = "Auto connect wifi : " + check_wifi.isChecked() + "\n";

                Log.i(TAG, id + thema + select_save + select_wifi);
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                edit_id.setText(null);
                group_thema.clearCheck();
                check_save.setChecked(false);
                check_wifi.setChecked(false);

                id = "";
                thema = "";
                select_save = "";
                select_wifi = "";

                title_user.setTextColor(Color.BLACK);
                title_thema.setTextColor(Color.BLACK);
                title_select.setTextColor(Color.BLACK);
            }
        });
    }
}