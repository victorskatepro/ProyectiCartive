package com.jeferson.proyecticartive.activities.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.jeferson.proyecticartive.R;
import com.jeferson.proyecticartive.activities.activities.ConsultaActivity;
import com.jeferson.proyecticartive.activities.adapter.CiudadAdapter;
import com.jeferson.proyecticartive.activities.models.Ciudad;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PrincipalFragment extends Fragment implements DatePickerListener {
    private static final String TAG = PrincipalFragment.class.getSimpleName();
    private CiudadAdapter adapter;
    private SearchView txtlima;
    private ListView lista;
    private DatePickerDialog datePickerDialog;
    ArrayList<Ciudad> listaCiudad;
    private String nombreCiudad;
    private PrincipalFragment homeActivity;
    private String fecha;
    private ImageButton imgselecion;
    private TextView txthoy,txtdesde,txta;
    private View rootView;
    private HorizontalPicker picker;
    private RelativeLayout sliderlayout;
    private Animation animation;
    private CardView fechaView;
    private LayoutAnimationController controller;
    private EditText edtlima;
    private ImageView imginfo;
    public PrincipalFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        lista = (ListView) view.findViewById(R.id.ContenlistView);
        txtlima = (SearchView) view.findViewById(R.id.buscador);
        txthoy = (TextView)view.findViewById(R.id.txthoy);
        picker = (HorizontalPicker) view.findViewById(R.id.datePicker);
        imgselecion = (ImageButton)view.findViewById(R.id.btnseleccionar);
        sliderlayout =(RelativeLayout) view.findViewById(R.id.animationlist);
        fechaView = (CardView) view.findViewById(R.id.fechView);
        edtlima = (EditText) view.findViewById(R.id.txtlima);
        imginfo = (ImageView) view.findViewById(R.id.imginformacion);
//        txtdesde.setTypeface(EasyFonts.robotoBold(view.getContext()));
//        txta.setTypeface(EasyFonts.robotoBold(view.getContext()));
//        edtlima.setTypeface(EasyFonts.robotoBold(view.getContext()));
//        rootView = view.findViewById(R.id.rootView);
//        txtlima.setQuery("",false);
//        rootView.requestFocus();
        //averiguar como poner en un metodo el onclick !!!!no olvidar
        imgselecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarItem();
            }
        });
        imginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 obtenerinformacion();
            }
        });
        //iniciar y configurar el date picker horizontal
        initPicker();
        //ocultar la lista
        lista.setVisibility(View.GONE);
        //rellenar la lista de las ciudades
        completarlist();
        //adapatar nuestra lista
        adapter = new CiudadAdapter(getActivity(), listaCiudad);
        lista.setAdapter(adapter);
        //mostrar la lista cuando se precione editar texto
        mostrarLista();
        //selecciona la ciudad
        seleccionCiudad();
        //filtrar la ciudad
        filtrarCiudad();
        imgselecion.setVisibility(View.GONE);
        return view;
    }
public void obtenerinformacion(){
    new SweetAlertDialog(getActivity(), SweetAlertDialog.CUSTOM_IMAGE_TYPE)
            .setTitleText("Proyecto Cartive")
            .setContentText("victor.saico@tecsup.edu.pe")
            .setCustomImage(R.drawable.logo_cartive)
            .show();
}
    public PrincipalFragment getHomeActivity() {
        return homeActivity;
    }

    public void setHome2Activity(PrincipalFragment home2Activity) {
        this.homeActivity = homeActivity;
    }

    private void animar(boolean mostrar) {
        AnimationSet set = new AnimationSet(true);
       Animation animation = null;
        if (mostrar) {
            //desde la esquina inferior derecha a la superior izquierda
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.1f);
        } else {    //desde la esquina superior izquierda a la esquina inferior derecha
            animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.1f, Animation.RELATIVE_TO_SELF, 0.0f);
        }
        //duración en milisegundos
        animation.setDuration(500);
        set.addAnimation(animation);
       LayoutAnimationController controller = new LayoutAnimationController(set, 0.25f);
        sliderlayout.setLayoutAnimation(controller);
        sliderlayout.startAnimation(animation);

        //lista.setLayoutAnimation(controller);
        //lista.startAnimation(animation);
//        //imgselecion.setLayoutAnimation

    }

    public void completarlist() {
        listaCiudad = new ArrayList<>();
        Ciudad ciu1 = new Ciudad("lima");
        Ciudad ciu2 = new Ciudad("Huancayo");
        Ciudad ciu3 = new Ciudad("Arequipa");
        Ciudad ciu4 = new Ciudad("Junin");
        Ciudad ciu5 = new Ciudad("Loreto");

        listaCiudad.add(ciu1);
        listaCiudad.add(ciu2);
        listaCiudad.add(ciu3);
        listaCiudad.add(ciu4);
        listaCiudad.add(ciu5);
    }

    public void filtrarCiudad() {
        txtlima.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                animar(true);
                lista.setVisibility(View.VISIBLE);
                adapter.getFilter().filter(query);
                return false;
            }
        });
    }

    public void seleccionCiudad() {
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                nombreCiudad = listaCiudad.get(i).getNombre();
                txtlima.setQueryHint(nombreCiudad);
                animar(false);
                lista.setVisibility(View.GONE);
                imgselecion.setVisibility(View.VISIBLE);

            }
        });
    }

    public void mostrarLista() {
        txtlima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animar(true);
                lista.setVisibility(View.VISIBLE);
            }
        });
    }


    public void seleccionarItem(){
        Log.d(TAG,"Enviar"+nombreCiudad+fecha);
        Intent intent = new Intent(getContext(), ConsultaActivity.class);
        intent.putExtra("nombre", nombreCiudad);
        intent.putExtra("fecha", fecha);
        startActivity(intent);
    }

    @Override
    public void onDateSelected(DateTime dateSelected) {
        Log.d(TAG, "Selected date is " + dateSelected.toString());
        int day = dateSelected.getDayOfWeek();
        int day3 = dateSelected.getDayOfMonth();
        int month = dateSelected.getMonthOfYear();
        int year = dateSelected.getYear();
        java.util.Date dia = new Date();
        int day2 = dia.getDay();
        int mes = dia.getMonth();
        mes = mes +1;
        int ano = dia.getYear();
        fecha = ""+year+"-"+month+"-"+day3;
        Log.d(TAG, "dia"+dia);
        //Toast.makeText(getContext(), "la fehca"+day+day2+"sis:"+mes+"cal:+"+month+ano, Toast.LENGTH_SHORT).show();
        //imprimir hoy en la seleccion de fecha
        if(mes == month && day == day2){
            txthoy.setText("Hoy");
        }else {
            txthoy.setText("");
        }
    }
    public void initPicker(){
        picker
                .setListener(this)
                //.setDays(10)
                .setOffset(10)
                .setDateSelectedColor(getResources().getColor(R.color.primary))
                .showTodayButton(false)
                .init();
        picker.setDate(new DateTime().plusDays(0));
        picker.setDate(new DateTime().plusDays(0));
    }
}
