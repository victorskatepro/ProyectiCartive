package com.jeferson.proyecticartive.activities.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeferson.proyecticartive.R;
import com.jeferson.proyecticartive.activities.activities.DetalleActivity;
import com.jeferson.proyecticartive.activities.models.Viaje;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JARVIS on 6/11/2017.
 */

public class ViajeAdapter extends RecyclerView.Adapter<ViajeAdapter.ViewHolder> {

    private List<Viaje> viajes;
    Activity activity;

    public ViajeAdapter(Activity activity){
        this.viajes = new ArrayList<>();
        this.activity = activity;
    }

    public void setViajesAdapter(List<Viaje> viajes) {
        this.viajes = viajes;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageempresa;
        public TextView nombreempresa;
        public TextView numerohoras;
        public TextView numeroasientos;
        public TextView hora;
        public TextView precio;
        private CardView detalleView;
        public ViewHolder(View itemView){
            super(itemView);
            imageempresa = (ImageView) itemView.findViewById(R.id.imgempresa);
            nombreempresa = (TextView) itemView.findViewById(R.id.txtempresanombre);
            numerohoras = (TextView) itemView.findViewById(R.id.txtnumerohoras);
            numeroasientos = (TextView) itemView.findViewById(R.id.txtnumeroasientos);
            hora = (TextView) itemView.findViewById(R.id.txthorapartida);
            precio = (TextView)itemView.findViewById(R.id.txtnumerocosto);
            detalleView = (CardView) itemView.findViewById(R.id.detalleCard);
            nombreempresa.setTypeface(EasyFonts.robotoBlack(itemView.getContext()));
            precio.setTypeface(EasyFonts.robotoBlack(itemView.getContext()));
            numerohoras.setTypeface(EasyFonts.robotoMedium(itemView.getContext()));
            numeroasientos.setTypeface(EasyFonts.robotoMedium(itemView.getContext()));
            hora.setTypeface(EasyFonts.robotoBlack(itemView.getContext()));

        }
    }
    @Override
    public ViajeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viaje, parent, false);

        return new ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final ViajeAdapter.ViewHolder holder, int position) {
        final Viaje viaje = this.viajes.get(position);
         //holder.nombreempresa.setText(viaje.get);
        holder.numerohoras.setText(""+viaje.getNumerohoras()+" hrs");
        holder.numeroasientos.setText(""+viaje.getNumeroticketsdisponibles()+" asientos");
        holder.hora.setText(viaje.getHorapartida());
        holder.precio.setText("PEN "+viaje.getPrecio());
        //String url = ApiService.API_BASE_URL + "/images/" + viaje.getImagen();
       // Picasso.with(holder.itemView.getContext()).load(url).into(holder.imageempresa);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetalleActivity.class);
                intent.putExtra("ID",viaje.getBus_id());
                intent.putExtra("NumAsientos",viaje.getNumerotickets());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.viajes.size();
    }


}
