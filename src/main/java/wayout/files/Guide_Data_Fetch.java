package wayout.files;

import javafx.application.Platform;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Guide_Data_Fetch {
    public static void main(String[] args) {
        try {
            String url = "https://mylocation.org/";
            Document doc = Jsoup.connect(url).get();
            Element info = doc.selectFirst(".info");

            String city = "";
            String country = "";

            if (info != null) {
                Elements rows = info.select("table tr");
                for (Element row : rows) {
                    String label = row.selectFirst("td").text();
                    String value = row.select("td").get(1).text();
                    if (label.equals("City")) {
                        city = value;
                    } else if (label.equals("Country")) {
                        country = value;
                    }
                }

                System.out.println(city);
                System.out.println(country);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
