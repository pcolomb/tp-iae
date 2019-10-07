package fr.elfoa.hello.rest.todo;

import org.codehaus.jackson.map.ObjectMapper;



import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pierre Colomb
 */
public final class Todos {

    private static final Logger LOG = Logger.getLogger(Todos.class.getName());



    private Todos(){}

    static String serialize(Object o){
        ObjectMapper mapper = new ObjectMapper();

        try(Writer writer = new StringWriter()) {
            mapper.writeValue(writer, o);
            writer.close();
            return writer.toString();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Unable to serialize todo list");
            return "{}";
        }
    }
}
