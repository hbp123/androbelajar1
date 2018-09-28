package com.raivstudio.katalogfilm;

import android.content.Context;
import android.view.*;
import android.widget.*;
import com.squareup.picasso.Picasso;
import java.text.*;
import java.util.*;

class AdapterFilm extends BaseAdapter{
    private ArrayList<ItemFilm> listFilm = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    public AdapterFilm(Context context) {
        this.context    = context;
        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<ItemFilm> items){
        listFilm = items;
        notifyDataSetChanged();
    }

    public void addItem(final ItemFilm item) {
        listFilm.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        listFilm.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (listFilm == null)
            return 0;

        return listFilm.size();
    }

    @Override
    public ItemFilm getItem(int position) {
        return listFilm.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder                  = new ViewHolder();
            convertView             = inflater.inflate(R.layout.item_movies, null);
            holder.jdl_film         = convertView.findViewById(R.id.tvJdl);
            holder.sin_film         = convertView.findViewById(R.id.tvSin);
            holder.tgl_film         = convertView.findViewById(R.id.tvTgl);
            holder.gb_film          = convertView.findViewById(R.id.gbposter);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.jdl_film.setText(listFilm.get(position).getJdlfilm());

        holder.sin_film.setText(listFilm.get(position).getSinopsis());

        String tanggal = listFilm.get(position).getTglfilm();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date date = format.parse(tanggal);

            SimpleDateFormat _formatbaru = new SimpleDateFormat("EEEE, dd - mm - yyyy");
            String _tanggalRilis = _formatbaru.format(date);
            holder.tgl_film.setText(_tanggalRilis);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Picasso.with(context).load("http://image.tmdb.org/t/p/w154/"+ listFilm
                .get(position)
                .getGbrfilm())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.gb_film);

        return convertView;
    }

    private static class ViewHolder {
        TextView jdl_film;
        TextView sin_film;
        TextView tgl_film;
        ImageView gb_film;
    }
}
