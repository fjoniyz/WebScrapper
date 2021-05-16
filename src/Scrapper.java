import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

public class Scrapper {
    public void getPrices(){
        File f;
        Scanner scan = new Scanner(System.in);
        System.out.println("Search: ");
        String query = scan.nextLine();
        f = new File(query);
        try{
            Document doc = Jsoup.connect(query).get();
            Elements prices = doc.getElementsByClass("a-price-whole");
            Elements titleOfProduct = doc.getElementsByClass("a-size-medium a-color-base a-text-normal");
            int counter = 0;
            LinkedList<Double> listOfPrices = new LinkedList();
            LinkedList<String> listOfProducts = new LinkedList();
            double sumOfPrices = 0.0;
            int countElements = 0;
            for(Element price : prices){
                String p = price.text();
                if(p.contains(".")){
                    p = p.replace(".", "");
                }
                p = p.replace(",", ".");
                sumOfPrices += Double.parseDouble(p);
                countElements ++;
                listOfPrices.add(Double.parseDouble(p));
            }
            System.out.println("Median prices of products: " + String.format("%.2f", sumOfPrices/countElements) + " euros.");
            Collections.sort(listOfPrices);
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
