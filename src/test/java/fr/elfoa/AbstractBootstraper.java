package fr.elfoa;

import org.jboss.weld.context.bound.BoundConversationContext;
import org.jboss.weld.context.bound.BoundRequestContext;
import org.jboss.weld.context.bound.MutableBoundRequest;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.HashMap;

/**
 * @author Pierre Colomb
 */
public abstract class AbstractBootstraper {

    private static WeldContainer container;

    private static BoundRequestContext requestContext;

    protected static void init(){
        Weld weld = new Weld();
        container = weld.initialize();

        BoundConversationContext conversationContext = container.instance()
                                                                .select(BoundConversationContext.class)
                                                                .get();



        conversationContext.associate(new MutableBoundRequest(new HashMap<String, Object>(), new HashMap<String, Object>()));
        conversationContext.activate();

    }

    protected void startRequest(){
        requestContext = container.instance()
                                  .select(BoundRequestContext.class)
                                  .get();

        requestContext.associate(new HashMap<String, Object>());
        requestContext.activate();
    }

    protected void stopRequest(){

        requestContext.invalidate();
        requestContext.deactivate();
    }

    protected <T> T getInstance(Class<T> clazz){
        return container.instance()
                        .select(clazz)
                        .get();
    }

    protected static void shutdown(){
        container.shutdown();
    }

}
