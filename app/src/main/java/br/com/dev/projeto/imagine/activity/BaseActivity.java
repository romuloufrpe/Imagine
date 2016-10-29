package br.com.dev.projeto.imagine.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.dev.projeto.imagine.R;


/**
 * Created by cedrim on 12/09/16.
 *
 *
 * BaseActivity, informa as necessidades basica da aplicação*/
public class BaseActivity extends AppCompatActivity
{
    /*Setando Toolbar No topo da tela*/
    public void setUpToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }
    }
}
