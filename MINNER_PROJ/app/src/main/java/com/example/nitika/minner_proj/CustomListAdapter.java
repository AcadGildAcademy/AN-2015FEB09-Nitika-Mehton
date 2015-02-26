package com.example.nitika.minner_proj;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by NITIKA on 26-Feb-15.
 */
public class CustomListAdapter extends ArrayAdapter<Custom_class> {
    private Context context;
    List<Custom_class> products;
    SharedPreferences sharedPreference;

    public CustomListAdapter(Context context, List<Custom_class> products) {
        super(context, R.layout.custom_list_layout, products);
        this.context = context;
        this.products = products;

    }
    private class ViewHolder {
        TextView productNameTxt;
        TextView productDescTxt;
        TextView productPriceTxt;
        ImageView favoriteImg;
    }


    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Custom_class getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_list_layout, null);
            holder = new ViewHolder();
            holder.productNameTxt = (TextView) convertView
                    .findViewById(R.id.txt_pdt_name);
            holder.productDescTxt = (TextView) convertView
                    .findViewById(R.id.txt_pdt_desc);
            holder.productPriceTxt = (TextView) convertView
                    .findViewById(R.id.txt_pdt_price);
            holder.favoriteImg = (ImageView) convertView
                    .findViewById(R.id.imgbtn_favorite);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Custom_class cus = (Custom_class) getItem(position);
        holder.productNameTxt.setText(cus.getName());
        holder.productDescTxt.setText(cus.getDescription());
        holder.productPriceTxt.setText(cus.getPrice() + "");
        return convertView;
    }
}

