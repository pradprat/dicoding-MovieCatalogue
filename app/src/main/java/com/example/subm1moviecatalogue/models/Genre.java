package com.example.subm1moviecatalogue.models;

public class Genre {
    private long id;
    private String genre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenreById(long Id){
        switch ((int) Id){
            case 28 :
                return "Action";
            case 12 :
                return "Adventure";
            case 16 :
                return "Animation";
            case 35 :
                return "Comedy";
            case 80 :
                return "Crime";
            case 99 :
                return "Documentary";
            case 18 :
                return "Drama";
            case 10751 :
                return "Family";
            case 14 :
                return "Fantasy";
            case 36 :
                return "History";
            case 27 :
                return "Horror";
            case 10402 :
                return "Music";
            case 9648 :
                return "Mystery";
            case 10749 :
                return "Romance";
            case 878 :
                return "ScienceFiction";
            case 10770 :
                return "TVMovie";
            case 53 :
                return "Thriller";
            case 10752 :
                return "War";
            case 37 :
                return "Western";
            default:
                return "";
        }
    }
}
