
import java.io.File;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public abstract class AbstractFileTransformer {
    protected Map<String, String> transformerProps;
    protected VelocityContext velocityContext;
    
    public AbstractFileTransformer(){
        
    }

    public void init(Map<String, String> transformerProps){
        this.transformerProps = transformerProps;
        
        String mailPath = transformerProps.get("TemplatesPath");
        
        File templateFilePath = new File(mailPath);
        String absolutePath = templateFilePath.getAbsolutePath();

        Properties p = new Properties();
        p.setProperty("file.resource.loader.path", absolutePath);
        p.setProperty("runtime.log", "velocity_example.log");
        Velocity.init(p);
    }
}
