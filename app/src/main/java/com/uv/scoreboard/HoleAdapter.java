package com.uv.scoreboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uv.scoreboard.Hole;



public class HoleAdapter extends RecyclerView.Adapter<HoleAdapter.HoleViewHolder>{

    private Hole[] mHoles;
    // Where this adapter is being used
    private Context mContext;

    public HoleAdapter(Context context,Hole[] holes){
        mContext=context;
        mHoles = holes;
    }

    // Called when new Views are needed
    @Override
    public HoleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("HoleAdapter","onCreateViewHolder("+parent +" ,"+viewType+")");
        // This is the spot where new Views are created when they are needed
        // not reused! (like where we check for ViewGroup being a null in DayAdapter)

        // 1. Inflate(create) View from XML
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hole_list_item,parent, false);

        HoleViewHolder viewHolder = new HoleViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HoleViewHolder holder, int position) {
        holder.bindHole(mHoles[position]);
        Log.d("HoleAdapter","onBindViewHolder("+holder +" ,"+position+")");
    }

    @Override
    public int getItemCount() {
        return mHoles.length;
    }

    public class HoleViewHolder extends RecyclerView.ViewHolder
            implements  View.OnClickListener{

        public TextView mScoreLabel;
        public TextView mHoleIdLabel;
        public Button mIncButton;
        public Button mDecButton;
        private Hole mHole;

        public HoleViewHolder(View itemView) {
            super(itemView);
            mScoreLabel=(TextView)itemView.findViewById(R.id.scoreLabel);
            mIncButton=(Button)itemView.findViewById(R.id.incButton);
            mDecButton=(Button)itemView.findViewById(R.id.decButton);
            mHoleIdLabel=(TextView)itemView.findViewById(R.id.holeIdLabel);

            mIncButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHole.setStrokes(mHole.getStrokes()+1);
                    bindHole(mHole);
                }
            });

            mDecButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mHole.getStrokes()>0) {
                        mHole.setStrokes(mHole.getStrokes() - 1);
                        mScoreLabel.setText(mHole.getStrokes()+"");
                    }
                }
            });

            //set a listener for the root view in the ViewHolder
            itemView.setOnClickListener(this);
        }

        // Bind hole fields to according views
        public void bindHole(Hole hole){
            // we will need reference to hole object for OnClickListeners
            mHole=hole;
            mScoreLabel.setText(hole.getStrokes()+"");
            mHoleIdLabel.setText(hole.getLabel());
        }

        // FIXME useless
        @Override
        public void onClick(View view) {
            Log.d("HoleAdapter","CLICK");
        }
    }




}
