package mathieu.r.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import mathieu.r.Model.Vehicles;
import mathieu.r.R;

public class ListVehiclesAdaptater extends RecyclerView.Adapter<ListVehiclesAdaptater.ViewHolder> {

    private ArrayList<Vehicles> dataset;                                                                //List d'object Vehicles
    private Context context;

    public ListVehiclesAdaptater(Context context) {
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
        Vehicles Vehicle = dataset.get(position);                                                          // Recuperation d'un Vehicles en fonction de son numero
        holder.titreView.setText(Vehicle.getName());                                                  // Initialisation du titre dans l'objet Vehicles

        Glide.with(context)                                                                         // Recuperation d'un image en fonction du numero
                .load("https://starwars-visualguide.com/assets/img/vehicles/" + Vehicle.getNumber() + ".jpg")
                .centerCrop()                                                                       // URL de l'image
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(ArrayList<Vehicles> listVehicle) {                                                     // Ajout d'un object dans la liste
        dataset.addAll(listVehicle);
        notifyDataSetChanged();
    }

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
