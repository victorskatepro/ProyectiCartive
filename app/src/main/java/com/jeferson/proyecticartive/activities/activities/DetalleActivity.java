package com.jeferson.proyecticartive.activities.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jeferson.proyecticartive.R;
import com.jeferson.proyecticartive.activities.adapter.AsientosAdapter;
import com.jeferson.proyecticartive.activities.models.Asiento;
import com.jeferson.proyecticartive.activities.servicies.ApiService;
import com.jeferson.proyecticartive.activities.servicies.ApiServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;

public class DetalleActivity  extends Activity implements AdapterView.OnItemClickListener {

    private Integer id,numasientos;
    GridView  gridView;
    AsientosAdapter customGridAdapter;
    ArrayList<Asiento> gridArray = new ArrayList<Asiento>();
    AsientosAdapter asientosAdapter;
    public Bitmap seatIcon;
    public Bitmap seatSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        id = getIntent().getExtras().getInt("ID");
        numasientos = getIntent().getExtras().getInt("NumAsientos");

        seatIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.seat_layout_screen_nor_avl);
        seatSelect = BitmapFactory.decodeResource(this.getResources(), R.drawable.seat_layout_screen_nor_std);
        totalSeat(50);

        gridView = (GridView) findViewById(R.id.gridView1);
        customGridAdapter = new AsientosAdapter(this, R.layout.seaterow_item, gridArray);
        gridView.setAdapter(customGridAdapter);
        gridView.setOnItemClickListener(this);
    }
    private void initialize(){
        ApiService service = ApiServiceGenerator.createService(ApiService.class);
        Call<Asiento> call = service.showAsiento(id);
    }
    private void totalSeat(int n)
    {
        for(int i = 1; i <= n; ++i)
        {
            gridArray.add(new Asiento("seat " + i, seatIcon));

        }
    }
    public void seatSelected(int pos)
    {
        gridArray.remove(pos);
        gridArray.add(pos, new Asiento("select", seatSelect));
        customGridAdapter.notifyDataSetChanged();
    }
    public void seatDeselcted(int pos)
    {

        gridArray.remove(pos);
        int i = pos + 1;
        gridArray.add(pos, new Asiento("seat" + i,seatIcon));
        customGridAdapter.notifyDataSetChanged();
    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        Asiento asiento = gridArray.get(position);
        Bitmap seatcompare = asiento.getImage();
        if (seatcompare == seatIcon)
        {
            seatSelected(position);
        }
        else
        {
            seatDeselcted(position);

        }

    }
}
