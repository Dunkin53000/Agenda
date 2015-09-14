package com.moovin.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.moovin.agenda.Adapter.CardArrayAdapter;

/**
 * Created by pierre on 12/09/2015.
 */
public class Semaine2FragmentTab extends Fragment {

    private static final String TAG = "CardListActivity";
    private CardArrayAdapter cardArrayAdapter;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab2,container,false);
        return v;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (ListView) this.getActivity().findViewById(R.id.card_listView2);

        listView.addHeaderView(new View(getActivity()));
        listView.addFooterView(new View(getActivity()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Intent myIntent = new Intent(getActivity(), JourActivity.class);
                myIntent.putExtra("JOUR", String.valueOf(position + 10));
                getActivity().startActivity(myIntent);

            }
        });

        cardArrayAdapter = new CardArrayAdapter(getActivity(), R.layout.list_item_card);


        Card card = new Card("Lundi", "L");
        cardArrayAdapter.add(card);

        Card card2 = new Card("Mardi", "Ma");
        cardArrayAdapter.add(card2);

        Card card3 = new Card("Mercredi", "Me");
        cardArrayAdapter.add(card3);

        Card card4 = new Card("Jeudi", "J");
        cardArrayAdapter.add(card4);

        Card card5 = new Card("Vendredi", "V");
        cardArrayAdapter.add(card5);

        Card card6 = new Card("Samedi", "S");
        cardArrayAdapter.add(card6);

        Card card7 = new Card("Dimanche", "D");
        cardArrayAdapter.add(card7);


        listView.setAdapter(cardArrayAdapter);
    }


}
