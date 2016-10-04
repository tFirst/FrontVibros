package com.frontvibros.frontvibros;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class MainActivity extends Activity {

    private static final String TEXTVIEWKDO_STATE_KEY = "TEXTVIEWKDO_STATE_KEY";
    private static final String TEXTVIEWKSO_STATE_KEY = "TEXTVIEWKSO_STATE_KEY";
    private static final String TEXTVIEWFW_STATE_KEY = "TEXTVIEWFW_STATE_KEY";
    static final String EDITTEXTKDR_STATE_KEY = "EDITTEXTKDR_STATE_KEY";
    static final String EDITTEXTKSR_STATE_KEY = "EDITTEXTKSR_STATE_KEY";
    double kdo, kso, frontVibros;
    EditText enterKDR, enterKSR;
    TextView textViewKDO, textViewKSO, textFrontVibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterKDR = (EditText) findViewById(R.id.editTextEnterKDR);
        enterKSR = (EditText) findViewById(R.id.editTextEnterKSR);

        textViewKDO = (TextView) findViewById(R.id.textAnswerKDO);
        textViewKSO = (TextView) findViewById(R.id.textAnswerKSO);
        textFrontVibros = (TextView) findViewById(R.id.textFrontVibrosView);

        if (savedInstanceState != null) {
            enterKDR.setText(savedInstanceState.getString(EDITTEXTKDR_STATE_KEY));
            enterKSR.setText(savedInstanceState.getString(EDITTEXTKSR_STATE_KEY));

            textViewKDO.setText(savedInstanceState.getString(TEXTVIEWKDO_STATE_KEY));
            textViewKSO.setText(savedInstanceState.getString(TEXTVIEWKSO_STATE_KEY));
            textFrontVibros.setText(savedInstanceState.getString(TEXTVIEWFW_STATE_KEY));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        savedInstanceState.putString(TEXTVIEWKDO_STATE_KEY, textViewKDO.getText().toString());
        savedInstanceState.putString(TEXTVIEWKSO_STATE_KEY, textViewKSO.getText().toString());
        savedInstanceState.putString(TEXTVIEWFW_STATE_KEY, textFrontVibros.getText().toString());

        savedInstanceState.putString(EDITTEXTKDR_STATE_KEY, enterKDR.getText().toString());
        savedInstanceState.putString(EDITTEXTKSR_STATE_KEY, enterKSR.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestart(){
        super.onRestart();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
     public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        enterKDR.setText(savedInstanceState.getString(EDITTEXTKDR_STATE_KEY));
        enterKSR.setText(savedInstanceState.getString(EDITTEXTKSR_STATE_KEY));

        textViewKDO.setText(savedInstanceState.getString(TEXTVIEWKDO_STATE_KEY));
        textViewKSO.setText(savedInstanceState.getString(TEXTVIEWKSO_STATE_KEY));
        textFrontVibros.setText(savedInstanceState.getString(TEXTVIEWFW_STATE_KEY));
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void StartWork(View v) throws IOException {

        if (enterKDR.length() == 0 || enterKSR.length() == 0)
            Toast.makeText(getApplicationContext(), "Text Fields is Empty!", Toast.LENGTH_LONG).show();
        else if (!WhoBig(enterKDR, enterKSR))
            Toast.makeText(getApplicationContext(), "KSR larger than KDR!", Toast.LENGTH_LONG).show();
        else {
            final double kdr = Double.parseDouble(enterKDR.getText().toString());
            final double ksr = Double.parseDouble(enterKSR.getText().toString());

            kdo = 7 * (kdr*kdr*kdr) / (2.4 + kdr);
            kso = 7 * (ksr*ksr*ksr) / (2.4 + ksr);
            frontVibros = (kdo - kso) / kdo * 100;

            textViewKDO.setText("" + new BigDecimal(kdo).setScale(1, RoundingMode.UP).doubleValue());
            textViewKSO.setText("" + new BigDecimal(kso).setScale(1, RoundingMode.UP).doubleValue());
            textFrontVibros.setText("" + new BigDecimal(frontVibros).setScale(1, RoundingMode.UP).doubleValue());
        }
    }

    private boolean WhoBig(EditText e1, EditText e2) {
        return Double.parseDouble(e1.getText().toString()) > Double.parseDouble(e2.getText().toString());
    }
}
