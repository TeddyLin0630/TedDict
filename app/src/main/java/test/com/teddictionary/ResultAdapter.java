package test.com.teddictionary;

import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import test.com.teddictionary.Model.Definition;
import test.com.teddictionary.event.MessageEvent;

/**
 * Created by teddylin on 29/12/2017.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.CardHolder> {
    private ArrayList<Definition> results = new ArrayList<>();

    public void setData(ArrayList<Definition> results) {
        this.results = results;
        this.notifyDataSetChanged();
    }

    @Override
    public CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_view_holder, parent, false);
        return new CardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CardHolder holder, int position) {
        final Definition definition = results.get(position);
        holder.definition.setText(definition.getDefinicion());
        holder.example.setText(definition.getExample());
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                MenuInflater inflater = popup.getMenuInflater();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_quizlet:
                                EventBus.getDefault().post(new MessageEvent.SaveDefinition2Quizlet(definition));
                                break;
                        }
                        return false;
                    }
                });
                inflater.inflate(R.menu.menu_main, popup.getMenu());
                popup.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class CardHolder extends RecyclerView.ViewHolder {
        protected TextView definition;
        protected TextView example;
        protected CardView cardView;

        public CardHolder(View v) {
            super(v);
            definition = v.findViewById(R.id.definition);
            example = v.findViewById(R.id.example);
            cardView = v.findViewById(R.id.result_card);
        }
    }
}
