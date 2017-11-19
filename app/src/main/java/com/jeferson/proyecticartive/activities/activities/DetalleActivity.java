package com.jeferson.proyecticartive.activities.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.jeferson.proyecticartive.R;
import com.jeferson.proyecticartive.activities.adapter.AsientosAdapter;
import com.jeferson.proyecticartive.activities.models.Asiento;
import com.jeferson.proyecticartive.activities.servicies.ApiService;
import com.jeferson.proyecticartive.activities.servicies.ApiServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleActivity  extends Activity {
    private static final String TAG = DetalleActivity.class.getSimpleName();

    private Integer id,numasientos;
    GridView  gridView;
    AsientosAdapter customGridAdapter;
    ArrayList<Asiento> gridArray = new ArrayList<Asiento>();
    AsientosAdapter asientosAdapter;
    public Bitmap seatIcon;
    public Bitmap seatSelect;
    public Bitmap seatDisabled;
    private int ocupado[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        id = getIntent().getExtras().getInt("ID");
        numasientos = getIntent().getExtras().getInt("NumAsientos");
        Log.d(TAG,"elid"+id);
        seatIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.seat_layout_screen_nor_avl);
        seatSelect = BitmapFactory.decodeResource(this.getResources(), R.drawable.seat_layout_screen_nor_std);
        seatDisabled = BitmapFactory.decodeResource(this.getResources(), R.drawable.seat_layout_reserved);


        gridView = (GridView) findViewById(R.id.gridView1);


        initialize();

    }
    private void initialize(){
        ApiService service = ApiServiceGenerator.createService(ApiService.class);
        Call<List<Asiento>> call = service.showAsiento(id);
        call.enqueue(new Callback<List<Asiento>>() {
            @Override
            public void onResponse(Call<List<Asiento>> call, Response<List<Asiento>> response) {
                try {

                    int statusCode = response.code();
                    Log.d(TAG, "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {
                       List<Asiento> asientos  = response.body();
                       rellenando(asientos);
                        //for(int i = 1; i<=50; i++)//iterar todos los asientos con for()
//                        {
//
////                            if(asientos.getEstado().equals("libre")) { // si el asiento esta libre.
////                                gridArray.add(new Asiento("Libre " + asiento.getNum_asiento(), seatIcon));
////                                Log.d(TAG,"numeroasiento"+asiento.getNum_asiento());
////                            }else{
////                                gridArray.add(new Asiento("Ocupado " + asiento.getNum_asiento(), seatDisabled));
////                                Log.d(TAG,"numeroasientoocupado"+asiento.getNum_asiento());
////                            }
//                            gridArray.add(new Asiento("Ocupado " + i, seatDisabled));
//                        }
                        Log.d(TAG, "asientos: " + asientos);
                    } else {
                        Log.e(TAG, "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }
                } catch (Throwable t) {
                    try {
                        Log.e(TAG, "onThrowable: " + t.toString(), t);
                        Toast.makeText(DetalleActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }
            @Override
            public void onFailure(Call<List<Asiento>> call, Throwable t) {
            Log.e(TAG, "onFailure: " + t.toString());
            Toast.makeText(DetalleActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }
    public void rellenando(List<Asiento> asientos){
        //Iterator iter = asientos.listIterator();
        //iter.get

        for(Asiento asiento : asientos){
           ocupado = new int[asientos.size()];
            int i = 1;
            i = i +1;
            ocupado[i] = asiento.getNum_asiento();
            Log.d(TAG, "asientosocupados: "+ocupado[0]);
        }

               // Cuando responde
               for (int i = 1; i <= numasientos; i++) {
                   // for (Asiento asiento : asientos)//iterar todos los asientos con for()
                   //{
//                            if(asientos.getEstado().equals("libre")) { // si el asiento esta libre.
//                                gridArray.add(new Asiento("Libre " + asiento.getNum_asiento(), seatIcon));
//                                Log.d(TAG,"numeroasiento"+asiento.getNum_asiento());
//                            }else{
//                                gridArray.add(new Asiento("Ocupado " + asiento.getNum_asiento(), seatDisabled));
//                                Log.d(TAG,"numeroasientoocupado"+asiento.getNum_asiento());
//                            }
                   //}
                   int s = 0;
                       if(ocupado[s] == i){
                           gridArray.add(new Asiento("Ocupado ", seatDisabled));
                           Log.d(TAG,"asientoocupado2:" + ocupado[s]);
                       }
                   //Log.d(TAG, "asientolibre: "+asiento.getNum_asiento());
                   s = s +1;

                       gridArray.add(new Asiento("Seat " + i, seatIcon));

           }
        customGridAdapter = new AsientosAdapter(this, R.layout.seaterow_item, gridArray);
        Log.d(TAG, "adaptando");
        gridView.setAdapter(customGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Asiento asiento = gridArray.get(position);

                // si es que el asiento está libre ...

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
        });
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


}
