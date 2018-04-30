package org.rp.sandboxweb.dao;

import org.mockito.Mockito;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import java.util.Hashtable;

public class MockInitialContextFactory implements InitialContextFactory {

    private static Object mockContext;

    @Override
    public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
        Context context = Mockito.mock(Context.class);
        System.out.println("getInitialContext called, hooyasee");
        Mockito.when(context.lookup("java:/comp/env/jdbc/DBDataSource")).thenReturn(mockContext);
        return context;
    }

    public static void setContext(Object ctx) {
        mockContext = ctx;
    }
}
