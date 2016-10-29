package br.com.dev.projeto.imagine.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.dev.projeto.fiqueseguro.R;
import br.com.dev.projeto.fiqueseguro.adapter.TabsAdapter;

/**
 * Created by cedrim on 29/09/16.
 */

public class ImagineTabsFragment extends  BaseFragment implements TabLayout.OnTabSelectedListener
{
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_mecanica, container, false);

        mViewPager = (ViewPager) view.findViewById(R.id.view_page);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new TabsAdapter(getContext(), getChildFragmentManager()));

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        int cor = getContext().getResources().getColor(R.color.white);

        tabLayout.setTabTextColors(cor, cor);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.classicos));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.esportivos));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.luxo));

        tabLayout.setOnTabSelectedListener(this);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab)
    {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
