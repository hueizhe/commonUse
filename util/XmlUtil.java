import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XMLUtil {
    /**
     * 获取文件的xml对象，然后获取对应的根节点root
     */
    public static String getRoot(String xmlString, String node) throws Exception {
        Document document = DocumentHelper.parseText(xmlString);
        final Element root = document.getRootElement();// 获取根节点
        Map<String,Object> map = new HashMap<String,Object>();
        getNodes(root, map);// 从根节点开始遍历所有节点
        return map.get(node).toString();
    }

    /**
     * 从指定节点Element node开始,递归遍历其所有子节点
     */
    @SuppressWarnings("unchecked")
    public static void getNodes(final Element node,Map<String, Object> map) {
        System.out.println("-------开始新节点-------------");
        // 当前节点的名称、文本内容和属性
        System.out.println("当前节点名称：" + node.getName());// 当前节点名称
        System.out.println("当前节点的内容：" + node.getTextTrim());// 当前节点内容
        map.put(node.getName(), node.getTextTrim());
        final List<Attribute> listAttr = node.attributes();// 当前节点的所有属性
        for (final Attribute attr : listAttr) {// 遍历当前节点的所有属性
            final String name = attr.getName();// 属性名称
            final String value = attr.getValue();// 属性的值
            System.out.println("属性名称：" + name + "---->属性值：" + value);
        }
        // 递归遍历当前节点所有的子节点
        final List<Element> listElement = node.elements();// 所有一级子节点的list
        for (final Element e : listElement) {// 遍历所有一级子节点
            getNodes(e, map);// 递归
        }
    }
}
