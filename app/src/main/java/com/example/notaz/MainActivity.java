package com.example.notaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.notaz.adapter.NoteAdapter;
import com.example.notaz.model.Notes;
import com.example.notaz.sqlite.DBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    List<Notes> notesList;
    NoteAdapter adapter;

    RecyclerView recNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        showAllNotes();

    }

    @Override
    protected void onResume() {
        super.onResume();

        showAllNotes();
    }

    public void showAllNotes() {
        notesList = dbHelper.getAllData();

        if (notesList != null) {
            setRecItems();
        } else {
            Toast.makeText(this, "Null!", Toast.LENGTH_SHORT).show();
        }

    }

    private void setRecItems() {
        adapter = new NoteAdapter(notesList, this);

        recNotes.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recNotes.setAdapter(adapter);
    }

    private void initView() {
        dbHelper = new DBHelper(this);
        recNotes = findViewById(R.id.rec_main);
    }

    public void gotoAddNote(View v) {
        startActivity(new Intent(this, AddNote.class));
    }

}
