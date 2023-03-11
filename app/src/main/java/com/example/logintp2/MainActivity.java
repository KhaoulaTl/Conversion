package com.example.logintp2;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;
public class MainActivity extends AppCompatActivity {
    EditText User;
    EditText Password;
    Button Login;
    TextView Info;
    int counter;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User = (EditText) findViewById(R.id.user);
        Password = (EditText) findViewById(R.id.pass);
        Info = (TextView) findViewById(R.id.txt);
        Login  = (Button) findViewById(R.id.b);

        Login.setOnClickListener(view -> {
            if (Objects.equals(User.getText().toString(),"khaoula") && Objects.equals(Password.getText().toString(),"tp2devmobile")) {
                Toast.makeText(MainActivity.this, "You have Authentificated Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, secondActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                counter--;
                Info.setText("No of attempts remaining : " + counter);
                if(counter ==0){
                    Login.setEnabled(false);
                }
            }
        });
}
}