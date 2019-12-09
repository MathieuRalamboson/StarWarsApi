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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import mathieu.r.Model.Film;
import mathieu.r.Model.Starships;
import mathieu.r.R;
import mathieu.r.View.Activity.DetailObjectActivity;

import static android.support.constraint.Constraints.TAG;

public class ListStarshipsAdaptater extends RecyclerView.Adapter<ListStarshipsAdaptater.ViewHolder> {

    private ArrayList<Starships> dataset;                                                                //List d'object Starships
    private ArrayList<Starships> datasetFull;
    private Context context;

    public ListStarshipsAdaptater(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
        datasetFull = new ArrayList<>(dataset);
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Starships starships = dataset.get(position);                                                          // Recuperation d'un Starships en fonction de son numero
        holder.titreView.setText(starships.getName());                                                  // Initialisation du titre dans l'objet Starships

        Glide.with(context)                                                                         // Recuperation d'un image en fonction du numero
                .load("https://starwars-visualguide.com/assets/img/starships/" + starships.getNumber() + ".jpg")
                .centerCrop()                                                                       // URL de l'image
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() { // Sur click d'un object
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on: " + starships.getName());

                // Appel DetailObjectActivity
                Intent intent = new Intent(context, DetailObjectActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("starships",starships); // Passage object film au nouvel activity
                intent.putExtras(bundle);

                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(ArrayList<Starships> listStarships) {                                                     // Ajout d'un object dans la liste
        dataset.addAll(listStarships);
        datasetFull = new ArrayList<>(dataset);
        notifyDataSetChanged();
    }

    public Filter getFilter() {
        return filter;
    }
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Starships> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) { // Rien ecris dans la barre
                filteredList.addAll(datasetFull);
            } else { // Quelque chose ecrit dans la barre de recherche
                String filterPattern = constraint.toString().toLowerCase().trim(); // Créé une nouvel string sans espace, en minuscule

                for(Starships item : datasetFull) { // on parcours notre list
                    if (item.getName().toLowerCase().contains(filterPattern)) { // Si l'item courant == taper dans la bare de recherche
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
