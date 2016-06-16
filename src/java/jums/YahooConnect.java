
package jums;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class YahooConnect implements Serializable {

    private static String APP_ID = "dj0zaiZpPXU1VGg3eFowdEJRMyZzPWNvbnN1bWVyc2VjcmV0Jng9Yjk-";
    private static String BASE_URI = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch";
    private static String BASE_URI_ID = "http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup";

    public static YahooConnect getInstance() {
        return new YahooConnect();
    }

    public ArrayList<SearchResultBeans> YahooSearch(String word) {
        ArrayList<SearchResultBeans> al = new ArrayList<SearchResultBeans>();
        String result = "";
        JsonNode head = null;
        try {
            String urlString = URLEncoder.encode(word, "utf-8");
            URL url = new URL(BASE_URI + "?appid=" + APP_ID + "&query=" + urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String tmp = "";
            while ((tmp = in.readLine()) != null) {
                result += tmp;
            }

            JsonFactory jfactory = new JsonFactory();
            JsonParser parser = jfactory.createJsonParser(result);
            ObjectMapper mapper = new ObjectMapper();
            head = mapper.readTree(parser);

            search:
            for (int i = 0; i < 20; i++) {
                SearchResultBeans srb = new SearchResultBeans();
                String str = String.valueOf(i);
                srb.setName(head.get("ResultSet").get("0").get("Result").get(str).get("Name").textValue());
                srb.setPrice(head.get("ResultSet").get("0").get("Result").get(str).get("Price").get("_value").textValue());
                srb.setImage(head.get("ResultSet").get("0").get("Result").get(str).get("Image").get("Medium").textValue());
                srb.setCode(head.get("ResultSet").get("0").get("Result").get(str).get("Code").textValue());
                 al.add(srb);
            }
            in.close();
            con.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return al;
    }

    public LookCartBeans YahooSearchID(String word) {
        LookCartBeans srb = new LookCartBeans();
        String result = "";
        JsonNode head = null;
        try {
            URL url = new URL(BASE_URI_ID + "?appid=" + APP_ID + "&itemcode=" + word + "&responsegroup=large");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String tmp = "";
            while ((tmp = in.readLine()) != null) {
                result += tmp;
            }

            JsonFactory jfactory = new JsonFactory();
            JsonParser parser = jfactory.createJsonParser(result);
            ObjectMapper mapper = new ObjectMapper();
            head = mapper.readTree(parser);

            srb.setUrl(head.get("ResultSet").get("0").get("Result").get("0").get("Url").textValue());
            srb.setName(head.get("ResultSet").get("0").get("Result").get("0").get("Name").textValue());
            srb.setCode(head.get("ResultSet").get("0").get("Result").get("0").get("Code").textValue());
            srb.setPrice(Integer.parseInt(head.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").textValue()));
            srb.setDescription(head.get("ResultSet").get("0").get("Result").get("0").get("Description").textValue());
            srb.setImage(head.get("ResultSet").get("0").get("Result").get("0").get("Image").get("Medium").textValue());
            srb.setDescription(head.get("ResultSet").get("0").get("Result").get("0").get("Description").textValue());

            in.close();
            con.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return srb;
    }

}
