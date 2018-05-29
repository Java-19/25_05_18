package com.sheygam.java_19_25_05_18;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Person> persons;
    private MyAdapterCallback callback;

    public MyAdapter() {
        persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(PersonManager.getPerson(i));
        }
    }

    public void setCallback(MyAdapterCallback callback){
        this.callback = callback;
    }

    public void addPerson(){
        Person p = new Person("Name","email","");
        persons.add(2,p);
        notifyItemInserted(2);
    }

    public void remove(){
        persons.remove(4);
        notifyItemRemoved(4);
    }

    public void remove(int index){
        persons.remove(index);
        notifyItemRemoved(index);
    }

    public void cancelRemove(int index){
        notifyItemChanged(index);
    }

    public void move(int from, int to){
        Person tmp = persons.remove(from);
        persons.add(to,tmp);
        notifyItemMoved(from,to);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MY_TAG", "onCreateViewHolder: ");

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("MY_TAG", "onBindViewHolder: " + position);

        Person p = persons.get(position);
        holder.nameTxt.setText(p.getName());
        holder.emailTxt.setText(p.getEmail());
        Picasso.get().load(p.getUrl()).into(holder.avatarImg);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView avatarImg;
        private TextView nameTxt, emailTxt;

        public MyViewHolder(View itemView) {
            super(itemView);
            avatarImg = itemView.findViewById(R.id.imageView);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            emailTxt = itemView.findViewById(R.id.emailTxt);
            itemView.setOnClickListener(this);
            avatarImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.imageView){
                if(callback != null){
                    callback.onImgClicked(getAdapterPosition());
                }
            }else {
                if (callback != null) {
                    callback.onRowClicked(getAdapterPosition(), persons.get(getAdapterPosition()));
                }
            }
        }
    }

    interface MyAdapterCallback{
        void onRowClicked(int position, Person person);
        void onImgClicked(int position);
    }
}
