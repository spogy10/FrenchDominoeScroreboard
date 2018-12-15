package poliv.jr.com.frenchdominoescroreboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AddPlayersActivity extends AppCompatActivity {

    EditText etPlayerName;
    TextView tvPlayersList;
    Button btClearList, btAddPlayer, btStartGame;
    List<String> playersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        playersList = new ArrayList<>();

        tvPlayersList = findViewById(R.id.tvDisplayPlayers);
        etPlayerName = findViewById(R.id.etPlayerName);
        btAddPlayer = findViewById(R.id.btAddPlayer);
        btClearList = findViewById(R.id.btClearList);
        btStartGame = findViewById(R.id.btStartGame);


        btAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPlayerToList();
            }
        });

        btClearList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearList();
            }
        });

        btStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    private void startGame() {
        if(!playersList.isEmpty()) {
            Intent intent = new Intent(AddPlayersActivity.this, ScoreActivity.class);
            intent.putStringArrayListExtra("List", (ArrayList<String>) playersList);
            startActivity(intent);
        }
    }

    private void clearList() {
        tvPlayersList.setText("");
        playersList.clear();
    }

    private void addPlayerToList() {
        String player = String.valueOf(etPlayerName.getText());
        if(player.equals(""))
            return;
        playersList.add(player);
        tvPlayersList.append(player + "\n");
        etPlayerName.setText("");
    }
}
