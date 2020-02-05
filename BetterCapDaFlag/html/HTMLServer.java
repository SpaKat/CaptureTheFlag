package html;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;
import static spark.Spark.init;

import static spark.Spark.stop;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.RequestWrapper;

import CaptureTheFlagGame.GameManager;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class HTMLServer {

	private GameManager gm;
	public HTMLServer(int port, GameManager gm) {
		this.gm = gm;
		setup(port);
	}
	private void setup(int port) {
		port(port);
		get("/", (request, response) -> 
		{
			Map map = new HashMap();
			//map.put("message", "Sam");
			String t = new ThymeleafTemplateEngine().render(new ModelAndView(map, "index"));
			//System.out.println(t);
			System.out.println(request.cookies());
			return t;
		});
		post("/test", (request, response) -> {

			System.out.println(request.queryParams("command"));
			return new ThymeleafTemplateEngine().render(new ModelAndView(new HashMap(), "index"));
		});
	}
	public void close() {
		stop();	
	}
	public static void main(String[] args) {
		new HTMLServer(8008, null);
	}


}
