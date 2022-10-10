package kz.kairulla.lab1_kre17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    EditText editTextA, editTextB, editTextX;
    TextView textViewOtvet;
    Button buttonSolver, buttonClear;
    View.OnKeyListener myKeyListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextA = (EditText) findViewById(R.id.editTextA);
        editTextB = (EditText) findViewById(R.id.editTextB);
        editTextX = (EditText) findViewById(R.id.editTextX);
        textViewOtvet = (TextView) findViewById(R.id.textViewOtvet);
        buttonSolver = (Button) findViewById(R.id.buttonSolver);
        buttonClear = (Button) findViewById(R.id.buttonClear);
//        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // залочил ориентацию

        // Собственный обработчик нажатий на клавиши
        myKeyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // она сохраняет содержимое при перевороте
                if (
                        editTextA.getText().toString().trim().equals("") ||
                        editTextB.getText().toString().trim().equals("") ||
                        editTextX.getText().toString().trim().equals("")
                ) {
                    buttonSolver.setEnabled(false); // Выключаем доступность нажатия у кнопки
                    buttonClear.setEnabled(false);
                } else {
                    buttonSolver.setEnabled(true); // Включаем доступность нажатия у кнопки
                    buttonClear.setEnabled(true);
                }
                return false;
            }
        };

        if (savedInstanceState != null) {
            textViewOtvet.setText(savedInstanceState.getString("Otvet"));
            buttonSolver.setEnabled(savedInstanceState.getBoolean("myButtonSolver"));
            buttonClear.setEnabled(savedInstanceState.getBoolean("myButtonClear"));
        } else {
            buttonSolver.setEnabled(false); // Выключаем доступность нажатия у кнопки
            buttonClear.setEnabled(false); // Выключаем доступность нажатия у кнопки
        }

        editTextA.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
        editTextB.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
        editTextX.setOnKeyListener(myKeyListener); // Добавляем к компоненту свой обработчик нажатий
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Otvet", textViewOtvet.getText().toString());
        outState.putBoolean("myButtonSolver", buttonSolver.isEnabled()); // сохранение состояния кнопки
        outState.putBoolean("myButtonClear", buttonClear.isEnabled()); // сохранение состояния кнопки
    }


    public void onClickButtonSolver(View view) {
        double a = 0, b = 0, x = 0, y = 0;

        try {
            a = Double.parseDouble(editTextA.getText().toString().trim());
            b = Double.parseDouble(editTextB.getText().toString().trim());
            x = Double.parseDouble(editTextX.getText().toString().trim());

            if (x >= 8) {
                y = (a * a + 4 * x * x + b) / (2.0 * x);
            } else {
                y = a * a - 2 * x * x;
            }

            textViewOtvet.setText(String.valueOf(y));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onClickButtonClear(View view) {
        editTextA.setText("");
        editTextB.setText("");
        editTextX.setText("");
        textViewOtvet.setText("ОТВЕТ:");
        buttonSolver.setEnabled(false); // Выключаем доступность нажатия у кнопки
        buttonClear.setEnabled(false);
    }
}