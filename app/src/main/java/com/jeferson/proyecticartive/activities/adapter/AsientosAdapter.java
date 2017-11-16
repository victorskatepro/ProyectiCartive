package com.jeferson.proyecticartive.activities.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeferson.proyecticartive.R;
import com.jeferson.proyecticartive.activities.models.Asiento;

import java.util.ArrayList;

/**
 * Created by JARVIS on 13/11/2017.
 */

public class AsientosAdapter extends ArrayAdapter<Asiento> {
    Context context;
    int layoutResourceId;
    ArrayList<Asiento> data = new ArrayList<Asiento>();

    public AsientosAdapter(Context context, int layoutResourceId, ArrayList<Asiento> data)
    {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        RecordHolder holder = null;

        try{
            if (row == null)
            {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);
                holder = new RecordHolder();
                holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
                holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
                row.setTag(holder);
            }
            else
            {
                holder = (RecordHolder) row.getTag();
            }
            Asiento asiento = data.get(position);
            holder.txtTitle.setText(asiento.getEstado());
            holder.imageItem.setImageBitmap(asiento.getImage());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return row;
    }
    public static class RecordHolder
    {
        public TextView txtTitle;
        public ImageView imageItem;

    }
}
