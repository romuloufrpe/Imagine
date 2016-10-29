package br.com.dev.projeto.imagine.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.dev.projeto.fiqueseguro.R;
import br.com.dev.projeto.fiqueseguro.activity.MecanicaActivity;
import br.com.dev.projeto.fiqueseguro.activity.MySingleton;
import br.com.dev.projeto.fiqueseguro.adapter.MecanicaAdapter;
import br.com.dev.projeto.fiqueseguro.domain.Mecanica;
import br.com.dev.projeto.fiqueseguro.domain.MecanicaService;


/**
 * Created by cedrim on 12/09/16.
 */
public class ImagineFragment extends BaseFragment
{
    private static int tabNumber;
    protected RecyclerView recyclerView;
    private List<Mecanica> mecanicas;
    private LinearLayoutManager linearLayoutManager;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public ImagineFragment()
    {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ImagineFragment newInstance(int sectionNumber)
    {
        ImagineFragment fragment = new ImagineFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_mecanica, container, false);

        if(getArguments() != null)
        {
            this.tipo = getArguments().getString("tipo");
        }
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleView);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

         return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskCarros();

    }



    private void taskCarros()
    {
        //this.mecanicas = ImagineService.getMecanicas(getContext());

        try {
            //Log.e(TAG, mecanicas.get(0).getNome() + "encontrados d d.");

             RequestQueue requestQueue = MySingleton.getInstance(getContext()).getRequestQueue();

            MecanicaService mecanicaService = new MecanicaService();

            getMecanicas(getContext(), tipo);

            String TAG = "ola";

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private MecanicaAdapter.MecanicaOnClickListener onClickMecanica()
    {
        return new MecanicaAdapter.MecanicaOnClickListener()
        {
            @Override
            public void onClickMecanica(View view, int idx)
            {
                Bundle args = new Bundle();

                Mecanica mecanica = mecanicas.get(idx);



                Intent intent = new Intent(getContext(), MecanicaActivity.class);

                intent.putExtra("nome", mecanica.getNome());
                intent.putExtra("image", mecanica.getUrlFoto());

                startActivity(intent);
                Toast.makeText(getContext(), "Imagine: " + mecanica.getNome(), Toast.LENGTH_SHORT).show();
            }
        };
    }

/*
    private class MecanicaTask
    {

        //ImagineService mecanicaService = new ImagineService();



    }

*/


    private static final boolean LOG_ON = false;
    private static final String TAG = "CarroService";
    private static final String URL = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.json";
    String tipo;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public void getMecanicas(Context context, String tipo) throws IOException
    {

    final List<Mecanica> listaMecanicas = new ArrayList<>();



        //String position = String.valueOf(tabLayout.getTabCount());

        //TabLayout tabLayout = (TabLayout) recyclerView.findViewById(R.id.tabs);


        String url = URL.replace("{tipo}", tipo);

        Log.e(TAG, url + "URL");

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {

                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            JSONObject jsonObject = response.getJSONObject("carros");
                            JSONArray jsonArray = jsonObject.getJSONArray("carro");

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject jsonCarro = jsonArray.getJSONObject(i);

                                Mecanica mecanica = new Mecanica();

                                mecanica.setNome(jsonCarro.getString("nome"));
                                mecanica.setUrlFoto(jsonCarro.getString("url_foto"));

                                Log.e(TAG, mecanica.getNome() + "Grupo");

                                listaMecanicas.add(mecanica);
                                if (listaMecanicas != null)
                                {
                                    ImagineFragment.this.mecanicas = listaMecanicas;

                                    recyclerView.setAdapter(new MecanicaAdapter(getContext(), listaMecanicas, onClickMecanica()));
                                }
                                // Log.e(TAG, mecanica.getNome() + "encontrados");

                                if (LOG_ON)
                                {
                                    Log.e(TAG, jsonArray.length() + "encontrados.");
                                }

                            }

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        // TODO Auto-generated method stub
                        Log.e("VOLLEY", "ERRO");
                    }
                });

        // Access the RequestQueue through your singleton class.

        MySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
//
//        Log.e(TAG, this.mecanicas.toString() + "encontradosasdasd");

    }


}






