import org.apache.commons.lang.StringEscapeUtils;

public class Test {

	/**
	 * ����apache�Ķ�html��ǩ����ת��ӿ�
	 * @param args
	 */
	public static void main(String[] args) {
		String html="<html>";
		System.out.println(StringEscapeUtils.escapeHtml(html));
	}
}
