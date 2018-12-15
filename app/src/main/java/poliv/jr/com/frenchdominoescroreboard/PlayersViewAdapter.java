package poliv.jr.com.frenchdominoescroreboard;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class PlayersViewAdapter extends RecyclerView.Adapter<PlayersViewAdapter.ViewHolder> {
    

    public PlayersViewAdapter() {
        
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
        return ScoreActivity.playerList.length;
    }

    public void resetScores(){
        for (Player p : ScoreActivity.playerList)
            p.setPoints(0);

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

        protected void onBindView(final int position){
            tvPlayerName.setText(ScoreActivity.playerList[position].getName());
            setScore(ScoreActivity.playerList[position].getPoints());

            btAddToScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickPlusButton(position);
                }
            });

            btPlus10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickPlus10Button(position);
                }
            });

        }

        private void onClickPlusButton(int position){
            if(String.valueOf(etAddToScore.getText()).equals(""))
                return;

            int addition = Integer.parseInt(String.valueOf(etAddToScore.getText()));

            int score = ScoreActivity.playerList[position].addToPoints(addition);

            setScore(score);
            etAddToScore.setText("");
        }

        private void onClickPlus10Button(int position){
            int score = ScoreActivity.playerList[position].addToPoints(10);

            setScore(score);
        }

        private void setScore(int score){

            if(score < 25)
                tvPlayerScore.setTextColor(Color.parseColor("#7be760"));
            else if(score < 50)
                tvPlayerScore.setTextColor(Color.parseColor("#E0E144"));
            else if(score < 75)
                tvPlayerScore.setTextColor(Color.parseColor("#ffa500"));
            else if(score < 90)
                tvPlayerScore.setTextColor(Color.RED);
            else
                tvPlayerScore.setTextColor(Color.parseColor("#8b0000"));

            tvPlayerScore.setText(String.valueOf(score));
        }
    }
}
