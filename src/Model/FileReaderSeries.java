package Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileReaderSeries {
    public static void main(String[] args) {
        ArrayList<Series> series;
        try{
            File seriesFile = new File("./Data/serier.txt");
            Scanner sc = new Scanner(seriesFile);
            while (sc.hasNextLine()){
                //Opdeler strengen efter hvert semi-kolon, og sætter hver del i et array.
                String seriesString = sc.nextLine();
                String[] seriesData = seriesString.split(";");

                //Parametre til series-konstruktør
                String seriesTitle = seriesData[0];

                String seriesYear = seriesData[1].trim();

                String genre = seriesData[2].trim();
                String[] genreArray = genre.split(",");
                for(int i = 0; i < genreArray.length; i++){
                    genreArray[i] = genreArray[i].trim();
                }

                String rating = seriesData[3].trim();
                String ratingDot = rating.replaceAll(",",".");
                Double ratingDouble = Double.parseDouble(ratingDot);

                /*TODO HJÆLP MED AT FÅ ET ARRAY AF SÆSON OG EPISODE
                //Sæson og episode i formatet ( 1-8,)
                String[] seData = seriesData[4].split(",");
                for(int i = 0; i < seData.length; i++){
                    seData[i] = seData[i].trim();
                }
                //Konverterer første tal i en String til int. (antallet af sæsoner)
                String[] seasonData = new String[];
                for(int i = 0; i < seData.length; i++) {
                    seasonData[i] = String.valueOf(seData[i].charAt(0));
                }
                 */

            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
