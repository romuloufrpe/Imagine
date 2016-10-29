package br.com.dev.projeto.imagine.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.dev.projeto.fiqueseguro.fragment.MecanicaFragment;

/**
 * Created by cedrim on 29/09/16.
 */

public class TabsAdapter extends FragmentPagerAdapter
{
    private Context context;
    public TabsAdapter(Context context, FragmentManager fm)
    {
        super(fm);
        this.context = context;
    }



    @Override
    public int getCount()
    {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        if (position == 0)
        {
            //return context.getString(R.string.classicos);
            return "CLASSICOS";
        }else if (position == 1)
        {
           // return context.getString(R.string.esportivos);
            return "ESPORTIVOS";
        }else
        {
           // return context.getString(R.string.luxo);
            return "LUXO";
        }
    }

    @Override
    public Fragment getItem(int position)
    {
        Bundle args = new Bundle();

        if(position == 0)
        {
            args.putString("tipo", "classico");
        }else if(position == 1)
        {
            args.putString("tipo", "esportivos");
        }else
        {
            args.putString("tipo", "luxo");
        }
        Fragment f = new MecanicaFragment();
        f.setArguments(args);

        return f;
    }
}
