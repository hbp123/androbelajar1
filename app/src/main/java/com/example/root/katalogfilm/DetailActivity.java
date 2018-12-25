package com.example.root.katalogfilm;import android.support.design.widget.CollapsingToolbarLayout;import android.support.v4.content.ContextCompat;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.support.v7.widget.Toolbar;import android.view.Window;import android.widget.ImageView;import android.widget.TextView;import com.squareup.picasso.Picasso;import java.text.ParseException;import java.text.SimpleDateFormat;import java.util.Date;public class DetailActivity extends AppCompatActivity {    public static String DETAIL_TITLE       = "detail_title";    public static String DETAIL_RELEASE     = "detail_release";    public static String DETAIL_OVERVIEW    = "detail_overview";    public static String DETAIL_BACKDROP    = "detail_backdrop";    public static String DETAIL_VOTE        = "detail_vote";    public static String DETAIL_AVG         = "detail_avg";    public static String DETAIL_POPULARITY  = "detail_popularity";    private TextView title, rating, overview, release, popularity;    private ImageView poster;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        this.requestWindowFeature(Window.FEATURE_NO_TITLE);        setContentView(R.layout.activity_detail);        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);        setSupportActionBar(toolbar);        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collaps);        collapsingToolbarLayout.setTitle(getString(R.string.movie_detail));        collapsingToolbarLayout.setCollapsedTitleTextColor(                ContextCompat.getColor(this, R.color.colorAccent));        collapsingToolbarLayout.setExpandedTitleColor(                ContextCompat.getColor(this, R.color.colorPrimary));        title       = (TextView)findViewById(R.id.detail_title);        rating      = (TextView)findViewById(R.id.detail_rating);        overview    = (TextView)findViewById(R.id.detail_overview);        release     = (TextView)findViewById(R.id.detail_release);        poster      = (ImageView)findViewById(R.id.detail_poster);        popularity  = (TextView)findViewById(R.id.detail_popularity);        //setting detail title        title.setText(getIntent().getStringExtra(DETAIL_TITLE));        //setting overview        overview.setText(getIntent().getStringExtra(DETAIL_OVERVIEW));        //rating setting data        rating.setText("Ratings "+getIntent().getStringExtra(DETAIL_AVG)+" /10");        //setting popularity        popularity.setText(getIntent().getStringExtra(DETAIL_POPULARITY)+" "+getString(R.string.user_view));        //setting release date        String getDate = getIntent().getStringExtra(DETAIL_RELEASE);        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");        try{            Date date = dateFormat.parse(getDate);            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MM dd, yyyy");            String setDate = simpleDateFormat.format(date);            release.setText(setDate);        } catch (ParseException e) {            e.printStackTrace();        }        //setting backdrop        Picasso.with(this).load("http://image.tmdb.org/t/p/w500/"+getIntent().getStringExtra(DETAIL_BACKDROP)).into(poster);    }}