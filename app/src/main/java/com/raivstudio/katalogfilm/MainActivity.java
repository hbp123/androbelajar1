package com.raivstudio.katalogfilm;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<ItemFilm>> {
    EditText inputjdl;
    ImageView gbposter,btncari;
    ListView listView;
    AdapterFilm adapter ;

    static final String EXTRAS_FILM = "EXTRAS_FILM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter     = new AdapterFilm(this);
        adapter.notifyDataSetChanged();
        listView    = findViewById(R.id.lvFilm);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {

                ItemFilm item = (ItemFilm)parent.getItemAtPosition(position);

                Intent pindah = new Intent(MainActivity.this, DetailFilm.class);
                pindah.putExtra(DetailFilm.Extra_Judul, item.getJdlfilm());
                pindah.putExtra(DetailFilm.Extra_tanggal, item.getTglfilm());
                pindah.putExtra(DetailFilm.Extra_rating, item.getRatefilm());
                pindah.putExtra(DetailFilm.Extra_Sinopsis, item.getSinopsis());
                pindah.putExtra(DetailFilm.Extra_poster, item.getGbrfilm());
                pindah.putExtra(DetailFilm.Extra_backdrop, item.getGbrbackdrop());
                MainActivity.this.onPause();
                startActivity(pindah);
            }
        });

        inputjdl  = findViewById(R.id.edt_judul);
        btncari    = findViewById(R.id.tmblcari);
        gbposter   = findViewById(R.id.gbposter);
        btncari.setOnClickListener(movieListener);

        String judul_film= inputjdl.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_FILM, judul_film);

        getLoaderManager().initLoader(0, bundle, this);
    }

    @Override
    public Loader<ArrayList<ItemFilm>> onCreateLoader(int i, Bundle bundle) {
        String judulFilm = "";
        if (bundle != null){
            judulFilm = bundle.getString(EXTRAS_FILM);
        }

        return new AsynctaskLoaderFilm(this, judulFilm);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ItemFilm>> loader, ArrayList<ItemFilm> itemFilm) {
        adapter.setData(itemFilm);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<ItemFilm>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener movieListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String _judulMovie = inputjdl.getText().toString();
            if(TextUtils.isEmpty(_judulMovie)){
                Toast.makeText(MainActivity.this, "Isikan judul film lebih dulu !",
                        Toast.LENGTH_SHORT).show();
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_FILM, _judulMovie);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}
