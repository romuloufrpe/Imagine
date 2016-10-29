package br.com.dev.projeto.imagine.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.dev.projeto.imagine.R;
import br.com.dev.projeto.imagine.adapter.ViewPagerAdapter;

public class MainActivity extends BaseActivity
{
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();
        /*Setando Adpters e dando o controle para a ViewPage*/
        mViewPagerAdapter = new ViewPagerAdapter(getContext(), getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.view_page);
        mViewPager.setAdapter(mViewPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        /*Botão Flutuante*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Colocar alguma ação comoda ou retirar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    /*Menu na Toolbar*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public Context getContext()
    {
        return this.context = this;
    }
}
