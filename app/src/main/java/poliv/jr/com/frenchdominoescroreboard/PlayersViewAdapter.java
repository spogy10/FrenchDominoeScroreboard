package poliv.jr.com.frenchdominoescroreboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class PlayersViewAdapter extends RecyclerView.Adapter<PlayersViewAdapter.ViewHolder> {

    List<String> playersList;

    public PlayersViewAdapter(List playersList) {
        this.playersList = playersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.onBindView(i);
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }

    public void resetScores(){
        notifyDataSetChanged();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvPlayerName, tvPlayerScore;
        EditText etAddToScore;
        Button btPlus10, btAddToScore;


        protected ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlayerName = itemView.findViewById(R.id.tvPlayerName);
            tvPlayerScore = itemView.findViewById(R.id.tvPlayerScore);
            etAddToScore = itemView.findViewById(R.id.etAdd);
            btPlus10 = itemView.findViewById(R.id.btPlus10);
            btAddToScore = itemView.findViewById(R.id.btPlus);
        }

        protected void onBindView(int position){
            tvPlayerName.setText(playersList.get(position));
            tvPlayerScore.setText("0");

            btAddToScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickPlusButton();
                }
            });

            btPlus10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickPlus10Button();
                }
            });

        }

        private void onClickPlusButton(){
            if(String.valueOf(etAddToScore.getText()).equals(""))
                return;

            int score = Integer.parseInt(String.valueOf(tvPlayerScore.getText()));
            int addition = Integer.parseInt(String.valueOf(etAddToScore.getText()));

            score += addition;

            tvPlayerScore.setText(String.valueOf(score));
            etAddToScore.setText("");
        }

        private void onClickPlus10Button(){
            int score = Integer.parseInt(String.valueOf(tvPlayerScore.getText()));


            score += 10;

            tvPlayerScore.setText(String.valueOf(score));
        }
    }
}
