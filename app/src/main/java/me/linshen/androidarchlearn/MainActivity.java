package me.linshen.androidarchlearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.linshen.androidarchlearn.db.AppDatabase;
import me.linshen.androidarchlearn.model.UserEntity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtName, mEtAge;
    private Button mButtonAdd, mButtonDel;

    private AppDatabase mAppdataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppdataBase = AppDatabase.getDatabase(getApplicationContext());
        mEtName = findViewById(R.id.et_name);
        mEtAge = findViewById(R.id.et_age);
        mButtonAdd = findViewById(R.id.btn_add);
        mButtonDel = findViewById(R.id.btn_del);
        mButtonAdd.setOnClickListener(this);
        mButtonDel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_add:
                BackgroundHandler.execute(new Runnable() {
                    @Override
                    public void run() {
                        UserEntity userEntity = new UserEntity(mEtName.getText().toString(),
                                mEtAge.getText().toString());
                        mAppdataBase.getUserEntityDao().addUser(userEntity);
                    }
                });
                break;
            case R.id.btn_del:
                BackgroundHandler.execute(new Runnable() {
                    @Override
                    public void run() {
                        UserEntity userEntity = new UserEntity(mEtName.getText().toString(),
                                mEtAge.getText().toString());
                        mAppdataBase.getUserEntityDao().deleteUser(userEntity);
                    }
                });
                break;
        }
    }
}
