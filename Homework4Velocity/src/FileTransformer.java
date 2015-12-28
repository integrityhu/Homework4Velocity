import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class FileTransformer extends AbstractFileTransformer {

    public FileTransformer(){
        super();
    }
    
    public void setContext(Map<String, String> contextMap){
        velocityContext = new VelocityContext(contextMap);
    }
    
    public String getTransformedContent(String fileName) {
        String result = null;
        
        if (Velocity.resourceExists(fileName)) {

            Template template = null;
            StringWriter writer = null;
            
            try {
                template = Velocity.getTemplate(fileName, "UTF-8");
                template.setEncoding("UTF-8");
                writer = new StringWriter();
                template.merge(velocityContext, writer);
                result = writer.toString();
            } catch (ResourceNotFoundException rnfe) {
                System.out.println("Error : cannot find template " + fileName);
            } catch (ParseErrorException pee) {
                System.out.println("Syntax error in template " + fileName + ":" + pee);
            } finally {
                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return result;
    }
}
