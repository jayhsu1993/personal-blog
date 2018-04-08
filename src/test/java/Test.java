import org.apache.commons.lang.StringEscapeUtils;

public class Test {

	/**
	 * 测试apache的对html标签进行转义接口
	 * @param args
	 */
	public static void main(String[] args) {
		String html="<html>";
		System.out.println(StringEscapeUtils.escapeHtml(html));
	}
}
