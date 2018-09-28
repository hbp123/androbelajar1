package com.raivstudio.katalogfilm;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DetailFilm extends AppCompatActivity {

    public static String Extra_Judul    = "Judul";
    public static String Extra_tanggal  = "Tanggal";
    public static String Extra_rating   = "Rating";
    public static String Extra_Sinopsis = "Sinopsis";
    public static String Extra_poster   = "Poster";
    public static String Extra_backdrop = "Bakcdrop";

    TextView Tvjudul, Tvtanggal, Tvrating, Tvsinopsis;
    ImageView Ivposter, Ivbackdrop;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        String judul, tanggal, rating, sinopsis, poster, backdrop;

        Tvjudul       = findViewById(R.id.detailjudul);
        Tvtanggal     = findViewById(R.id.tvRilis);
        Tvrating      = findViewById(R.id.tvRating);
        Tvsinopsis    = findViewById(R.id.tvSinopsis);
        Ivposter      = findViewById(R.id.ivposter);
        Ivbackdrop    = findViewById(R.id.ivbackdrop);

        judul    = getIntent().getStringExtra(Extra_Judul);
        tanggal  = getIntent().getStringExtra(Extra_tanggal);
        rating   = getIntent().getStringExtra(Extra_rating);
        sinopsis = getIntent().getStringExtra(Extra_Sinopsis);
        poster   = getIntent().getStringExtra(Extra_poster);
        backdrop = getIntent().getStringExtra(Extra_backdrop);

        Tvjudul.setText(judul);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(tanggal);
            SimpleDateFormat _format = new SimpleDateFormat("EEEE, dd MMM yyyy");
            String _tanggalrilis = _format.format(date);
            Tvtanggal.setText(_tanggalrilis);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Tvrating.setText("Ratings IMDB = "+rating+"/10");

        Tvsinopsis.setText(sinopsis);

        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w342/"+poster)
                .into(Ivposter);

        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/original/"+backdrop)
                .into(Ivbackdrop);

        }
    }

