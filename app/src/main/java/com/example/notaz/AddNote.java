package com.example.notaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notaz.adapter.NoteAdapter;
import com.example.notaz.sqlite.DBHelper;

public class AddNote extends AppCompatActivity {

    EditText edtTitle, edtNote;
    Button btnSave;

    DBHelper helper;

    String id, title, note;
    int action = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        initView();
        getDataForUpdate();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (action == 0) {
                    addNote();
                } else {
                    updateNote();
                }
                gotoMainActivity();
                finish();
            }
        });

    }

    void getDataForUpdate() {
        id = getIntent().getStringExtra(NoteAdapter.ID_KEY);
        title = getIntent().getStringExtra(NoteAdapter.TITLE_KEY);
        note = getIntent().getStringExtra(NoteAdapter.NOTE_KEY);
        action = getIntent().getIntExtra(NoteAdapter.ACTION_KEY, 0);

        if (action == 1) {
            edtNote.setText(note);
            edtTitle.setText(title);
        }
    }

    public void initView() {

        helper = new DBHelper(this);

        edtNote = findViewById(R.id.edt_note);
        edtTitle = findViewById(R.id.edt_title);
        btnSave = findViewById(R.id.btn_save);


    }

    void addNote() {

        String title = edtTitle.getText().toString();
        String note = edtNote.getText().toString();

        helper.addNote(title, note);

    }

    void updateNote() {
        helper.updateNote(id, edtTitle.getText().toString(), edtNote.getText().toString());
    }

    void gotoMainActivity() {
        Intent intent = new Intent(AddNote.this, MainActivity.class);
        startActivity(intent);
    }

}
