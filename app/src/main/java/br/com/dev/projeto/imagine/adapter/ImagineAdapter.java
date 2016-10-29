package br.com.dev.projeto.imagine.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.dev.projeto.fiqueseguro.R;
import br.com.dev.projeto.fiqueseguro.domain.Mecanica;

/**
 * Created by cedrim on 12/09/16.
 *Adapter Imagine*/
public class ImagineAdapter extends RecyclerView.Adapter<ImagineAdapter.MecanicasViewHolder>
{
    protected static final String TAG = "mecanica";
    private final List<Mecanica> mecanicas;
    private final Context context;
    private MecanicaOnClickListener mecanicaOnClickListener;

    /*Necessario para receber as informações*/
    public ImagineAdapter(Context context, List<Mecanica> mecanicas, MecanicaOnClickListener mecanicaOnClickListener)
    {
        this.context = context;
        this.mecanicas = mecanicas;
        this.mecanicaOnClickListener = mecanicaOnClickListener;
    }

    public int getItemCount()
    {
        return this.mecanicas != null ? this.mecanicas.size() : 0;
    }
    /*Criando holders para serem manipuladas*/
    @Override
    public MecanicasViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_container, parent, false);
        MecanicasViewHolder holder = new MecanicasViewHolder(view);

        return holder;
    }
    /*Setando holder e usando Picasso para recuperação da imagem*/
    @Override
    public void onBindViewHolder(final MecanicasViewHolder holder, final int position)
    {
        Mecanica mecanica = mecanicas.get(position);

        holder.tNome.setText(mecanica.getNome());

        holder.progressBar.setVisibility(View.VISIBLE);


        Picasso.with(context).load(mecanica.getUrlFoto()).fit().into(holder.img,
                new com.squareup.picasso.Callback()
                {
                    @Override
                    public void onSuccess()
                    {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError()
                    {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });

        if (mecanicaOnClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    mecanicaOnClickListener.onClickMecanica(holder.itemView, position);
                }
            });
        }
    }
    /*criando uma interface para o onClick*/
    public interface MecanicaOnClickListener
    {
        void onClickMecanica(View view, int idx);
    }

    /*Necessario por causa do recycle view, capturando holder e setando os layouts*/
    public static class MecanicasViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tNome;
        ImageView img;
        ProgressBar progressBar;
        CardView cardView;

        public MecanicasViewHolder(View view)
        {
            super(view);

            tNome = (TextView) view.findViewById(R.id.section_label);
            img = (ImageView) view.findViewById(R.id.imageView);
            progressBar = (ProgressBar) view.findViewById(R.id.progressImage);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}

