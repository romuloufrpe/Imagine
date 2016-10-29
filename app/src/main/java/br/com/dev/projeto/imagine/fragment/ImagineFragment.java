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

import br.com.dev.projeto.imagine.R;
import br.com.dev.projeto.imagine.activity.ImagineActivity;
import br.com.dev.projeto.imagine.activity.MySingleton;
import br.com.dev.projeto.imagine.adapter.ImagineAdapter;
import br.com.dev.projeto.imagine.domain.Imagine;
import br.com.dev.projeto.imagine.domain.ImagineService;


/**
 * Created by cedrim on 12/09/16.
 */
public class ImagineFragment extends BaseFragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final boolean LOG_ON = false;
    private static final String TAG = "CarroService";
    private static final String URL = "http://www.livroandroid.com.br/livro/carros/carros_{tipo}.json";
    private static int tabNumber;
    protected RecyclerView recyclerView;
    String tipo;
    private List<Imagine> imagines;
    private LinearLayoutManager linearLayoutManager;


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

/*
    private class MecanicaTask
    {

        //ImagineService mecanicaService = new ImagineService();



    }

*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_imagine, container, false);

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

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskCarros();

    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    private void taskCarros()
    {
        //this.imagines = ImagineService.getMecanicas(getContext());

        try {
            //Log.e(TAG, imagines.get(0).getNome() + "encontrados d d.");

             RequestQueue requestQueue = MySingleton.getInstance(getContext()).getRequestQueue();

            ImagineService mecanicaService = new ImagineService();

            getImagines(getContext(), tipo);

            String TAG = "ola";

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImagineAdapter.ImagineOnClickListener onClickImagine()
    {
        return new ImagineAdapter.ImagineOnClickListener()
        {
            @Override
            public void onClickImagine(View view, int idx)
            {
                Bundle args = new Bundle();

                Imagine imagine = imagines.get(idx);

                Intent intent = new Intent(getContext(), ImagineActivity.class);

                intent.putExtra("nome", imagine.getNome());
                intent.putExtra("image", imagine.getUrlFoto());

                startActivity(intent);
                Toast.makeText(getContext(), "Imagine: " + imagine.getNome(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public void getImagines(Context context, String tipo) throws IOException
    {

    final List<Imagine> listaImagines = new ArrayList<>();



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

                                Imagine imagine = new Imagine();

                                imagine.setNome(jsonCarro.getString("nome"));
                                imagine.setUrlFoto(jsonCarro.getString("url_foto"));

                                Log.e(TAG, imagine.getNome() + "Grupo");

                                listaImagines.add(imagine);
                                if (listaImagines != null)
                                {
                                    ImagineFragment.this.imagines = listaImagines;

                                    recyclerView.setAdapter(new ImagineAdapter(getContext(), listaImagines, onClickImagine()));
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
//        Log.e(TAG, this.imagines.toString() + "encontradosasdasd");

    }


}






