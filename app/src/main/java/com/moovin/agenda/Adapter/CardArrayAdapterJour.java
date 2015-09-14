package com.moovin.agenda.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.moovin.agenda.CardJour;
import com.moovin.agenda.R;

import java.util.List;

/**
 * Created by pierre on 13/09/2015.
 */
public class CardArrayAdapterJour extends ArrayAdapter<CardJour> {

    private static final String TAG = "CardArrayAdapter";
    List<CardJour> cardList;
    private int lastPosition = -1;

    private class CardViewHolder {
        TextView cours;
        TextView horaire;
        TextView salle;

    }

    public CardArrayAdapterJour(Context context, int textViewResourceId,List<CardJour> cardList) {
        super(context, textViewResourceId, cardList);
        this.cardList = cardList;
    }

    @Override
    public void add(CardJour object) {

        super.add(object);
        cardList.add(object);
        notifyDataSetChanged();
    }


    @Override
    public void remove(CardJour object) {
        cardList.remove(object);
        super.remove(object);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cardList.size();
    }

    @Override
    public CardJour getItem(int index) {
        return cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_cardjour, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.cours = (TextView) row.findViewById(R.id.courstext);
            viewHolder.horaire = (TextView) row.findViewById(R.id.horairetext);
            viewHolder.salle = (TextView) row.findViewById(R.id.salletext);


            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }
        CardJour card = getItem(position);

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        int color1 = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(card.getFirstLetter(), color1);

        ImageView image = (ImageView) row.findViewById(R.id.imageView);
        image.setImageDrawable(drawable);
        viewHolder.cours.setText(card.getCours());
        viewHolder.horaire.setText(card.getHoraire());
        viewHolder.salle.setText(card.getSalle());




        Animation animation = AnimationUtils.loadAnimation(getContext(), (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        row.startAnimation(animation);
        lastPosition = position;

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
