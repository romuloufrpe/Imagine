package br.com.dev.projeto.imagine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.dev.projeto.imagine.R;
import br.com.dev.projeto.imagine.domain.Imagine;


public class ImagineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagine);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ImageView imageView = (ImageView) findViewById(R.id.imageMecanica);
        TextView textView = (TextView) findViewById(R.id.textMecanica);

        Intent intent = getIntent();

        Imagine imagine = new Imagine();

        imagine.setNome(intent.getStringExtra("nome"));
        imagine.setUrlFoto(intent.getStringExtra("image"));


        Picasso.with(this).load(imagine.getUrlFoto()).into(imageView);
        textView.setText(imagine.getNome());


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
