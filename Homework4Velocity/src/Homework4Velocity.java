import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

/**
 * @author pzoli
 *
 */
public class Homework4Velocity {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    /**
     * @param args
     * For String resource use StringResourceLoader:
     * http://velocity.apache.org/engine/devel/apidocs/org/apache/velocity/runtime/resource/loader/StringResourceLoader.html
     * 
     */
    public static void main(String[] args) {
        
                FileTransformer fileTransformer = new FileTransformer();
                Map<String, String> transformerProps = new HashMap<String, String>();
                transformerProps.put("TemplatesPath", "");
                fileTransformer.init(transformerProps);

                Map<String, String> context = new HashMap<String, String>();
                context.put("subject", StringToolkit.HTMLEnc("hírlevél"));
                context.put("sender", StringToolkit.HTMLEnc("Papp Zoltán"));
                context.put("deliveredAt", dateFormat.format(new Date()));
                context.put("context", StringToolkit.HTMLEnc("http://integrity.hu"));
                context.put("cardId", StringToolkit.HTMLEnc("1234567890ABCDEF"));
                context.put("recipient", StringToolkit.HTMLEnc("Drozdik Szilveszter"));
                context.put("fromName", StringToolkit.HTMLEnc("Your ZoMa Agent"));
                
                fileTransformer.setContext(context);
                String mailContent = fileTransformer.getTransformedContent("mail_advice.tpl");

                System.out.println(mailContent);
    }

}
