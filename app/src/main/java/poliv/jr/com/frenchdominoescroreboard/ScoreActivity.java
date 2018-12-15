package poliv.jr.com.frenchdominoescroreboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PlayersViewAdapter adapter;
    List<String> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        playerList = intent.getStringArrayListExtra("List");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetScores();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager lm = new LinearLayoutManager(this);

        adapter = new PlayersViewAdapter(playerList);

        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);



    }

    private void resetScores() {
        adapter.resetScores();
    }

}
