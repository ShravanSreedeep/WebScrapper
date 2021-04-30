import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class FlipkartScraper 
{

	public static void main(String[] args)
	{
		String url="https://www.flipkart.com/mobiles/pr?sid=tyy%2C4io&p%5B%5D=facets.price_range.from%3D7000&sort=popularity&p%5B%5D=facets.price_range.to%3D10000&otracker=clp_creative_card_2_27.creativeCard.CREATIVE_CARD_mobile-phones-store_ML48SGJEH4G7&fm=neo%2Fmerchandising&iid=M_92905533-9fe2-44a7-b4b2-2fef415e78d5_27.ML48SGJEH4G7&ppt=clp&ppn=mobile-phones-store&ssid=ilz6puzvkg0000001601262889115";
		try 
		{
			FileOutputStream f = new FileOutputStream("phonelist.csv");
			PrintWriter p = new PrintWriter(f);
			Document page = Jsoup.connect(url).userAgent("Jsoup Scaper").get();
			String phoneselector = "div._4rR01T";
			String phonecashselector = "div._30jeq3._1_WHN1";
			Elements phoneElements = page.select(phoneselector);
			Elements phonePrice = page.select(phonecashselector);
			ArrayList<String> phoneNames = new ArrayList<>();
			ArrayList<String> phonepricecash = new ArrayList<>();
		
		for (Element a:phoneElements) 
			phoneNames.add(a.text());

		for (Element i:phonePrice) 
			phonepricecash.add(i.text());

		p.println("Phone,Cash");

		for (int i = 0; i < phonepricecash.size(); i++) 
		{
			String s=phoneNames.get(i).replaceAll(",", " ");
			String x=phonepricecash.get(i).replaceAll("₹", " ").replaceAll(",", " ");
			p.println(s+","+x);
		}
		
		p.close();
		
		
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
	}

}
