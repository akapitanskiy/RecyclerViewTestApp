package com.alexthekap.recyclerviewtestapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * @author Alexey Kapitanskiy
 */
public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder> {

    private static int viewHolderCount = 0;
    private int numberItems;

    private Context parent;

    public NumbersAdapter(int numberItems, Context parent) {
        this.numberItems = numberItems;
        this.parent = parent;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext(); // получили контекст нашего RecyclerView
        int layoutIdForListItem = R.layout.number_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent, false); // false - говорим что не надо помещать эту view в
        // родителя это за нас сделает RecyclerView

        NumberViewHolder numberViewHolder = new NumberViewHolder(view);
        numberViewHolder.viewHolderIndex.setText("viewHolder: " + String.format("%2s", viewHolderCount));
        viewHolderCount++;
        return numberViewHolder;
    }

    // метод вызывается при обновлении ViewHolder'а новыми данными
    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int position) {

        numberViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }



    class NumberViewHolder extends RecyclerView.ViewHolder {
        TextView listItemNumberView;
        TextView viewHolderIndex;
        ImageView viewHolderImage;
//        ViewAnimator anim;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);

            listItemNumberView = itemView.findViewById(R.id.tv_number_item);
            viewHolderIndex = itemView.findViewById(R.id.tv_viewHolderNumber);
            viewHolderImage = itemView.findViewById(R.id.iv_viewHolderImage);
//            anim = itemView.findViewById(R.id.anim);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemIndex = getAdapterPosition();
                    Toast.makeText(parent, "Item " + itemIndex + " clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }

        void bind(int listIndex) {
            listItemNumberView.setText("item: " + String.valueOf(listIndex));
            Picasso.get()
                    .load("https://media3.giphy.com/media/DVHrK100eH3J6/200_s.gif")
                    .resize(180, 200)
                    .centerCrop()
//                    .placeholder(R.drawable.user_placeholder)
//                    .error(R.drawable.user_placeholder_error)
                    .into(viewHolderImage);

//            new GetURLImageTask(viewHolderImage).execute("https://media3.giphy.com/media/DVHrK100eH3J6/100_s.gif");

//            Glide.with(parent)
//                    .load("https://media3.giphy.com/media/DVHrK100eH3J6/100_s.gif")
//                    .asGif()
//                    .error(R.drawable.baseline_camera_black_48dp_2)
//                    .crossFade().sizeMultiplier(0.2F)
//                    .into(viewHolderImage);
        }
    }
}
