import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns="/notused", loadOnStartup=1)
public class InitServlet extends HttpServlet {
    /**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("****************************************************************");
	}

}
