/*package Model;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DemoJeppe {
    public static void main(String[] args) {
        ArrayList<Media> content = new ArrayList<>();
        ArrayList<ArrayList<Episode>> series= new ArrayList<>();
        ArrayList<Episode> episodes = new ArrayList<>();
        String lines;
            File file = new File("./Data/serier.txt");
                try {
                    BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1));
                    lines = reader.readLine();
                    String[] lineData = lines.trim().split(";");
                    String[] allSeasonsString = lineData[4].trim().split(",");
                    for (int i = 0; i < allSeasonsString.length; i++)  {
                        String[] currSeason = allSeasonsString[i].trim().split("-");
                        for (:
                             ) {

                        }
                        episodes.add(new Episode("ep"+(i+1),Integer.parseInt(currSeason[1])));
                    }
                    System.out.println(Arrays.toString(allSeasonsString));
                    System.out.println(episodes.get(0).getTitle());
                    series.add(episodes);

            } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
*/