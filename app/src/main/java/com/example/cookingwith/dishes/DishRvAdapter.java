package com.example.cookingwith.dishes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingwith.R;
import com.example.cookingwith.model.DishModel;

import java.util.List;

public class DishRvAdapter extends RecyclerView.Adapter<DishRvAdapter.MyViewHolder> {
    private List<DishModel> list;
    private ItemClickListener clickListener;


    public DishRvAdapter(List<DishModel> list, ItemClickListener clickListener) {
        this.list = list;
        this.clickListener  = clickListener;

    }
    @NonNull
    @Override
    public DishRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_rv_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishRvAdapter.MyViewHolder holder, int position) {
        holder.titleTextView.setText(list.get(position).getTitle());
        holder.descriptionTextView.setText(list.get(position).getDescription());
        holder.photoImageView.setImageResource(list.get(position).getPhotoId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        ImageView photoImageView;

        public MyViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.titleTextView);
            descriptionTextView = view.findViewById(R.id.descriptionTextView);
            photoImageView = view.findViewById(R.id.photoImageView);
        }

    }

    public interface ItemClickListener {

        public void onItemClick(DishModel dataModel);
    }
}
