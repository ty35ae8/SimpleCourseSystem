package student.ams.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

//自定義分頁標籤
public class NavigationTag extends TagSupport{

	private static final long serialVersionUID = -4803211304715512546L;
	
	//request中用於保存Page<E>物件的變量名，默認為"page"
	private String bean = "page";
	
	//分頁跳轉的url地址，此屬性必須
	private String url = null;
	
	//顯示頁碼數量
	private int number = 5;
	
	@Override
	public int doStartTag() throws JspException{
		JspWriter writer = pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Page page = (Page) request.getAttribute(bean);
		if(page==null) {
			return SKIP_BODY;
		}
		
		url = resolveUrl(url,pageContext);
		
		try {
			//計算總頁數
			int pageCount = page.getTotal()/page.getSize();
			if(page.getTotal()%page.getSize()>0) {
				pageCount++;
			}
			writer.print("<nav><url class=\"pagination\">");
			//頁首鏈結路徑
			String homeUrl = append(url,"page",1);
			//頁尾鏈結路徑
			String backUrl = append(url,"page",pageCount);
			//顯示"上一頁"按鈕
			if(page.getPage()>1) {
				String preUrl = append(url,"page",page.getPage()-1);
				preUrl = append(preUrl,"rows",page.getSize());
				writer.print("<li><a href=\""+homeUrl+"\">"+"頁首</a></li>");
				writer.print("<li><a href=\""+preUrl+"\">"+"上一頁</a></li>");
			}else {
				writer.print("<li class=\"disabled\"><a href=\"#\">"+"頁首</a></li>");
				writer.print("<li class=\"disabled\"><a href=\"#\">"+"上一頁</a></li>");
			}
			
			//顯示當前頁碼的前2頁和後2頁碼
			//ex: 1 -> 1 2 3 4 5 , 4 -> 2 3 4 5 6
			int indexPage = 1;
			if(page.getPage()-4<=0) {
				indexPage=1;
			}else if(pageCount-page.getPage()<=2) {
				indexPage = pageCount-4;
			}else {
				indexPage = page.getPage()-2;
			}
			for(int i=1;i<=number&&indexPage<=pageCount;indexPage++,i++) {
				if(indexPage==page.getPage()) {
					writer.print("<li class=\"active\"><a href=\"#\">"+indexPage+"<span class=\"sr-only\"></span></a></li>");
					continue;
				}
				String pageUrl = append(url,"page",indexPage);
				pageUrl = append(pageUrl,"rows",page.getSize());
				writer.print("<li><a href=\""+pageUrl+"\">"+indexPage+"</a></li>");
			}
			
			//顯示"下一頁"按鈕
			if(page.getPage()<pageCount) {
				String nextUrl = append(url,"page",page.getPage()+1);
				nextUrl = append(nextUrl,"rows",page.getSize());
				writer.print("<li><a href=\""+nextUrl+"\">"+"下一頁</a></li>");
				writer.print("<li><a href=\""+backUrl+"\">"+"頁尾</a></li>");
			}else {
				writer.print("<li class=\"disabled\"><a href=\"#\">"+"下一頁</a></li>");
				writer.print("<li class=\"disabled\"><a href=\"#\">"+"頁尾</a></li>");
			}
			writer.print("</nav>");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return super.doStartTag();
	}
	
	//為url添加翻頁請求參數
	private String resolveUrl(String url,PageContext pageContext) {
		Map params = pageContext.getRequest().getParameterMap();
		for(Object key:params.keySet()) {
			if("page".equals(key)||"rows".equals(key)) {
				continue;
			}
			
			Object value = params.get(key);
			if(value==null) {
				continue;
			}
			if(value.getClass().isArray()) {
				url = append(url,key.toString(),((String[])value)[0]);
			}else if(value instanceof String) {
				url = append(url,key.toString(),value.toString());
			}
		}
		
		return url;
	}
	
	//為url加上參數對
	private String append(String url,String key,String value) {
		if(url==null || url.trim().length()==0) {
			return "";
		}
		if(url.indexOf("?")==-1) {
			url = url + "?" + key + "=" + value;
		}else {
			if(url.endsWith("?")) {
				url = url + key + "=" + value;
			}else {
				url = url + "&amp;" + key + "=" + value;
			}
		}
		
		return url;
	}
	
	private String append(String url,String key,int value) {
		return append(url,key,String.valueOf(value));
	}

	public String getBean() {
		return bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
	
}


