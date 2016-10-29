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

import br.com.dev.projeto.imagine.R;
import br.com.dev.projeto.imagine.domain.Imagine;

/**
 * Created by cedrim on 12/09/16.
 *Adapter Imagine*/
public class ImagineAdapter extends RecyclerView.Adapter<ImagineAdapter.ImagineViewHolder>
{
    private final List<Imagine> imagines;
    private final Context context;
    private ImagineOnClickListener imagineOnClickListener;

    /*Necessario para receber as informações*/
    public ImagineAdapter(Context context, List<Imagine> imagines, ImagineOnClickListener imagineOnClickListener)
    {
        this.context = context;
        this.imagines = imagines;
        this.imagineOnClickListener = imagineOnClickListener;
    }

    public int getItemCount()
    {
        return this.imagines != null ? this.imagines.size() : 0;
    }
    /*Criando holders para serem manipuladas*/
    @Override
    public ImagineViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_container, parent, false);
        ImagineViewHolder holder = new ImagineViewHolder(view);

        return holder;
    }
    /*Setando holder e usando Picasso para recuperação da imagem*/
    @Override
    public void onBindViewHolder(final ImagineViewHolder holder, final int position)
    {
        Imagine imagine = imagines.get(position);

        holder.tNome.setText(imagine.getNome());

        holder.progressBar.setVisibility(View.VISIBLE);


        Picasso.with(context).load(imagine.getUrlFoto()).fit().into(holder.img,
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

        if (imagineOnClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    imagineOnClickListener.onClickImagine(holder.itemView, position);
                }
            });
        }
    }
    /*criando uma interface para o onClick*/
    public interface ImagineOnClickListener
    {
        void onClickImagine(View view, int idx);
    }

    /*Necessario por causa do recycle view, capturando holder e setando os layouts*/
    public static class ImagineViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tNome;
        ImageView img;
        ProgressBar progressBar;
        CardView cardView;

        public ImagineViewHolder(View view)
        {
            super(view);

            tNome = (TextView) view.findViewById(R.id.section_label);
            img = (ImageView) view.findViewById(R.id.imageView);
            progressBar = (ProgressBar) view.findViewById(R.id.progressImage);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }
    }
}

