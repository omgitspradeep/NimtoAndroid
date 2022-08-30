package com.example.nimto0.adapters;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nimto0.R;
import com.example.nimto0.utils.Constants;

import java.util.List;

public class CustomizedGalleryAdapter extends BaseAdapter {

    private final Context context;
    private final List<com.example.nimto0.models.marriagedata.Gallery> imagesList;

    public CustomizedGalleryAdapter(Context c, List<com.example.nimto0.models.marriagedata.Gallery> images) {
        context = c;
        this.imagesList = images;
    }

    // returns the number of images, in our example it is 10
    public int getCount() {
        return imagesList.size();
    }

    // returns the Item of an item, i.e. for our example we can get the image
    public Object getItem(int position) {
        return position;
    }

    // returns the ID of an item
    public long getItemId(int position) {
        return position;
    }

    // returns an ImageView view
    public View getView(int position, View convertView, ViewGroup parent) {
        // position argument will indicate the location of image
        // create a ImageView programmatically
        ImageView imageView = new ImageView(context);

        // set image in ImageView
        Glide.with(context)
                .load(Constants.baseURL+imagesList.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.book_loading)
                .into(imageView);


        // set ImageView param
        imageView.setLayoutParams(new Gallery.LayoutParams(200, 200));
        return imageView;
    }
}
