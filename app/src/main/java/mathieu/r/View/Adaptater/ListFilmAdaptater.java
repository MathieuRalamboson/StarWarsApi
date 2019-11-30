package mathieu.r.View.Adaptater;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import mathieu.r.DetailObjectFragment;
import mathieu.r.Model.Film;
import mathieu.r.R;
import mathieu.r.View.FilmActivity;

import static android.support.constraint.Constraints.TAG;

public class ListFilmAdaptater extends RecyclerView.Adapter<ListFilmAdaptater.ViewHolder> {

    private ArrayList<Film> dataset;                                                                //List d'object Film
    private Context context;

    public ListFilmAdaptater(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
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

        holder.imageView.setOnClickListener(new View.OnClickListener() { // Sur click image
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on: " + film.getTitle());
                Toast.makeText(context,"Clicked on Pokemon : "+film.getTitle(),Toast.LENGTH_SHORT).show();
                FilmActivity filmActivity = new FilmActivity();
                filmActivity.ChangeFragment(v); // on affiche un fragment
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(ArrayList<Film> listFilm) {                                                     // Ajout d'un object dans la liste
        dataset.addAll(listFilm);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titreView;

        public ViewHolder( View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            titreView = (TextView) itemView.findViewById(R.id.titreView);

        }
    }
}
