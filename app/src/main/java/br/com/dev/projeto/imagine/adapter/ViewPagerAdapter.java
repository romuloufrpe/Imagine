package br.com.dev.projeto.imagine.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.dev.projeto.imagine.R;
import br.com.dev.projeto.imagine.fragment.ImagineFragment;

/**
 * Created by cedrim on 12/09/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter
{
    private Context context;
    public ViewPagerAdapter(Context context, FragmentManager fm)
    {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position)
    {
        // getItem is called to instantiate the fragment for the given page.
        // Return a ImagineFragment (defined as a static inner class below).
        Bundle args = new Bundle();
        if(position == 0)
        {
            args.putString("tipo", "classicos");
        }else if(position == 1)
        {
            args.putString("tipo", "esportivos");
        }else
        {
            args.putString("tipo", "luxo");
        }
        //return ImagineFragment.newInstance(position + 1);
        Fragment f = new ImagineFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public int getCount()
    {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:
                return context.getString(R.string.classicos);
               // return "CLASSICOS";
            case 1:
                return context.getString(R.string.esportivos);
                //return "ESPORTIVOS";
            case 2:
                return context.getString(R.string.luxo);
                //return "LUXO";
        }
        return null;
    }
}