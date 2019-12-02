package mathieu.r.View.Adaptater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mathieu.r.Model.Film;
import mathieu.r.R;
import mathieu.r.View.Activity.DetailObjectActivity;

import static android.support.constraint.Constraints.TAG;

public class ListFilmAdaptater extends RecyclerView.Adapter<ListFilmAdaptater.ViewHolder> implements Filterable {

    private ArrayList<Film> dataset;                                                                //List d'object Film
    private ArrayList<Film> datasetFull;                                                                //List d'object Film
    private Context context;

    public ListFilmAdaptater(Context context) {
        this.context = context;
        dataset = new ArrayList<>();datasetFull = new ArrayList<>(dataset);

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Film film = dataset.get(position);                                                          // Recuperation d'un film en fonction de son numero
        holder.titreView.setText(film.getTitle());                                                  // Initialisation du titre dans l'objet Film

        Glide.with(context)                                                                         // Recuperation d'un image en fonction du numero
                .load("https://starwars-visualguide.com/assets/img/films/" + film.getEpisode_id() + ".jpg")
                .centerCrop()                                                                       // URL de l'image
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() { // Sur click d'un object
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on: " + film.getTitle());

                // Appel DetailObjectActivity
                Intent intent = new Intent(context, DetailObjectActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("film",film); // Passage object film au nouvel activity
                intent.putExtras(bundle);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(ArrayList<Film> listFilm) {                                                     // Ajout d'un object dans la liste
        dataset.addAll(listFilm);
        datasetFull = new ArrayList<>(listFilm);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Film> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) { // Rien ecris dans la barre
                filteredList.addAll(datasetFull);
            } else { // Quelque chose ecrit dans la barre de recherche
                String filterPattern = constraint.toString().toLowerCase().trim(); // Créé une nouvel string sans espace, en minuscule

                for(Film item : datasetFull) { // on parcours notre list
                    if (item.getTitle().toLowerCase().contains(filterPattern)) { // Si l'item courant == taper dans la bare de recherche
                        filteredList.add(item);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataset.clear();
            dataset.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titreView;

        public ViewHolder( View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            titreView = (TextView) itemView.findViewById(R.id.titreView);

        }
    }
}
