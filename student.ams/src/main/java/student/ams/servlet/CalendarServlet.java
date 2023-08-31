package student.ams.servlet;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.oauth2.Oauth2Scopes;


public class CalendarServlet extends AbstractAuthorizationCodeServlet {

	private static final long serialVersionUID = 7950769111377346603L;
	private static FileDataStoreFactory dataStoreFactory;
	
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
		return null;
	}

}
