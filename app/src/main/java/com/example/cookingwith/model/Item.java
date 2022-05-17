package com.example.cookingwith.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Item implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "text")
    public String text;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "type")
    public String dishType;

    public Item() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return uid == item.uid &&
                timestamp == item.timestamp &&
                quantity == item.quantity &&
                Objects.equals(text, item.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, text, timestamp, quantity, dishType);
    }

    protected Item(Parcel in) {
        uid = in.readInt();
        text = in.readString();
        timestamp = in.readLong();
        quantity = in.readInt();
        dishType = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(text);
        dest.writeLong(timestamp);
        dest.writeInt(quantity);
        dest.writeString(dishType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
