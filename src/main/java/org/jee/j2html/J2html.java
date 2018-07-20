package org.jee.j2html;
import static j2html.TagCreator.*;

import java.net.URLDecoder;

import j2html.attributes.Attr;
import j2html.attributes.Attr.ShortForm;
/*
 * java组织html的工具
 * https://j2html.com/examples.html
 */
public class J2html {
	
	public static void main(String[] args) {
        String aa = join(p(join(span(join("This paragraph has")).withStyle("font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, STHeiti, &quot;Microsoft Yahei&quot;, sans-serif; font-size: 16px; font-style: normal; font-weight: 400; white-space: normal;"), b("bold"), "and", i("italic"),img().withSrc("http://www.baidu.com"))),p(join(span(join("This paragraph has")).withStyle("font-family: &quot;Helvetica Neue&quot;, Helvetica, Arial, STHeiti, &quot;Microsoft Yahei&quot;, sans-serif; font-size: 16px; font-style: normal; font-weight: 400; white-space: normal;"), b("bold"), "and", i("italic"),img().withSrc("http://www.baidu.com")))).render();
        System.out.println(aa);
        String a="%3Cp%3Evvvvvvvvvvvvvvvvvvvdssssssssssssssssssssssdddddddddddddddddddddddddssssssss%3Cbr%2F%3E%3C%2Fp%3E";
        System.out.println(URLDecoder.decode(a));
    }

}
