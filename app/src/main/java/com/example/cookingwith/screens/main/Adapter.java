package com.example.cookingwith.screens.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.cookingwith.App;
import com.example.cookingwith.R;
import com.example.cookingwith.model.Item;
import com.example.cookingwith.screens.details.ItemDetailsActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder> {

    // ----------------------------------------------------------------------
    // сортед лист предназначен чтобы автоматически определять изменения внутри себя
    // и выдавать соответстующие команды что в нем обновилось
    // чтобы в последствии без потерь проиграть все анимации

    private SortedList<Item> sortedList;

    public Adapter() {
        sortedList = new SortedList<>(Item.class, new SortedList.Callback<Item>() {
            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);  // recyclerview узнает что элементы изменили в каком-то диапазоне и изменит их без затрагивания других
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);  // recyclerview узнает что элементы изменили в каком-то диапазоне и изменит их без затрагивания других
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);  // recyclerview узнает что элементы изменили в каком-то диапазоне и изменит их без затрагивания других
            }

            @Override
            public int compare(Item o1, Item o2) {
                return (int) (o2.timestamp - o1.timestamp); // сортируем по времени
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count); // recyclerview узнает что элементы изменили в каком-то диапазоне и изменит их без затрагивания других
            }

            @Override
            public boolean areContentsTheSame(Item oldItem, Item newItem) {
                return oldItem.equals(newItem); // возвращает тру если два элемента равны полностью
            }

            @Override
            public boolean areItemsTheSame(Item item1, Item item2) {
                return item1.uid == item2.uid; // проверяет айди элементов, содержимое может быть разным (если например изменили сущ элемент)
            }
        });
    }

    // ----------------------------------------------------------------------

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ItemViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    // при вызове этого кода sortedList сравнит свое содержимое, с новым содержимым, найдет разницу
    // и вызовет необходимые функции, чтобы обновить только те эдлементы которые нужны
    // а recyclerView проиграет соответствующие анимации только для этих элементов
    public void setItems(List<Item> items) {
        sortedList.replaceAll(items);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView itemText;
        TextView itemNumber;
        View delete;

        Item item;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemText = itemView.findViewById(R.id.itemText);
            itemNumber = itemView.findViewById(R.id.itemNumber);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ItemDetailsActivity.start((Activity) itemView.getContext(), item);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.getInstance().getItemDao().delete(item);
                }
            });
        }

        public void bind(Item item) {
            this.item = item;

            itemText.setText(item.text);
            itemNumber.setText(String.valueOf(item.quantity));
        }
    }
}
