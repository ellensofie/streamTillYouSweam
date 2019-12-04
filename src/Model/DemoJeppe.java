package Model;
import java.io.*;
import java.util.*;

public class DemoJeppe {
    public static void main(String[] args) {
        ArrayList<ArrayList<Episode>> series= new ArrayList<>();
        ArrayList<Episode> episodes = new ArrayList<>();
        String lines;
        boolean testing = true;
        int lenDiff = 0;
        File file = new File("./Data/serier.txt");
        try {
            BufferedReader reader = new BufferedReader( new FileReader(file));
            while((lines = reader.readLine()) != null) {
                String[] fixedTempLines = new String[5];
                String[] seriesData = lines.trim().split(";");

                //movieString splittes ved ";", derfor er movieTitle på index 0
                String seriesTitle = seriesData[0];

                //year
                String[] yearList = seriesData[1].trim().split("-");
                int startYear = Integer.parseInt(yearList[0]);
                String endYear = "-running";
                if (yearList.length==2) {
                    endYear = yearList[1];
                }
                System.out.println(startYear+ " "+endYear);
                //genre
                String[] genreA = seriesData[2].trim().split(",");
                String[] genreArray = new String[genreA.length];
                for (int i = 0; i < genreA.length; i++) {
                    genreArray[i] = genreA[i].trim();
                }
                //rating
                String ratingDot = seriesData[3].trim().replaceAll(",", ".");;
                double seriesRating = Double.parseDouble(ratingDot);
                //Seasons
                ArrayList<ArrayList<Episode>> seriesSeasons = new ArrayList<>();
                String[] allSeasonsString = seriesData[4].trim().split(";");/*
                for (int i = 0; i < allSeasonsString.length; i++) {
                    String[] tempSeasonString = allSeasonsString[i].trim().split("\\w(?=-)");
                    ArrayList<Episode> tempSeason = new ArrayList<>();
                    System.out.println(Arrays.toString(tempSeasonString));
                    for (int j = 0; j < Integer.parseInt(tempSeasonString[1]); j++) {
                        tempSeason.add(new Episode("ep"+j,j));
                    }
                    seriesSeasons.add(tempSeason);
                }*/
                System.out.println(seriesSeasons);
                //Opretter ny movie med ovenstående parametre, samt tilføjer objektet til "movies"
                /*
                Series tempSeries = new Series(seriesTitle, seriesYear, seriesRating, genreArray);
                series.add(tempSeries);

                for(Movie m : movies){
                    m.show();
                }
                 */}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
