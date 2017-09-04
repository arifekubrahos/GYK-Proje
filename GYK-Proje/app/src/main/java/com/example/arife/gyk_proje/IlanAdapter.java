package com.example.arife.gyk_proje;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Arife on 29.08.2017.
 */

public class IlanAdapter extends RecyclerView.Adapter<IlanAdapter.MyViewHolder>{

    private List<Announcements> currentList;
    private LayoutInflater inflater;

    //consturcture veritabından aldığımız listeyi atmamız için
    public IlanAdapter(List<Announcements> currentList){
        this.currentList = currentList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView contentText, descText, mailText, phoneText,addressText,nameText;
        private CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            nameText =itemView.findViewById(R.id.namehelpText);
            contentText =itemView.findViewById(R.id.contenthelpText);
            descText =itemView.findViewById(R.id.deschelpText);
            mailText =itemView.findViewById(R.id.mailhelpText);
            phoneText = itemView.findViewById(R.id.phonehelpText);
            addressText =itemView.findViewById(R.id.addresshelpText);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activitiy_ilan_card,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Announcements ann = currentList.get(position);
        if(ann.isTemplate() ==false){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#d7ccc8"));

        }
        else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffd180"));
        }

        holder.contentText.setText(ann.getContent());
        holder.descText.setText(ann.getDescription());
        holder.nameText.setText(ann.getName());

        if(ann.getEmail() == null)
            holder.mailText.setVisibility(View.INVISIBLE);
        else
            holder.mailText.setText("Mail:    "+ann.getEmail());
        if(ann.getAddress() == null)
            holder.addressText.setVisibility(View.INVISIBLE);
        else
            holder.addressText.setText("Adres:   "+ann.getAddress());
        if(ann.getPhone() == null)
            holder.phoneText.setVisibility(View.INVISIBLE);
        else
            holder.phoneText.setText("Telefon: "+ann.getPhone());

        new DownloadImage(holder.img).execute(String.valueOf(ann.getPhotoUrl()));

    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        ImageView img;

        public DownloadImage(ImageView img) {
            this.img = img;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            String urldisplay = strings[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            img.setImageBitmap(result);
        }
    }
    @Override
    public int getItemCount() {
        return currentList.size();
    }
}
