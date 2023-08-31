package student.ams.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;

import student.ams.entity.Student;
import student.ams.service.IStudentService;

import com.google.api.services.oauth2.Oauth2Scopes;


public class CalendarServletCallback extends AbstractAuthorizationCodeCallbackServlet {
	
	private static final long serialVersionUID = 20868671537378271L;
	private static FileDataStoreFactory dataStoreFactory;
	
	
	@Override
	protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential)
	      throws ServletException, IOException {
		
		req.getSession().setAttribute("credential", credential);
		resp.sendRedirect("/sigin");
	}

	@Override
	protected void onError(HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse)
	      throws ServletException, IOException {
	    // handle error
	}
	
	@Override
	protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
		dataStoreFactory = new FileDataStoreFactory(new File("D:\\test"));
		return new GoogleAuthorizationCodeFlow.Builder(
		        new NetHttpTransport(), GsonFactory.getDefaultInstance(),
		        "638065841277-68v884ftr24s4agjjm0ev0m1ffqe493v.apps.googleusercontent.com", "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",
		        Arrays.asList(CalendarScopes.CALENDAR,Oauth2Scopes.USERINFO_EMAIL,Oauth2Scopes.USERINFO_PROFILE))
				.setDataStoreFactory(dataStoreFactory).setAccessType("offline").build();
	}

	@Override
	protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath("/oauth2callback");
	    return url.build();
	}

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
		return "";
	}

}
