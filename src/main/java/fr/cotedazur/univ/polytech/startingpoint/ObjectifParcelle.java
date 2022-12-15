package fr.cotedazur.univ.polytech.startingpoint;

import java.util.Optional;

public class ObjectifParcelle {
    private final int point;
    private final int nombreParcelleAposer;
    private int nombreRestantFinirObjectif;
    public ObjectifParcelle(int nombreDePoint,int nombreParcelleAposer){
        point = nombreDePoint;
        this.nombreParcelleAposer = nombreParcelleAposer;
        nombreRestantFinirObjectif = nombreParcelleAposer;
    }

    public Optional<String> checkObjectifNombreParcelleAposer(Parcelle parcelle){
        if(parcelle == null || parcelle.getClass() == ParcelleDisponible.class) return Optional.empty();
        nombreRestantFinirObjectif--;
        return Optional.of("\nIl reste " + nombreRestantFinirObjectif + " parcelle a poser");
    }
    public int getPoint(){
        return point;
    }
}
