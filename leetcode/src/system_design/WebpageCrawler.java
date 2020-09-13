package system_design;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WebpageCrawler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm";
		WebpageCrawler wc = new WebpageCrawler();
		List<String> result = wc.crawler(url);
		for(String s : result) {
			System.out.println(s);
		}
	}
	
	/*
	 * multi-thread
	 */
	
	public List<String> crawler(String url) {
		CrawlerThread.setFirstUrl(url);
		CrawlerThread[] thread_pools = new CrawlerThread[7];
		for(int i = 0; i < 7; i++) {
			thread_pools[i] = new CrawlerThread();
			thread_pools[i].start();
		}
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		for(int i = 0; i < 7; i ++) {
			thread_pools[i].stop();
		}
		
		List<String> results = CrawlerThread.getResult();
		return results;
	}
	
	
}

class CrawlerThread extends Thread{
	private static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	private static HashMap<String, Boolean> mp = new HashMap<String, Boolean>();
	private static List<String> results = new ArrayList<String>();
	public static void setFirstUrl(String url) {
		try {
			queue.put(url);
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static List<String> getResult() {
		return results;
	}
	
	@Override
	public void run() {
		while(true) {
			String url = "";
			try {
				url = queue.take();
			} catch (Exception e) {
				// TODO: handle exception
				break;
			}
			String domain = "";
			try {
				URL netUrl = new URL(url);
				domain = netUrl.getHost();
			} catch (MalformedURLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			if (!mp.containsKey(url) && domain.endsWith("wikipedia.org")) {
				mp.put(url, true);
				results.add(url);
				List<String> urls = HtmlParser.parseUrls(url);
				for (String u: urls) {
					try {
						queue.put(u);
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
				}
			}
		}
	}
}



