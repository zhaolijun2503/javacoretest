package org.javacore.RegularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * http://www.cnblogs.com/jingmoxukong/p/6030197.html
 * @author acfun
 *
 */
public class RegularExpression {
	
	public static void main(String args[]) {
		String content = "src: local[emot=ff/]fds[ss Sans Light'][emot=ac,01/][emot=ac,02/][emot=ac,03/], local['OpenSans-Light'], url(http://fonts.gstatic.com/s/opensans/v13/DXI1ORHCpsQm3Vp6mXoaTa-j2U0lmluP9RWlSytm3ho.woff2) format('woff2')";
		// 从内容上截取路径数组
		Pattern pattern = Pattern.compile("(?<=\\[emot=)[^]]*(?=\\/])");  
		Matcher matcher = pattern.matcher(content);
		while(matcher.find()){
		   System.out.println(matcher.group());
		   String reg = "(?<=\\[emot=" + matcher.group() + ")[^]]*(?=\\/])";
	        String patt = "[emot=" + matcher.group() + "/]";
	        String patten=matcher.group().replace(",", "/");
	        String img="<img src='http://cdn.aixifan.com/dotnet/20130418/umeditor/dialogs/emotion/images/"+patten+".gif' class='img-emot-ac'/>";
	        content=content.replace(patt,img);
	        System.out.println(content);
		   //matcher.replaceAll("")
		   //System.out.println(matcher.replaceAll(matcher.group().replaceAll(matcher.group(), "dd")));
		}
	}

}
