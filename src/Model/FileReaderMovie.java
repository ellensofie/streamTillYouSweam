package Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
public class FileReaderMovie {
    protected static ArrayList<Movie> movies;

    public FileReaderMovie(){
        movies = new ArrayList<>();
    }
    public static void main(String[] args) {
        try {
            File movieFile = new File("./Data/film.txt");
            Scanner sc = new Scanner(movieFile);
            while (sc.hasNextLine()) {
                String movieString = sc.nextLine();
                String[] movieData = movieString.split(";");

                //movieString splittes ved ";", derfor er movieTitle på index 0
                String movieTitle = movieData[0];

                //year
                String yearString = movieData[1].trim();
                int movieYear = Integer.parseInt(yearString);

                //genre
                String genreSpace = movieData[2].trim();
                String[] genreA = genreSpace.split(",");
                String[] genreArray = new String[genreA.length];
                for (int i = 0; i < genreA.length; i++) {
                    genreArray[i] = genreA[i].trim();
                }

                //rating
                String ratingString = movieData[3].trim();
                String ratingDot = ratingString.replaceAll(",", ".");  //Undgå NumberFormatException
                double movieRating = Double.parseDouble(ratingDot);
                //Opretter ny movie med ovenstående parametre, samt tilføjer objektet til "movies"
                Movie movie = new Movie(movieTitle, movieYear, movieRating, genreArray,null);
                movies.add(movie);

                /*
                for(Movie m : movies){
                    m.show();
                }
                */

                //System.out.println(Arrays.toString(movieData));
/*
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public ArrayList<Movie> getMovies(){
        for(Movie m : movies){

        }
    }
}

 */
