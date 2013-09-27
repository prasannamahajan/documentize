package storageservices;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


/**
 * Servlet implementation class StorageService
 */
@WebServlet("/StorageService")
public class StorageService extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StorageService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**y 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean result=true;
		Xmlgeneric xg=new Xmlgeneric();
		xg.l2.add(request.getParameter("nsname"));
		xg.l2.add(request.getParameter("nfname"));
		xg.l2.add(request.getParameter("osname"));
		xg.l2.add(request.getParameter("ofname"));
		xg.l2.add(request.getParameter("proff"));
		xg.l2.add(request.getParameter("address"));
		xg.l2.add(request.getParameter("date1"));
		xg.l2.add(request.getParameter("fiwina"));
		xg.l2.add(request.getParameter("adfiwit"));
		xg.l2.add(request.getParameter("nasewi"));
		xg.l2.add(request.getParameter("adsewit"));
		response.setContentType("application/json");
		JSONObject reply1=new JSONObject();
		try {
			reply1=writeResponse(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		response.getWriter().write(reply1.toString());
    	response.getWriter().flush();
    	System.out.println(xg.l2);
    	try {
			documentgen();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void documentgen() throws ParserConfigurationException, TransformerException, IOException, SAXException {
		XmlCreator xmg=new XmlCreator();
		xmg.createxml();
	}

	protected JSONObject writeResponse(boolean result)throws JSONException {
		JSONObject res=new JSONObject();
		if(result==true)
		{
		res.put("success", true);
		}
		else
		{
			res.put("success", false);
		}
		
		return res;
	}
	
	
	
		
	

}
