package poliv.jr.com.frenchdominoescroreboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PlayersViewAdapter adapter;
    public static Player[] playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if( savedInstanceState == null)
            getPlayerListFromIntent();
        else
            getPlayerListFromSavedInstance(savedInstanceState);

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

        adapter = new PlayersViewAdapter();

        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);

    }

    private void getPlayerListFromSavedInstance(Bundle savedInstanceState) {
        Parcelable[] array = savedInstanceState.getParcelableArray("List");

        playerList = (Player[]) array;
    }

    private void getPlayerListFromIntent() {
        Intent intent = getIntent();
        List<String> playerListString = intent.getStringArrayListExtra("List");

        playerList = new Player[playerListString.size()];
        for(int i = 0; i < playerList.length; i++)
            playerList[i] = new Player(playerListString.get(i));

    }

    private void resetScores() {
        adapter.resetScores();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArray("List", playerList);
        super.onSaveInstanceState(outState);
    }
}
