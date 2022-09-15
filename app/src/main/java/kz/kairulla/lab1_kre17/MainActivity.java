package kz.kairulla.lab1_kre17;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editTextA, editTextB, editTextX;
    TextView textViewOtvet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextA = (EditText) findViewById(R.id.editTextA);
        editTextB = (EditText) findViewById(R.id.editTextB);
        editTextX = (EditText) findViewById(R.id.editTextX);
        textViewOtvet = (TextView) findViewById(R.id.textViewOtvet);
    }

    public void onClickButtonSolver(View view) {
        double a = 0, b = 0, x = 0, y = 0;
        String[] editTextEmptyTest = new String[] {
                editTextA.getText().toString(), editTextB.getText().toString(), editTextX.getText().toString()
        };

        /*for (int i = 0; i < 3; i++) {
            if (TextUtils.isEmpty(editTextEmptyTest[i])) {
                switch (i) {
                    case (0):
                        editTextA.setError("пусто");
                        break;
                    case (1):
                        editTextB.setError("пусто");
                        break;
                    case (2):
                        editTextX.setError("пусто");
                        break;
                }
            }
        }*/

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
    }
}