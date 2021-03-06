

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Policy;
import business.CustomerBO;
import business.VehicleBO;

/**
 * Servlet implementation class VehicleRegistrationServlet
 */
@WebServlet("/VehicleRegistrationServlet")
public class VehicleRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		String state = request.getParameter("state");
		String v_type = request.getParameter("type");
		String manufacturer = request.getParameter("manufacturer");
		String model = request.getParameter("model");
		int engine_number = Integer.parseInt(request.getParameter("engine"));
		int yom = Integer.parseInt(request.getParameter("yom"));
		String location = request.getParameter("location");
		int price = Integer.parseInt(request.getParameter("price"));
		String dop = request.getParameter("dop");
		String clas = request.getParameter("class");
		
		
		/*if(name.isEmpty()||v_type.isEmpty()||manufacturer.isEmpty()||model.isEmpty()||state.isEmpty()||location.isEmpty()||dop.isEmpty());
		{
			RequestDispatcher rd = request.getRequestDispatcher("vehicleregistration.jsp");
			out.println("<font color=red>Please fill all the fields</font>");
			rd.include(request, response);
		}*/
		
		{
			VehicleBO vehicleBO=new VehicleBO();
			Policy p=new Policy();
			try {
				p = vehicleBO.register(v_type,engine_number,id,state,manufacturer,model,yom,location,price,dop,clas);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    Date date = new Date();
		        System.out.println(sdf.format(date));
			  
			  
			 Date d1=null;
			 String d3;
			  Date d2 = null;
				try {
					d3=sdf.format(date);
					d1=sdf.parse(d3);
					d2 = sdf.parse(dop);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long diff = d1.getTime() - d2.getTime();
				
		
			RequestDispatcher rd = request.getRequestDispatcher("vehicle_registered.jsp");

			out.println("<font color=red>Vehicle Registered successfully</font><br>");
			if(diff<31)
			out.println("<font color=red>3% Dicount is provided </font><br>");
			if(diff>1825)
			out.println("<font color=red>Vehicle is 5 years old so concession is provided. </font><br>");	
			request.setAttribute("PolicyID", p.getPolicyid()); 
			request.setAttribute("Amount", p.getP_amount()); 
			request.setAttribute("Date", p.getM_date());
			
			rd.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
