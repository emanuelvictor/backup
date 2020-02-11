package br.edu.udc.sistemas.IAVI.emanuelvictor.annotation;

import br.edu.udc.sistemas.IAVI.emanuelvictor.entity.Marca;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

//import java.lang.reflect.Field;

/**
 * Created by emanuelvictor on 09/09/14.
 */
public class TestAnnotation {

    @Test
    public void main() throws Exception {
        Object obj = new Marca();

        Class <?> c = obj.getClass();

        if (c.isAnnotationPresent(Entity.class)) {
            if (c.isAnnotationPresent(Table.class)) {
                Table t = c.getAnnotation(Table.class);
                System.out.println(t.name());

                Field listFields[] = c.getDeclaredFields();
                for (int i = 0; i < listFields.length; i++) {
                    System.out.println(listFields[i].getName());
                    if (listFields[i].isAnnotationPresent(Id.class)) {
                        Method listMethods[] = c.getMethods();

                        for (int j = 0; j < listMethods.length; j++) {
                            if (listMethods[j].getName().toUpperCase().equals("SET" + listFields[i].getName().toUpperCase())) {
                                listMethods[j].invoke(obj, 100);
                            }

                        }
                    }
                }
            }

        }

        Marca marca = (Marca) obj;
        System.out.println(marca.getIdMarca());
    }
}
