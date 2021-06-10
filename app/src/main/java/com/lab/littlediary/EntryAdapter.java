package com.lab.littlediary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.PhoneViewHold>  {

    ArrayList<EntryHelper> entryArrayList;
    final private ListItemClickListener listOnClickListener;

    public EntryAdapter(ArrayList<EntryHelper> entryArrayList, ListItemClickListener listener) {
        this.entryArrayList = entryArrayList;
        listOnClickListener = listener;
    }

    @NonNull

    @Override
    public PhoneViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_recycler_card, parent, false);
        return new PhoneViewHold(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHold holder, int position) {


        EntryHelper entryHelper = entryArrayList.get(position);
        holder.title.setText(entryHelper.getTitle());

        Calendar calendar = Calendar.getInstance();
        calendar.set(entryHelper.getDate(), entryHelper.getMonth(), entryHelper.getDate());
        String dayLongName = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        String monthLongName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

        holder.date.setText(dayLongName.substring(0, 3) + " " + entryHelper.getDate() + " " + monthLongName.substring(0, 3));
    }

    @Override
    public int getItemCount() {
        return entryArrayList.size();

    }

    public interface ListItemClickListener {
        void onListClick(int clickedItemIndex);
    }

    public class PhoneViewHold extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        TextView date;
        RelativeLayout relativeLayout;


        public PhoneViewHold(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            title = itemView.findViewById(R.id.taglineCard);
            date = itemView.findViewById(R.id.dateCard);
            relativeLayout = itemView.findViewById(R.id.background_color);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            listOnClickListener.onListClick(clickedPosition);
        }
    }

}

